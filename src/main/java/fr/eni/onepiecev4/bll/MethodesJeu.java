package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.stereotype.Service;

@Service
public interface MethodesJeu {



    Groupe tirageGroupe();

    Personnage tiragePersonnage();

    String nomComplet (Personnage personnage);

    char[] melanger(char[] mot);

    Personnage tiragePersonnageAvecPseudo();

    Personnage tiragePersonnageAvecAge();

    Personnage tiragePersonnageAvecPrime();

    char[] nomemclature(String nomusuel);

    String jeuDesLettres(String reponseNom, Personnage personnage);

    Integer calculduScore(Integer score, String resultat);

    Integer calculduScore2(String resultat);

    String[] questionSurnom(Personnage perso);

    String reponseSurnom(String reponsePseudo, Personnage personnage);

    String reponseEquipage(String reponse, Personnage persoChoisi, Groupe groupeChoisi);

    String AffichageReponseJeuEquipage(String resultat, String reponse, Personnage persoChoisi, Groupe groupeChoisi);

    String reponseAge(String reponse, Personnage persoPrincipal, Personnage persoSecondaire);

    String AffichageReponseJeuAge(String resultat, Personnage persoPrincipal, Personnage persoSecondaire);

    String reponsePrime(String reponse, Personnage persoPrincipal, Personnage persoSecondaire);

    String AffichageReponseJeuPrime(String resultat, Personnage persoPrincipal, Personnage persoSecondaire);

    Integer calculerscorefinal(float score, float total);

}


