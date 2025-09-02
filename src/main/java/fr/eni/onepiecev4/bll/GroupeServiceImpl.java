package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import fr.eni.onepiecev4.dal.GroupeDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService{

    PersonnageService personnageService;

    private GroupeDAO groupeDAO;

    public GroupeServiceImpl(PersonnageService personnageService, GroupeDAO groupeDAO) {
        this.personnageService = personnageService;
        this.groupeDAO = groupeDAO;
    }

    /**

    private static Groupe NAKAMAS = new Groupe(1, "l'équipage du chapeau de paille", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageNakamas);
    private static Groupe SHANKS = new Groupe(2,"l'équipage de Shanks le roux", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageShanks);
    private static Groupe BARBEBLANCHE = new Groupe(3, "l'équipage de Barbe Blanche", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageBarbeBlanche);
    private static Groupe MARINE = new Groupe(4,  "la Marine", fr.eni.onepiecev4.bll.PersonnageServiceImpl.marine);
    private static Groupe CORSAIRES = new Groupe(5, "l'organisation des Capitaines Corsaires", fr.eni.onepiecev4.bll.PersonnageServiceImpl.corsaires);
    private static Groupe KAIDO = new Groupe(6, "l'équipage aux cents bêtes", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageKaido);
    private static Groupe BIGMOM = new Groupe(7, "l'équipage de Big Mom", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageBigmom );
    private static Groupe GOLDROGER = new Groupe(8, "l'équipage de Gol D. Roger", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageGoldRoger);
    private static Groupe PIREGENERATION = new Groupe(9, "la Pire Génération", fr.eni.onepiecev4.bll.PersonnageServiceImpl.piregeneration);
    private static Groupe LAW =  new Groupe(10, "l'équipage du Heart", fr.eni.onepiecev4.bll.PersonnageServiceImpl.equipageLaw);

    public static final Groupe[] tableauGroupe = {NAKAMAS, SHANKS, BARBEBLANCHE, MARINE, CORSAIRES, KAIDO,
            BIGMOM, GOLDROGER, PIREGENERATION, LAW };

    public static List<Groupe> lstGroupes = new ArrayList<>();

    public static int tailleGroupes = lstGroupes.size();

    private static int indexlstGroupes = 11;

    List<Personnage> lstCapitaines = new ArrayList<>();

    public GroupeServiceImpl() {
        simulationCoucheDALetDB();
    } **/


    @Override
    public List<Groupe> consulterGroupeList(){
        return groupeDAO.consulterGroupeList();
    }

    @Override
    public Groupe consulterGroupeParId(Integer id){
        return groupeDAO.consulterGroupeParId(id);
    }

    @Override
    public void creerGroupe(Groupe groupe){
        groupeDAO.creerGroupe(groupe);
    }

    @Override
    public void effacerGroupe(Groupe groupe) {
        groupeDAO.effacerGroupe(groupe);
    }

    @Override
    public void mettreajourGroupe(Groupe groupe) {
        groupeDAO.mettreajourGroupe(groupe);
    }

    @Override
    public void ajouterPersonnageGroupe(Groupe groupe, Personnage personnage) {
        groupeDAO.ajouterPersonnageGroupe(groupe, personnage);
    }

    @Override
    public void supprimerPersonnageGroupe(Groupe groupe, Personnage personnage){
        groupeDAO.supprimerPersonnageGroupe(groupe, personnage);
    }

    @Override
    public List<Personnage> groupeAfficheListMembre(Groupe groupe) {
        return groupeDAO.consulterListMembre(groupe, groupe);
    }
}
