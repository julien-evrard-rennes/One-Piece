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

    public static Personnage luffy = new Personnage(1,"Luffy", "Monkey", "au chapeau de paille", 'D', 'H', 19, 3000000000L, LocalDate.of(1996,12,05));
    private static	Personnage zoro = new Personnage(2,"Zoro","Roronoa", "le chasseur de pirates",' ','H', 21, 1111000000L);
    private static	Personnage nami = new Personnage(3,"Nami", "", "la voleuse",' ','F', 20, 366000000);
    private static	Personnage usopp = new Personnage(4,"Usopp", "", "sniperking", ' ','H', 20, 500000000);
    private static	Personnage sanji = new Personnage(5,"Sanji", "Vinsmoke", "la jambe noire", ' ', 'H', 21, 1032000000L);
    private static	Personnage chopper = new Personnage(6,"Chopper","Tony Tony","le fan de barbe à papa",' ','H',17, 1000);
    private static	Personnage robin = new Personnage(7,"Robin","Nico","la démone d'Ohara",' ','F',30, 930000000);
    private static	Personnage franky = new Personnage(8,"Franky","Cutty Flam", "le cyborg",' ','H', 36, 394000000);
    private static	Personnage brook = new Personnage(9,"Brook", "","Soul King",' ','H', 90, 383000000);
    private static	Personnage jinbe = new Personnage(10,"Jinbe", "", "le paladin des mers",' ','H',46, 1100000000);
    private static  Personnage vivi = new Personnage(11,"Vivi", "nefertari", "Miss Wednesday", 'D','F', 18,0);
    private static  Personnage bartolomeo = new Personnage(12,"Bartolomeo", "", "le cannibale",' ','H', 24, 200000000);

    public static Personnage shanks = new Personnage(13,"Shanks", "", "le roux",' ','H', 39, 4048900000L);
    private static 	Personnage beckmann = new Personnage(14,"Beckmann", "Ben","",' ','H', 50,0);
    private static 	Personnage yasopp = new Personnage(15,"Yasopp", "", "le chasseur",' ','H', 47,0);
    private static 	Personnage uta = new Personnage(16,"Uta","", "la plus grande cantatrice du monde",' ','F',21,0);

    public static 	Personnage barbeblanche = new Personnage(17,"Newgate","Edward", "Barbe Blanche", ' ', 'H', 72, 5046000000L);
    private static 	Personnage marco = new Personnage(18,"Marco","","Le Phénix",' ','H',45,1374000000);
    private static Personnage ace = new Personnage(19,"Ace", "Portgas", "aux poings ardents",'D','H', 22, 550000000L);
    private static 	Personnage oden = new Personnage(20,"Oden","Kozuki","",' ','H',39,0);
    private static 	Personnage izou = new Personnage(21,"Izou","","le samouraï",' ','H',45, 510000000L);
    private static 	Personnage barbenoire = new Personnage(22,"Teach","Marshall", "Barbe Noire",'D','H',40,3996000000L);
    private static 	Personnage caborage = new Personnage(24, "Caborage","", "le roi de l'aurore",' ','H',40,0);
    private static 	Personnage chavipere = new Personnage(25,"Chavipère","","le roi du crépuscule",' ','H',40,0);
    private static 	Personnage momonosuke = new Personnage(26,"Momonosuke","Kozuki","",' ','H',28,0);

    public static 	Personnage akainu = new Personnage(27,"Sakazuki","AkaInu", "Le chien rouge",' ','H',55, 5000000000L);
    private static 	Personnage borsalino = new Personnage(28,"Kizaru","Borsalino","le singe jaune",' ','H', 58, 3000000000L);
    private static 	Personnage issho = new Personnage(29,"Issho", "le tigre violet","",' ','H',54,3000000000L);
    private static 	Personnage garp = new Personnage(30,"Garp", "Monkey", "le héros de la Marine",'D','H', 78,3000000000L);
    private static 	Personnage smoker = new Personnage(31,"Smoker","","le chasseur blanc",' ','H',36,0);
    private static 	Personnage kobby = new Personnage(32,"Kobby","","",' ','H', 18, 500000000);
    private static 	Personnage hermep = new Personnage(33,"Hermep","","",' ','H',22, 100000000);
    private static 	Personnage tashigi = new Personnage(34,"Tashigi","","",' ','F',23,0);
    private static 	Personnage kuzan = new Personnage(35,"aokiji","Kuzan", "le faisan bleu",' ','H',49,0);

    private static 	Personnage pudding = new Personnage(36,"Pudding","Charlotte","le monstre",' ','F',16,0);
    public static 	Personnage bigmom = new Personnage(37,"Linlin","Charlotte","Big Mom",' ','F',68,4388000000L);
    private static 	Personnage smoothie = new Personnage(38,"Smoothie","Charlotte","",' ','F',35,932000000L);
    private static 	Personnage slurp = new Personnage(39,"Slurp","Charlotte","",' ','H',50,700000000);
    private static 	Personnage daifuku = new Personnage(40,"Daifuku","Charlotte","",' ','H',48,300000000);
    private static 	Personnage brulee = new Personnage(41,"Brûlée","Charlotte","",' ','F',43,0);
    private static 	Personnage pekoms = new Personnage(42,"Pekoms","","",' ','H',27,330000000);

    public static 	Personnage law = new Personnage(43,"law","Trafalgar", "le chirurgien de la mort", 'D','H', 26,3000000000L);
    private static 	Personnage hanafuda = new Personnage(44,"Hanafuda","",'H');
    private static 	Personnage weevil = new Personnage(45,"Weevil","Edward","Barbe Blanche Jr.",' ','H',35,480000000);
    private static 	Personnage crocodile = new Personnage(46,"Crocodile","","Mr 0",' ','H',46, 1965000000);
    private static 	Personnage doflamingo = new Personnage(47,"Doflamingo", "Don Quichotte","Joker",' ','H',41,340000000);
    private static 	Personnage moria = new Personnage(48,"Moria", "Gecko", "",' ','H',50, 320000000);
    private static 	Personnage mihawk = new Personnage(49,"Mihawk", "Dracule","Oeil de Faucon", ' ','H',43, 3590000000L);
    private static 	Personnage hancock = new Personnage(50,"Hancock", "Boa", "la princesse serpent",' ','F',31,1659000000);
    private static 	Personnage kuma = new Personnage(51,"Kuma", "Bartolomew", "le tyran",' ','H',47,296000000);
    private static 	Personnage baggy = new Personnage(52,"Baggy","", "le clown",' ','H',39,3189000000L);

    public static 	Personnage kaido = new Personnage(53,"Kaido","","le roi dragon",' ','H',59,4611100000L);
    private static 	Personnage king = new Personnage(54,"King", "Alber","l'incendie",' ','H',47,1390000000);
    private static 	Personnage queen = new Personnage(55,"Queen", "Scien","la pandémie",' ','H',56,1320000000);
    private static 	Personnage jack = new Personnage(56,"Jack","","la sécheresse",' ','H',28,1000000000);
    private static 	Personnage ulti = new Personnage(57,"Ulti","","",' ','F',22, 400000000);
    private static 	Personnage pageone = new Personnage(58,"Page One","","",' ','H',20, 290000000);
    private static 	Personnage blackmaria = new Personnage(59,"Black Maria","","",' ','F',29,480000000);
    private static 	Personnage hawkins = new Personnage(60,"hawkins", "Basil","le sorcier",' ','H',31,320000000);
    private static 	Personnage apoo = new Personnage(61,"Apoo", "Scratchmen","le mugissant",' ','H',31,350000000);
    private static 	Personnage yamato = new Personnage(62,"Yamato","", "Oden Kozuki",' ','H',28,0);

    private static 	Personnage crocus = new Personnage(63,"Crocus","","",' ','H',73,0);
    private static 	Personnage rayleigh = new Personnage(64,"Rayleigh", "Silvers", "le bras droit du roi des pirates",' ','H',78,0);
    private static 	Personnage roger = new Personnage(65,"Roger", "Gol", "le roi des pirates", 'D','H', 53, 5564800000L);
    private static 	Personnage gaban = new Personnage(66,"Gaban", "Scoper", "le bras gauche du roi des pirates",' ','H', 79,0);

    private static 	Personnage kid = new Personnage(67,"Kid", "Eustass","captain",' ','H',23,3000000000L);
    private static 	Personnage drake = new Personnage(68,"Drake", "X","le drapeau rouge",' ','H',33,222000000);
    private static 	Personnage urouge = new Personnage(69,"Urouge","","le moine fou",' ','H',47,108000000);
    private static 	Personnage killer = new Personnage(70,"Killer","", "le massacreur",' ','H',27,200000000);
    private static 	Personnage bonney = new Personnage(71,"Bonney", "Jewelry","la gloutonne",' ','F',12,320000000);
    private static 	Personnage bege = new Personnage(72,"Bege", "Capone","gang",' ','H',42,350000000);

    private static 	Personnage bepo = new Personnage(73,"Bepo","","",' ','H', 22,1500);
    private static 	Personnage jeanbart = new Personnage(74,"Bart", "Jean",'H');
    private static 	Personnage penguin = new Personnage(75,"Penguin","","",' ','H',28,0);
    public static 	Personnage ikkaku = new Personnage(76,"Ikkaku","",'F');

    // private String[] A RAJOUTER { carrot, bartolomeo, karoo };


    private static final Personnage[] NAKAMAS = { luffy, zoro, nami, chopper, usopp, sanji, robin, franky, brook, jinbe, vivi, bartolomeo };
    private static final Personnage[] SHANKS = { shanks, beckmann, yasopp, uta };
    private static final Personnage[] BIGMOM = { bigmom, smoothie, daifuku, brulee, pekoms, slurp, pudding, bege };
    private static final Personnage[] BARBEBLANCHE = { barbeblanche, marco, ace, oden, barbenoire, caborage, chavipere, momonosuke, izou };
    private static final Personnage[] MARINE = { garp, akainu, borsalino, issho, kuzan, kobby, hermep, smoker, tashigi, drake };
    private static final Personnage[] CORSAIRES = { law, jinbe, weevil, crocodile, doflamingo, moria, mihawk, hancock, kuma, barbenoire, baggy, hanafuda };
    private static final Personnage[] KAIDO = { kaido, queen, jack, king, ulti, pageone, blackmaria, yamato, drake, hawkins, apoo };
    private static final Personnage[] GOLDROGER = { shanks, baggy, oden, crocus, roger, rayleigh, caborage, chavipere, momonosuke, gaban };
    private static final Personnage[] PIREGENERATION = { kid, luffy , hawkins , drake , law , apoo , killer , bonney , barbenoire, bege ,zoro, urouge};
    private static final Personnage[] LAW = { law, bepo, jeanbart, penguin, ikkaku };

    public static List<Personnage> equipageNakamas = Arrays.asList(NAKAMAS);
    public static List<Personnage> equipageShanks = Arrays.asList(SHANKS);
    public static List<Personnage> equipageBigmom = Arrays.asList(BIGMOM);
    public static List<Personnage> equipageBarbeBlanche = Arrays.asList(BARBEBLANCHE);
    public static List<Personnage> marine = Arrays.asList(MARINE);
    public static List<Personnage> corsaires = Arrays.asList(CORSAIRES);
    public static List<Personnage> equipageKaido = Arrays.asList(KAIDO);
    public static List<Personnage> equipageGoldRoger = Arrays.asList(GOLDROGER);
    public static List<Personnage> piregeneration = Arrays.asList(PIREGENERATION);
    public static List<Personnage> equipageLaw = Arrays.asList(LAW);

    private static List<Personnage> personnageList = new ArrayList<>();

    private static final Personnage[] CAPITAINES = {luffy, barbeblanche, law, bigmom, akainu, shanks, kaido };
    public static List<Personnage> lstcapitaine = Arrays.asList(CAPITAINES);
    public PersonnageServiceImpl() {
        simulationCoucheDALetDB();
    }


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
        List list = personnageDAO.consulterListPersonnagesAvecPseudo();
        return personnageDAO.consulterListPersonnagesAvecPseudo();
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
