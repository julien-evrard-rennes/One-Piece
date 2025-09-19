package fr.eni.onepiecev4.dal;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonnageDAO {

    List<Personnage> consulterListPersonnages();

    Personnage consulterPersonnageParId(long id);

    void creerPersonnage(Personnage personnage);

    void effacerPersonnage(Personnage personnage);

    void mettreajourPersonnage(Personnage personnage);

    List<Groupe> consulterListGroupes(Personnage personnage);

    List<Personnage> consulterListPersonnagesAvecPseudo();

    List<Personnage> consulterListPersonnagesAvecPrime();

    List<Personnage> consulterListPersonnagesAvecAge();
}
