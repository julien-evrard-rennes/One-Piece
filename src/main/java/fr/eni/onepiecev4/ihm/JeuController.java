package fr.eni.onepiecev4.ihm;

import fr.eni.onepiecev4.bll.GroupeService;
import fr.eni.onepiecev4.bll.MethodesJeu;
import fr.eni.onepiecev4.bll.PersonnageService;
import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/jeu")
@Controller
public class JeuController {

    public GroupeService groupeService;
    public PersonnageService personnageService;
    public MethodesJeu methodesJeu;

    public JeuController(GroupeService groupeService, PersonnageService personnageService, MethodesJeu methodesJeu) {
        this.groupeService = groupeService;
        this.personnageService = personnageService;
        this.methodesJeu = methodesJeu;
    }

    //@ModelAttribute("groupeChoisi")
    //public Groupe GroupeChoisi () {
    //return methodesJeu.tirageGroupe();
    //}

    @GetMapping
    public String affichageJeu( Model model) {

        // Tirage d'un personnage
        Personnage personnageChoisi = methodesJeu.tiragePersonnage();
        model.addAttribute("personnageChoisi", personnageChoisi);

        // Transformation du nom et du prenom en une suite de char que l'on mélange
        char[] tirageNom = methodesJeu.melanger(methodesJeu.nomemclature(personnageChoisi.getNom()));
        char[] tiragePrenom = methodesJeu.melanger(methodesJeu.nomemclature(personnageChoisi.getPrenom()));
        model.addAttribute("tirageNom", tirageNom);
        model.addAttribute("tiragePrenom", tiragePrenom);

        Integer score =0;
        model.addAttribute("score", score);

        return "/jeu/affichage_jeu_melange";
    }


    @PostMapping("/premiere_reponse")
    public String premiereReponse(@RequestParam String reponseNom,
                                  long idPerso,
                                  Integer score,
                                  Model model) {

        // Réponse renvoyée à la vue
        model.addAttribute("reponseBrute", reponseNom);

        Personnage persoChoisi = personnageService.consulterPersonnageParId(idPerso);
        model.addAttribute("personnageChoisi", persoChoisi);
        String nomComplet = methodesJeu.nomComplet(persoChoisi);
        model.addAttribute("nomComplet", nomComplet);

        // On envoie à la vue une réponse selon le résultat
        String resultat = methodesJeu.jeuDesLettres(reponseNom, persoChoisi);
        if (resultat.equals("Complete")) {
            model.addAttribute("resultat", "Vous avez trouvé son nom complet.");
        }
        if (resultat.equals("Nom")){
            model.addAttribute("resultat", "Vous n'avez trouvé que son nom.");
        }
        if (resultat.equals("Prenom")){
            model.addAttribute("resultat", "Vous n'avez trouvé que son prénom.");
        }
        if (resultat.isEmpty()){
            model.addAttribute("resultat", "Perdu");
        }

        // Affichage du score de manière dynamique
        Integer pointsGagnes = methodesJeu.calculduScore(score, resultat);
        model.addAttribute("score", pointsGagnes);
        model.addAttribute("nouveauScore", score + pointsGagnes);

        // Lancement du second jeu si le personnage a un surnom
        if (persoChoisi.getSurnom() != null) {
            model.addAttribute("questionSuivante", "Surnom");
            String resultatSurnom [] = methodesJeu.questionSurnom(persoChoisi);
            model.addAttribute("proposition1", resultatSurnom[1]);
            model.addAttribute("proposition2", resultatSurnom[2]);
            model.addAttribute("proposition3", resultatSurnom[3]);
        }
        else{
            model.addAttribute("questionSuivante", "Passer");
        }


        return "/jeu/affichage_resultat_melange"; // renvoie vers la page de résultat
    }


    @PostMapping("/question_pseudo")
    public String reponsePseudo(@RequestParam
                                    String reponsePseudo,
                                long idPerso,
                                Integer score,
                                Model model) {

        Personnage persoChoisi = personnageService.consulterPersonnageParId(idPerso);
        model.addAttribute("personnageChoisi", persoChoisi);

        String reponseAttendue = persoChoisi.getSurnom();
        model.addAttribute("reponseAttendue", reponseAttendue);
        model.addAttribute("reponsePseudo", reponsePseudo);

        // On envoie à la vue une réponse selon le résultat
        String resultat = methodesJeu.reponseSurnom(reponsePseudo, persoChoisi);
        if (resultat.equals("Gagne")) {
            model.addAttribute("resultat", "Effectivement le surnom de "
                    +persoChoisi.getNom() + ' ' + persoChoisi.getPrenom()
                    + " est bien '" + persoChoisi.getSurnom() + "'");
        }
        if (resultat.equals("Perdu")) {
            model.addAttribute("resultat", "Perdu, le surnom de "
                    +persoChoisi.getNom() + ' ' + persoChoisi.getPrenom() 
                    + " est '" + persoChoisi.getSurnom()+ "'");
        }

        // Affichage du score de manière dynamique
        Integer pointsGagnes = methodesJeu.calculduScore2(resultat);
        model.addAttribute("score", pointsGagnes);
        model.addAttribute("nouveauScore", score + pointsGagnes);

        return "/jeu/affichage_resultat_pseudo"; // renvoie vers la page de résultat
    }

    @PostMapping("/jeu_groupe")
    public String questionGroupe(@RequestParam
                                long idPerso,
                                Integer score,
                                int numerodequestion,
                                Model model) {

        Personnage persoChoisi = personnageService.consulterPersonnageParId(idPerso);
        model.addAttribute("numeroDeQuestion", numerodequestion);
        model.addAttribute("personnageChoisi", persoChoisi);
        String nomComplet = methodesJeu.nomComplet(persoChoisi);
        model.addAttribute("nomComplet", nomComplet);

        if (persoChoisi.getSexe() =='F') {
            model.addAttribute("pronom", "elle");
        }
        else {
            model.addAttribute("pronom", "il");
        }

        model.addAttribute("score", score);
        Groupe groupeChoisi = methodesJeu.tirageGroupe();
        model.addAttribute("groupeChoisi", groupeChoisi);

        return "/jeu/affichage_jeu_groupe";
    }

    @PostMapping("/jeu_groupe_reponse")
    public String reponseGroupe(@RequestParam
                                long idPerso,
                                Integer score,
                                int numerodequestion,
                                int idGroupe,
                                String reponse,
                                Model model) {
        Personnage persoChoisi = personnageService.consulterPersonnageParId(idPerso);
        Groupe groupeChoisi = groupeService.consulterGroupeParId(idGroupe);
        model.addAttribute("personnageChoisi", persoChoisi);

        /* On calcule si c'est gagné ou perdu */
        String resultat = methodesJeu.reponseEquipage(reponse, persoChoisi, groupeChoisi);

        /* On va créer dynamiquement une réponse qui va s'afficher */
        String reponseAffiche = methodesJeu.AffichageReponseJeuEquipage(resultat, reponse, persoChoisi, groupeChoisi);
        model.addAttribute("reponseAffiche", reponseAffiche);
        model.addAttribute("numeroDeQuestion", numerodequestion);

        /* Affichage du score */
        Integer pointsGagnes = methodesJeu.calculduScore2(resultat);
        model.addAttribute("score", pointsGagnes);
        model.addAttribute("nouveauScore", score + pointsGagnes);

        // Affichage de la prochaine question si on connait l'age du personnage

        if (persoChoisi.getAge() >0) {
            model.addAttribute("questionSuivante", "Age");
            String nomComplet = methodesJeu.nomComplet(persoChoisi);
            model.addAttribute("nomComplet", nomComplet);

            /* Tirage d'un nouveau personnage et affichage de son nom */
            Personnage secondPerso = methodesJeu.tiragePersonnageAvecAge();
            if (secondPerso.getId() == persoChoisi.getId()) {
                if (persoChoisi.getId() < groupeService.consulterGroupeList().size()) {
                    secondPerso = personnageService.consulterPersonnageParId((idPerso) + 1);
                } else {
                    secondPerso = personnageService.consulterPersonnageParId((idPerso) - 1);
                }
            }
            model.addAttribute("secondPerso", secondPerso);
            String nomCompletPerso2 = methodesJeu.nomComplet(secondPerso);
            model.addAttribute("nomCompletPerso2", nomCompletPerso2);

            if (persoChoisi.getSexe() == 'F') {
                model.addAttribute("pronom", "elle");
                model.addAttribute("reponsePlusVieux", "elle est plus vieille");
                model.addAttribute("reponsePlusJeune", "elle est plus jeune");
            } else {
                model.addAttribute("pronom", "il");
                model.addAttribute("reponsePlusVieux", "il est plus vieux");
                model.addAttribute("reponsePlusJeune", "il est plus jeune");
            }
        } else {
            model.addAttribute("questionSuivante", "Passer");
            model.addAttribute("secondPerso", persoChoisi);
        }

        return "/jeu/affichage_resultat_jeu_groupe";
    }

    @PostMapping("/jeu_age_reponse")
    public String reponseAge(@RequestParam
                             long idPerso,
                             Integer score,
                             int numerodequestion,
                             long idPerso2,
                             String reponse,
                             Model model) {
        Personnage persoPrincipal = personnageService.consulterPersonnageParId(idPerso);
        Personnage persoSecondaire = personnageService.consulterPersonnageParId(idPerso2);

        // Si le jeu de l'age concerne le personnage
        if (persoPrincipal.getAge()!= 0) {

            model.addAttribute("reponseAfficher", "age");

            // On calcule si c'est gagné ou perdu
            String resultat = methodesJeu.reponseAge(reponse, persoPrincipal, persoSecondaire);
            model.addAttribute("resultat", resultat);

            // On va créer dynamiquement une réponse qui va s'afficher
            String reponseAffiche = methodesJeu.AffichageReponseJeuAge(resultat, persoPrincipal, persoSecondaire);
            model.addAttribute("reponseAffiche", reponseAffiche);

            // Affichage du score
            Integer pointsGagnes = methodesJeu.calculduScore2(resultat);
            model.addAttribute("score", pointsGagnes);
            model.addAttribute("nouveauScore", score + pointsGagnes);
        }
        else {
            Integer pointsGagnes = 0;
            model.addAttribute("score", pointsGagnes);
            model.addAttribute("nouveauScore", score + pointsGagnes);
            model.addAttribute("reponseAfficher", "rien");
        }

        // On lance et on affiche le jeu suivant
        model.addAttribute("numeroDeQuestion", numerodequestion);
        model.addAttribute("personnageChoisi", persoPrincipal);
        String nomComplet = methodesJeu.nomComplet(persoPrincipal);
        model.addAttribute("nomComplet", nomComplet);

        /* Tirage du persoTertiaire et affichage de son nom */

        Personnage persoTertiaire = methodesJeu.tiragePersonnageAvecPrime();
        if (persoTertiaire.getId() == persoPrincipal.getId()) {
            if (persoPrincipal.getId() > 1) {
                persoTertiaire = personnageService.consulterPersonnageParId((idPerso) - 1);
            } else {
                persoTertiaire = personnageService.consulterPersonnageParId((idPerso) + 1);
            }
        }
        model.addAttribute("troisiemePerso", persoTertiaire);
        String nomCompletPerso3 = methodesJeu.nomComplet(persoTertiaire);
        model.addAttribute("nomCompletPerso3", nomCompletPerso3);

        return "/jeu/affichage_resultat_jeu_age";
    }

    @PostMapping("/jeu_prime_reponse")
    public String reponsePrime(@RequestParam
                             long idPerso,
                             Integer score,
                             int numerodequestion,
                             long idPerso3,
                             String reponse,
                             Model model) {
        Personnage persoPrincipal = personnageService.consulterPersonnageParId(idPerso);
        Personnage persoSecondaire = personnageService.consulterPersonnageParId(idPerso3);

        model.addAttribute("personnageChoisi", persoPrincipal);
        String nomComplet = methodesJeu.nomComplet(persoPrincipal);
        model.addAttribute("nomComplet", nomComplet);

        /* On calcule si c'est gagné ou perdu */
        String resultat = methodesJeu.reponsePrime(reponse, persoPrincipal, persoSecondaire);
        model.addAttribute("resultat", resultat);
        model.addAttribute("numeroDeQuestion", numerodequestion);

        /* On va créer dynamiquement une réponse qui va s'afficher */
        String reponseAffiche = methodesJeu.AffichageReponseJeuPrime(resultat, persoPrincipal, persoSecondaire);
        model.addAttribute("reponseAffiche", reponseAffiche);

        /* Affichage du score */
        Integer pointsGagnes = methodesJeu.calculduScore2(resultat);
        model.addAttribute("score", pointsGagnes);
        model.addAttribute("nouveauScore", score + pointsGagnes);

        return "/jeu/affichage_resultat_jeu_prime";
    }



    @PostMapping ("/finir_le_jeu")
    public String cartonDeFin (@RequestParam
                               long idPerso,
                               Integer score,
                               int numerodequestion,
                               Model model){
        Personnage persoPrincipal = personnageService.consulterPersonnageParId(idPerso);
        model.addAttribute("personnageChoisi", persoPrincipal);
        String nomComplet = methodesJeu.nomComplet(persoPrincipal);
        model.addAttribute("nomComplet", nomComplet);
        model.addAttribute("numeroDeQuestion", numerodequestion);
        model.addAttribute("score", score);
        Integer total = (numerodequestion * 10);
        model.addAttribute("total", total);

        Integer pourcentage = methodesJeu.calculerscorefinal(score, total);
        model.addAttribute("pourcentage", pourcentage);

        model.addAttribute("reponseAffiche", "");

        return "/jeu/affichage_conclusion";
    }


    }
