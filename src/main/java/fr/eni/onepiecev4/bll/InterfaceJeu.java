package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;

import java.util.Random;
import java.util.Scanner;

public class InterfaceJeu {

/*
	public static Random r = new Random();
	public static final char VIDE = ' ';

	// Mettre le score à 0;
	public static int score = 0;

    public static void LancerJeu(){


		// 0.0 Instancier le jeu pour dix parties
		Scanner console = new Scanner(System.in);
		// 0.5 - Faire une jolie présentation
		System.out.println("####################################");
		System.out.println("####   LE JEU DE ONE PIECE !!!  ####");
		System.out.println("####################################");

		// Commencer la boucle de gameplay
		for (int i = 1; i < 6; i++) {
			
			// 1 - Tirage aléatoire d'un groupe de pirate dans le tableau
			Groupe groupePirate =  MethodesJeu.tirageGroupe();
			// 3 - Tirage aléatoire d'un personnage dans le tableau choisi
			Personnage perso = MethodesJeu.tirerPersoAleatoirement(groupePirate);
			String nomDuPerso = perso.nomusuel;

			//4 - Transformation du nom du personnage en une suite de lettres
			char[] mot = MethodesJeu.nomemclateur(nomDuPerso);

			// 5 - mélanger les lettres du mot à trouver
			char[] tirage = fr.eni.jse.bll.MethodesJeu.melanger(mot);

			// 6 - Présenter le tirage au joueur
			System.out.println("Voici le tirage n° " + i);
			fr.eni.jse.bll.MethodesJeu.afficher(tirage);
			// 7 - Saisir proposition
			System.out.println("Quel est le mot caché ? (sans espace)");
			// 8 -  On récupére la saisie de l'utilisateur, on converti en minuscule
			// puis on transforme la string en tableau de caractère.
			String saisie = console.nextLine().toLowerCase();
			char[] prop = fr.eni.jse.bll.MethodesJeu.nomemclateur(saisie);

			// 9 - Vérifications Si les lettres de la proposition et du tirage sont identiques.
			if (!fr.eni.jse.bll.MethodesJeu.bonnesLettres(prop, tirage)) {
				System.err.println("Lettre incorrecte !");
			}
			if (!fr.eni.jse.bll.MethodesJeu.sontIdentiques(prop, mot)) {
				System.err.println("PERDU  - Le mot à trouver était: ");
				fr.eni.jse.bll.MethodesJeu.afficher(mot);
				if (perso.nom==null)perso.nom="";
				System.out.println("(" +perso.nom + " " +perso.particule + " " + perso.prenom + ")");
			} else {
				System.out.println("GAGNÉ  - Le mot à trouver était effectivement : ");
				fr.eni.jse.bll.MethodesJeu.afficher(mot);
				if (perso.nom==null)perso.nom="";
				System.out.println("("+ perso.nom + " " +perso.particule + " " + perso.prenom + ")");
				System.out.println("+10 points!");
				score = (score + 10);
			}

			// 10 - Affichage du score
			System.out.println("Score : " + score);

			System.out.println("APPUYEZ SUR UN BOUTON POUR CONTINUER");
			String prop5 = console.nextLine();
			fr.eni.jse.bll.MethodesJeu.prochainQuizz(prop5);
			
			//Nouvelle épreuve
			if (perso.surnom != null) {
			String [] tableauDesPropositions = fr.eni.jse.bll.MethodesJeu.questionSurnom(perso);
			int reponse = console.nextInt();
			int score2 = fr.eni.jse.bll.MethodesJeu.reponseSurnom(tableauDesPropositions, score, perso.surnom, reponse);
			score = (score2);
			};
			
			// 11 - Relance d'un autre équipage
			Groupe groupePirate2 = fr.eni.jse.bll.MethodesJeu.tirageGroupe();
			// On pose la nouvelle question au joueur
			System.out.println("Ce personnage fait-il partie (ou a t'il fait partie) de " + groupePirate2.getNom() + " ?");
			System.out.println(" OUI (tapez 1) NON (tapez 2) ");
			int prop2 = console.nextInt();

			// On regarde que le personnage est bien dans le tableau de l'équipage proposé
			boolean confirmation = fr.eni.jse.bll.MethodesJeu.scanPerso (perso.nomusuel, groupePirate2);
			// On répond au joueur en fonction de sa réponse. 
			int score3 = fr.eni.jse.bll.MethodesJeu.questionAlignement(confirmation, prop2, score, groupePirate2.getNom(), groupePirate.getNom());
			score = (score3);
			
			// 15 - Recherche un nouveau personnage dans la base de donnée
			Groupe groupePirate3 = fr.eni.jse.bll.MethodesJeu.tirageGroupe();
			Personnage perso2 = fr.eni.jse.bll.MethodesJeu.tirerPersoAleatoirement(groupePirate3);

			// 15,5 - Verification. Si l'age du personnage est inconnue on retire un nouveau personnage
			if (perso2.age == 0){Groupe groupePirate4 = fr.eni.jse.bll.MethodesJeu.tirageGroupe();
				perso2 = fr.eni.jse.bll.MethodesJeu.tirerPersoAleatoirement(groupePirate4);
			}

			// 16 - On demande si le personnage initial est plus jeune ou plus vieux que le nouveau personnage
			if (perso.age > 0) {
			System.out.println(perso.nomusuel + " est-il (ou elle) plus jeune ou plus agé(e) que " + perso2.nomusuel + " ?");
			System.out.println("plus jeune (tapez 1) ");
			System.out.println("plus vieux/vieille (tapez 2) ");
			System.out.println("Ces deux persos ont le même âge (tapez 3) ");
			int prop3 = console.nextInt();

			int score4 = fr.eni.jse.bll.MethodesJeu.reponseAge(prop3, perso.age, perso2.age, perso.nomusuel, perso2.nomusuel, score);
			score = (score4);
				};

			// Affichage du score
			System.out.println("Score : " + score);
			
			// Relance de la prochain quizz
			String prop4 = console.nextLine();
			fr.eni.jse.bll.MethodesJeu.prochainQuizz(prop4);

			if (perso.prime > 0) {
				Personnage perso3 = fr.eni.jse.bll.MethodesJeu.questionPrime(perso);
				int prop6 = console.nextInt();
				int score5 = fr.eni.jse.bll.MethodesJeu.reponsePrime(perso, perso3, score, prop6);
				score = (score5);
				};
				
			System.out.println("---------------------------");
			System.out.println("APPUYEZ SUR UN BOUTON POUR CONTINUER");
			String prop7 = console.nextLine();
			fr.eni.jse.bll.MethodesJeu.prochainQuizz(prop4);


		}
		// Fermeture de la classe Scanner
		console.close();
		// Révélation du score total
		System.out.println("---------------------------");
		System.out.println("Fin de la partie");
		System.out.println("Ton score total est de " + score + " points");
	} */


    }



