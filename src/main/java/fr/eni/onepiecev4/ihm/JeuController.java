package fr.eni.onepiecev4.ihm;

import fr.eni.onepiecev4.bll.GroupeService;
import fr.eni.onepiecev4.bll.MethodesJeu;
import fr.eni.onepiecev4.bll.PersonnageService;
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
            model.addAttribute("triche", resultatSurnom[0]);
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
            model.addAttribute("resultat", "Perdu, le surnom de"
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
                                Model model) {

        Personnage persoChoisi = personnageService.consulterPersonnageParId(idPerso);
        model.addAttribute("personnageChoisi", persoChoisi);
        model.addAttribute("score", score);



        return "/jeu/affichage_jeu_groupe";
    }



    }
