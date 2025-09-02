package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupeService {

    List<Groupe> consulterGroupeList();

    Groupe consulterGroupeParId(Integer id);

    void mettreajourGroupe (Groupe groupe);

    void creerGroupe(Groupe groupe);

    void effacerGroupe(Groupe groupe);

    void ajouterPersonnageGroupe(Groupe groupe, Personnage personnage);

    void supprimerPersonnageGroupe(Groupe groupe, Personnage personnage);

    List<Personnage> groupeAfficheListMembre(Groupe groupe);
}
