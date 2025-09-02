package fr.eni.onepiecev4.dal;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;

import java.util.List;

public interface GroupeDAO {

    List<Groupe> consulterGroupeList();

    Groupe consulterGroupeParId(Integer id);

    void mettreajourGroupe (Groupe groupe);

    void creerGroupe(Groupe groupe);

    void effacerGroupe(Groupe groupe);

    void ajouterPersonnageGroupe(Groupe groupe, Personnage personnage);

    void supprimerPersonnageGroupe(Groupe groupe, Personnage personnage);

    List<Personnage> consulterListMembre(Groupe groupe, Groupe groupe1);
}
