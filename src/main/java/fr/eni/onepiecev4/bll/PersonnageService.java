package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonnageService {

    void creerPersonnage(Personnage personnage);

    List<Personnage> consulterPersonnageListe();

    Personnage consulterPersonnageParId(long id);

    void effacerPersonnage(Personnage personnage);

    void mettreajourPersonnage(Personnage personnage);

    String personnagePrimeAffiche (Personnage personnage);

    String personnageSexeAffiche (Personnage personnage);

    String personnageAgeAffiche (Personnage personnage);

    List<Groupe> personnageAfficheListEquipage (Personnage personnage);

    List<String> personnageSexeList ();

    List<Personnage> consulterListePersonnagesAvecPseudo();

    boolean verifierAppartenance(Personnage personnage, Groupe groupe);
}
