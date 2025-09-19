package fr.eni.onepiecev4.bll;

import java.util.List;
import java.util.Random;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.stereotype.Service;

@Service
public class MethodesJeuImpl implements MethodesJeu {

    private static Random r = new Random();
    private static final char VIDE = ' ';
    private final PersonnageServiceImpl personnageServiceImpl;
    private GroupeService groupeService;
    private PersonnageService personnageService;
    private String[] compliment = { "Effectivement", "C'est vrai", "Oui" , "Tout à fait" , "Bravo" };
    private String[] deception = { "Malheureusement", "Hélas", "C'est faux", "Non" , "Perdu" };

    public MethodesJeuImpl(GroupeService groupeService, PersonnageService personnageService, PersonnageServiceImpl personnageServiceImpl) {
        this.groupeService = groupeService;
        this.personnageService = personnageService;
        this.personnageServiceImpl = personnageServiceImpl;
    }

    /**
     * Méthode permettant de tirer un groupe au hasard
     *
     * @return un groupe.
     */

    @Override
    public Groupe tirageGroupe() {
        List<Groupe> list = groupeService.consulterGroupeList();

        int numChoisi = (r.nextInt(list.size()));
        Groupe groupeChoisi = groupeService.consulterGroupeParId(numChoisi);
        return groupeChoisi;
    }

    /**
     * Méthode permettant de tirer un personnage au hasard
     *
     * @return un personnage.
     */

    @Override
    public Personnage tiragePersonnage() {
        List<Personnage> list = personnageService.consulterPersonnageListe();
        int numChoisi = (r.nextInt(list.size()));

        Personnage personnageChoisi = personnageService.consulterPersonnageParId(numChoisi);
        return personnageChoisi;
    }

    /**
     * Méthode permettant de tirer un personnage disposant d'un pseudo
     *
     * @return un personnage.
     */

    @Override
    public Personnage tiragePersonnageAvecPseudo() {
        List<Personnage> list = personnageService.consulterListePersonnagesAvecPseudo();
        int numChoisi = (r.nextInt(list.size()));
        Personnage personnageChoisi = list.get(numChoisi);
        return personnageChoisi;
    }

    @Override
    public Personnage tiragePersonnageAvecAge() {
        List<Personnage> list = personnageService.consulterListePersonnagesAvecAge();
        int numChoisi = (r.nextInt(list.size()));
        Personnage personnageChoisi = list.get(numChoisi);
        return personnageChoisi;
    }

    @Override
    public Personnage tiragePersonnageAvecPrime() {
        List<Personnage> list = personnageService.consulterListePersonnagesAvecPrime();
        int numChoisi = (r.nextInt(list.size()));
        Personnage personnageChoisi = list.get(numChoisi);
        return personnageChoisi;
    }


    /**
     * Methode permettant de transformer une String en une suite de Char, après en avoir supprimé les caractères.
     *
     * @param nomusuel
     * @return
     */

    @Override
    public char[] nomemclature(String nomusuel) {
        String chaineSaisie = nomusuel;
        chaineSaisie = chaineSaisie.replaceAll("\\s", "");
        char accents[][] = {{'é', 'è', 'ê', 'à', 'ù', 'û', 'ô', 'É', 'Ê', 'ë', 'â', 'î', 'ï', 'ç',},
                {'e', 'e', 'e', 'a', 'u', 'u', 'o', 'e', 'e', 'e', 'a', 'i', 'i', 'c',}};
        // Matrice de correspondance des caractères accentués en caractères sans accent
        for (int j = 0; j < accents[0].length; j++) {
            chaineSaisie = chaineSaisie.replace(accents[0][j], accents[1][j]);
        }
        char[] mot = chaineSaisie.toUpperCase().toCharArray();

        return mot;
    }

    /**
     * Méthode permettant de créer un nouveau tableau de charactères contenant les
     * mêmes caractères que ceux présent dans le tableau passé en paramètre. Mais
     * ceux-ci sont positonné dans un ordre aléatoire
     *
     * @param mot : le tableau de caractères contenant les lettres du mot
     * @return un nouveau tableau dont les caractères ont été mélangés aléatoirement
     */
    @Override
    public char[] melanger(char[] mot) {
        // clonage du tableau
        char[] mel = new char[mot.length];
        for (int i = 0; i < mel.length; i++) {
            mel[i] = mot[i];
        }

        // Echanges de position de caractères
        for (int i = 0; i < mel.length * 4; i++) {
            int p1 = r.nextInt(mel.length);
            int p2 = r.nextInt(mel.length);
            char tmp = mel[p1];
            mel[p1] = mel[p2];
            mel[p2] = tmp;
        }
        return mel;
    }

    /**
     * Méthode permettant de prendre la réponse entré par le joueur
     * et de la comparer au résultat attendu.
     *
     * @param reponseNom
     * @param personnage
     * @return String
     */

    @Override
    public String jeuDesLettres(String reponseNom, Personnage personnage) {
        String affichageResultat = "";

		// créer une réponse, des variantes du nom et du prénom et en unifie l'orthographe
        String reponse = String.valueOf(nomemclature(reponseNom));
		System.out.println("reponse = " + reponse);
        char[] reponseAttendueTab = nomemclature(personnage.getNom() + personnage.getPrenom());
        String reponseAttendue = String.valueOf(reponseAttendueTab);
        char[] reponseAccepteeTab = nomemclature(personnage.getPrenom() + personnage.getNom());
        String reponseAcceptee = String.valueOf(reponseAccepteeTab);
        char[] reponseAttendueAlternativeTab = nomemclature(personnage.getNom() + personnage.getParticule() + personnage.getPrenom());
        String reponseAttendueAlternative = String.valueOf(reponseAttendueAlternativeTab);

		// verifie la concordence nom + prenom et ses variantes
        if (reponse.equals(reponseAttendue) ||
                reponse.equals(reponseAttendueAlternative) ||
                reponse.equals(reponseAcceptee)) {
            affichageResultat = "Complete";
        }
		else {
            String[] reponseSplit = reponseNom.split(" ");
            System.out.println(reponseSplit);
            // Recherche de la concordance réponse - nom
            if (!personnage.getNom().equals(' ') && personnage.getNom() != null) {
                for (int i = 0; i < reponseSplit.length; i++) {
                    String valeur = String.valueOf(nomemclature(reponseSplit[i]));
                    if (valeur.equals(String.valueOf(nomemclature(personnage.getNom())))) {
                        affichageResultat = "Nom";
                    }
                }
            }
            System.out.println(affichageResultat);
            //Recherche de la concordance réponse - prenom
            for (int i = 0; i < reponseSplit.length; i++) {
                String valeur = String.valueOf(nomemclature(reponseSplit[i]));
                if (valeur.equals(String.valueOf(nomemclature(personnage.getPrenom())))) {
                    affichageResultat = "Prenom";
                }
            }
        }

        return affichageResultat;
    }

    /**
     * Méthode en charge de comparer 2 tableaux de caractères et retourne vrai si
     * les 2 tableaux de caractères sont identiques (meme nombre de valeurs et memes
     * valeurs)
     *
     * @param prop le premier mot à comparer
     * @param mot  le seconde mot à comparer.
     * @return Vrai si les 2 tableaux de caractères sont identiques.
     */


    public static boolean sontIdentiques(char[] prop, char[] mot) {
        boolean ok = prop.length == mot.length;
        if (ok) {
            int i = 0;
            while (ok && i < mot.length) {
                ok = prop[i] == mot[i];
                i++;
            }
        }
        return ok;
    }

    /**
     * Méthode en charge de vérifier si tous les caractères présents dans le tableau
     * de caracteres {@code prop} sont présents dans le tableau de caractères
     * {@code tirage}. Un caractère présent plusieurs fois dans {@code prop} doit
     * être présent au moins autant de fois dans {@code tirage}.
     *
     * @param prop   le tableau avec les caractères à vérifier.
     * @param tirage le tableau avec les caractères à utiliser.
     * @return vrai si uniquement les caractères présent dans {@code tirage} sont
     * utilisés dans {@code prop}
     */

    @Override
    public boolean bonnesLettres(char[] prop, char[] tirage) {
        // clonage du tableau
        char[] copie = new char[tirage.length];
        for (int i = 0; i < copie.length; i++) {
            copie[i] = tirage[i];
        }

        // Vérification de chaque lettre de la proposition
        int j = 0;
        boolean ok = true;
        while (ok && j < prop.length) {
            int k = 0;
            while (k < copie.length && prop[j] != copie[k]) {
                k++;
            }
            if (k == copie.length) {
                ok = false;
            } else {
                copie[k] = VIDE;
                j++;
            }
        }
        return ok;
    }

    @Override
    public Integer calculduScore(Integer score, String resultat) {
        if (resultat.equals("Complete")) {
            score = 10;
        }
        if (resultat.equals("Nom") || resultat.equals("Prenom")) {
            score = 5;
        }

        return score;
    }

    @Override
    public Integer calculduScore2(String resultat) {
        int score = 0;
        if (resultat.equals("Gagne")) {
            score = 10;
        }
        if (resultat.equals("Perdu")) {
            score = 0;
        }

        return score;
    }

    /**
     * Méthode en charge de scanner le tableau pour voir si le personnage s'y trouve.
     * @param mot
     */
	/*
	static boolean scanPerso(String nomPerso, Groupe groupePirate2) {
		boolean confirmation = false;
		List <Personnage> groupeDuPerso = groupePirate2.nomdeliste;
		for (int i = 0; i<(groupeDuPerso.size()); i++){	
			if (nomPerso==(groupeDuPerso.get(i).nomusuel)) confirmation = true;
			};
		
		return confirmation;
		}

	 */

    /**
     * Méthode permettant de comparer la provenance de deux personnages selon leur
     * groupe d'appartenance
     *
     * @return un score
     */
    @Override
    public String[] questionSurnom(Personnage perso) {
        String proposition1 = "";
        String proposition2 = "";
        String proposition3 = "";
        String surnom1 = perso.getSurnom();
        Personnage perso2 = tiragePersonnageAvecPseudo();
        String surnom2 = perso2.getSurnom();

        Personnage perso3 = tiragePersonnageAvecPseudo();
        String surnom3 = perso3.getSurnom();
        int numTirage = (r.nextInt(6));
        if (numTirage == 1) {
            proposition1 = surnom1;
            proposition2 = surnom2;
            proposition3 = surnom3;
        }
        if (numTirage == 2) {
            proposition1 = surnom2;
            proposition2 = surnom3;
            proposition3 = surnom1;
        }
        if (numTirage == 4) {
            proposition1 = surnom2;
            proposition2 = surnom1;
            proposition3 = surnom3;
        }
        if (numTirage == 3) {
            proposition1 = surnom3;
            proposition2 = surnom1;
            proposition3 = surnom2;
        }
        if (numTirage == 5) {
            proposition1 = surnom3;
            proposition2 = surnom2;
            proposition3 = surnom1;
        } else {
            proposition1 = surnom1;
            proposition2 = surnom3;
            proposition3 = surnom2;
        }
        ;
        String[] tableauDesPropositions = {Integer.toString(numTirage), proposition1, proposition2, proposition3};
        return tableauDesPropositions;
    }


    /**
     * Permet de permuter les réponses d'un tableau
     *
     * @param tableauDesPropositions
     * @param score
     * @param surnom
     * @param reponse
     * @return
     */
    @Override
    public String reponseSurnom(String reponsePseudo, Personnage personnage) {
        String reponse="";
        if (reponsePseudo.equals(personnage.getSurnom())) {
            reponse = "Gagne";
        }
 		else {
             reponse = "Perdu";
        }

        return reponse;
    }

    /**
     * Va chercher dans la liste si le groupe et le perso concordent et attribue une réponse en fonction de la réponse du joueur
     * @param reponse
     * @param persoChoisi
     * @param groupeChoisi
     * @return Gagné ou Perdu
     */

    @Override
    public String reponseEquipage(String reponse, Personnage persoChoisi, Groupe groupeChoisi) {

        boolean appartenance = personnageService.verifierAppartenance(persoChoisi, groupeChoisi);

        if (reponse.equals("oui") && appartenance == true ) {
            return "Gagne";
        }
        if (reponse.equals("oui") && appartenance == false ) {
            return "Perdu";
        }
        if (reponse.equals("non") && appartenance == false ) {
            return "Gagne";
        }
        else {
            return "Perdu";
        }
    }

    /**
     *  Génére une réponse à afficher selon la réponse du joueur, l'appartenance du personnage au groupe, et le nom du personnage et du groupe choisi
     * @param resultat
     * @param reponse
     * @param persoChoisi
     * @param groupeChoisi
     * @return
     */
    @Override
    public String AffichageReponseJeuEquipage(String resultat, String reponse, Personnage persoChoisi, Groupe groupeChoisi) {
        String premierePartie = "";
        String deuxiemePartie = "";
        if (resultat.equals("Gagne")){
            int numChoisi = (r.nextInt(compliment.length));
            premierePartie = compliment[numChoisi];
        }
        else if (resultat.equals("Perdu")){
            int numChoisi = (r.nextInt(deception.length));
            premierePartie = deception[numChoisi];
        }

        if ((reponse.equals("oui") && resultat.equals("Gagne"))||(reponse.equals("non") && resultat.equals("Perdu"))) {
            deuxiemePartie = "a bien fait partie de ";
        }
        else if ((reponse.equals("oui") && resultat.equals("Perdu"))||(reponse.equals("non") && resultat.equals("Gagne"))) {
            deuxiemePartie = "n'a jamais fait partie de ";
        }

        return premierePartie + ", " +
                 persoChoisi.getNom() + ' '
                + persoChoisi.getParticule() + ' '
                + persoChoisi.getPrenom() + ' '
                + deuxiemePartie
                + groupeChoisi.getNom()
                + '.';
    }

    /**
     * Methode permettant de comparer l'age de deux personnages et de les comparer à la réponse donnée
     * @param reponse
     * @param persoPrincipal
     * @param persoSecondaire
     * @return Gagné ou Perdu
     */

    @Override
    public String reponseAge(String reponse, Personnage persoPrincipal, Personnage persoSecondaire) {

        if (reponse.equals("oui") && persoPrincipal.getAge() == persoSecondaire.getAge() ) {
            return "Gagne";
        }
        else if (reponse.equals("+vieux") && persoPrincipal.getAge() > persoSecondaire.getAge() ) {
            return "Gagne";
        }
        else if (reponse.equals("+jeune") && persoPrincipal.getAge() < persoSecondaire.getAge() ) {
            return "Gagne";
        }
        else {
            return "Perdu";
        }

    }

    @Override
    public String AffichageReponseJeuAge(String resultat, Personnage persoPrincipal, Personnage persoSecondaire) {
        String premierePartie = "";
        String deuxiemePartie = "";
        if (resultat.equals("Gagne")){
            int numChoisi = (r.nextInt(compliment.length));
            premierePartie = compliment[numChoisi];
        }
        else if (resultat.equals("Perdu")){
            int numChoisi = (r.nextInt(deception.length));
            premierePartie = deception[numChoisi];
        }

        if (persoPrincipal.getAge() == persoSecondaire.getAge() ) {
            return premierePartie + ' ' +
                    persoPrincipal.getNom() + ' '
                    + persoPrincipal.getParticule() + ' '
                    + persoPrincipal.getPrenom() + " et "
                    + persoSecondaire.getNom() + ' '
                    + persoSecondaire.getParticule() + ' '
                    + persoSecondaire.getPrenom() + " ont le même âge ("
                    + persoPrincipal.getAge() + " ans.)"
                    ;
        } else {
            if (persoPrincipal.getAge() > persoSecondaire.getAge()) {
                if (persoPrincipal.getSexe() == 'F') {
                    deuxiemePartie = " est plus vieille";
                } else {
                    deuxiemePartie = "est plus vieux ";
                }
            } else if (persoPrincipal.getAge() < persoSecondaire.getAge()) {
                    deuxiemePartie = " est plus jeune ";
            }

        }
            return premierePartie + ' ' +
                    persoPrincipal.getNom() + ' '
                    + persoPrincipal.getParticule() + ' '
                    + persoPrincipal.getPrenom() +
                    deuxiemePartie + "(" +
                    persoPrincipal.getAge() + " ans) que " +
                    persoSecondaire.getNom() + ' '
                    + persoSecondaire.getParticule() + ' '
                    + persoSecondaire.getPrenom() + " (" +
                    persoSecondaire.getAge() + " ans)";
        }
    }

    /*

	public static Personnage questionPrime(Personnage perso) {

			Groupe groupePirate = tirageGroupe();
			Personnage perso2 = tirerPersoAleatoirement(groupePirate);
			
			if (perso2.prime == 0){Groupe groupePirate4 = MethodesJeu.tirageGroupe();
				perso2 = MethodesJeu.tirerPersoAleatoirement(groupePirate4);
				}
			
			System.out.println("Entre " + perso.nomusuel + " et " + perso2.nom + " " + perso2.prenom+ " lequel a une plus grosse prime ?");
			System.out.println(perso.nomusuel + "(tapez 1) ");
			System.out.println(perso2.nom + " " + perso2.prenom + "(tapez 2)");
			System.out.println("Ces deux persos ont la même prime (tapez 3) ");

			return perso2;
			}

	public static int reponsePrime(Personnage perso, Personnage perso3, int score, int prop6) {
		long  prime1 = perso.prime; long prime2 = perso3.prime;
		String montantPrime1 ="montant inconnu"; 
		String montantPrime2 ="montant inconnu";
		if(prime1>1000000) montantPrime1 = Long.toString(prime1/1000000) + " Millions";
		if(prime1>1000000000) montantPrime1 = Long.toString(prime1/1000000000) + " Milliards";
		if(prime2>1000000) montantPrime2 = Long.toString(prime2/1000000) + " Millions";
		if(prime2>1000000000) montantPrime2 = Long.toString(prime2/1000000000) + " Milliards";
		
		if (prop6 == 1){
			if ((prime1-prime2)<0){
				System.err.println("PERDU : la prime de " + perso.nomusuel + " s'élève à " + montantPrime1 + " de berrys tandis que celle de " + perso3.nomusuel + " est a " +  montantPrime2 + " de berry");
				};
			if ((prime1-prime2)>0){System.out.println("GAGNÉ : la prime de " + perso.nomusuel + " s'élève à " + montantPrime1 + " de berrys tandis que celle de " + perso3.nomusuel + " est a " +  montantPrime2 + " de berry");
				System.out.println("+10 points!");
				score = (score + 10);
				};
			if ((prime1-prime2)==0){
				System.err.println("PERDU : Ces deux personnages ont tous les deux la même prime");
				};
			}
		if (prop6 == 2){
				if ((prime1-prime2)<0){System.out.println("GAGNÉ : la prime de " + perso.nomusuel + " s'élève à " + montantPrime1 + " de berrys tandis que celle de " + perso3.nomusuel + " est a " +  montantPrime2 + " de berry");
				System.out.println("+10 points!");
				score = (score + 10);
				};
				if ((prime1-prime2)>0){
				System.err.println("PERDU : la prime de " + perso.nomusuel + " s'élève à " + montantPrime1 + " de berrys tandis que celle de " + perso3.nomusuel + " est a " +  montantPrime2 + " de berry");
				};
				if ((prime1-prime2)==0){
				System.err.println("PERDU : Ces deux personnages ont tous les deux la même prime");
				};
			};
		if (prop6 == 3){
			if ((prime1-prime2)<0){
				System.err.println("PERDU : la prime de " + perso.nomusuel + " s'élève à " + montantPrime1 + " de berrys tandis que celle de " + perso3.nomusuel + " est a " +  montantPrime2 + " de berry");
			};
			if ((prime1-prime2)>0){System.err.println("PERDU : la prime de " + perso.nomusuel + " s'élève à " + montantPrime1 + " de berrys tandis que celle de " + perso3.nomusuel + " est a " +  montantPrime2 + " de berry");
				};
			if ((prime1-prime2)==0){
				System.out.println("GAGNÉ : Ces deux personnages ont effectivement tous les deux la même prime");
				System.out.println("+10 points!");
				score = (score + 10);
				};
			};


		return score;
	}

*/


