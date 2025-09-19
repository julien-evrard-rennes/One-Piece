package fr.eni.onepiecev4.bll;

import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import fr.eni.onepiecev4.dal.PersonnageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnageServiceImpl implements PersonnageService {

    private PersonnageDAO personnageDAO;

    /* private int indexPersonnages = 77;


    public void simulationCoucheDALetDB() {
        personnageList.addAll(equipageNakamas);
        personnageList.addAll(equipageShanks);
        personnageList.addAll(equipageBigmom);
        personnageList.addAll(equipageBarbeBlanche);
        personnageList.addAll(marine);
        personnageList.addAll(corsaires);
        personnageList.addAll(equipageKaido);
        personnageList.addAll(equipageGoldRoger);
        personnageList.addAll(piregeneration);
        personnageList.addAll(equipageLaw);
    } */

    @Autowired
    public PersonnageServiceImpl(PersonnageDAO personnageDAO) {
        this.personnageDAO = personnageDAO;
    }

    @Override
    public List<Personnage> consulterPersonnageListe(){
        return personnageDAO.consulterListPersonnages();
    }

    @Override
    public List<Personnage> consulterListePersonnagesAvecPseudo() {
        return personnageDAO.consulterListPersonnagesAvecPseudo();
    }

    @Override
    public List<Personnage> consulterListePersonnagesAvecPrime() {
        return personnageDAO.consulterListPersonnagesAvecPrime();
    }

    @Override
    public List<Personnage> consulterListePersonnagesAvecAge() {
        return personnageDAO.consulterListPersonnagesAvecAge();
    }


    @Override
    public Personnage consulterPersonnageParId(long id) {
        Personnage personnagebll = personnageDAO.consulterPersonnageParId(id);
        return personnagebll;
    }

    @Override
    public void creerPersonnage(Personnage personnage){
        if ((personnage.getParticule())== '?'){ personnage.setParticule(' ');}
        personnageDAO.creerPersonnage(personnage);
    }

    @Override
    public void effacerPersonnage(Personnage personnage) {
        personnageDAO.effacerPersonnage(personnage);
    }

    @Override
    public void mettreajourPersonnage(Personnage personnage){
        personnageDAO.mettreajourPersonnage(personnage);
    }

    @Override
    public String personnagePrimeAffiche(Personnage personnage) {
        float prime = personnage.getPrime();

        if (prime>0) {
            if (prime/2000>1){
                if(prime/1000000>1){
                    if(prime/1000000000>1){
                        return prime/1000000000 + " milliards de berrys";
                    }
                  return prime/1000000 + " millions de berrys";
                }
            return prime/1000 + " milliers de berrys";
            }
            long primePropre =  (long)prime;
        return primePropre + " berrys";
        }
        return "Pas de prime";
    }

    @Override
    public String personnageSexeAffiche(Personnage personnage) {
        char sexe = personnage.getSexe();
        if (sexe=='H') return "Masculin";
        if (sexe=='F') return "Féminin";
        else return "Indéfini ou non-binaire";
    }

    @Override
    public String personnageAgeAffiche(Personnage personnage) {
        int age = personnage.getAge();
        if(age==0) return "AGE INCONNU";
        else return age+ " ans";
    }

    @Override
    public List<String> personnageSexeList() {
        List<String> sexeChoix = List.of("Homme","Femme","Autre");
        return sexeChoix;
    }

    @Override
    public List<Groupe> personnageAfficheListEquipage(Personnage personnage) {
        return personnageDAO.consulterListGroupes(personnage);
    }

    @Override
    public boolean verifierAppartenance(Personnage personnage, Groupe groupe){
        List groupes = personnageDAO.consulterListGroupes(personnage);
        if (groupes.contains(groupe)) {
            return true;
        }
        else {
            return false;
        }
    }


}
