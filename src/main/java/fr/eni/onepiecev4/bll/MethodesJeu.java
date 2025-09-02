package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.stereotype.Service;

@Service
public interface MethodesJeu {

    Groupe tirageGroupe();

    Personnage tiragePersonnage();

    boolean bonnesLettres(char[] prop, char[] tirage);

    char[] melanger(char[] mot);

    Personnage tiragePersonnageAvecPseudo();

    char[] nomemclature(String nomusuel);

    String jeuDesLettres(String reponseNom, Personnage personnage);

    Integer calculduScore(Integer score, String resultat);

    Integer calculduScore2(String resultat);

    String[] questionSurnom(Personnage perso);

    String reponseSurnom(String reponsePseudo, Personnage personnage);
}


