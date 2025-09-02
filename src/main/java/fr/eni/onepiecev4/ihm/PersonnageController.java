package fr.eni.onepiecev4.ihm;

import fr.eni.onepiecev4.bll.GroupeService;
import fr.eni.onepiecev4.bll.PersonnageService;
import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import jakarta.validation.Valid;
import org.apache.catalina.Engine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/personnages")
@Controller
@SessionAttributes({"listeGroupes","listeSexes"})
public class PersonnageController {

    private final GroupeService groupeService;
    public PersonnageService personnageService;

    public PersonnageController(PersonnageService personnageService, GroupeService groupeService) {
        this.personnageService = personnageService;
        this.groupeService = groupeService;
    }

    @ModelAttribute("listeGroupes")
    public List<Groupe> getListeGroupes() { return groupeService.consulterGroupeList();
    }

    @ModelAttribute("ListeSexes")
    public List<String> getListeSexes() { return personnageService.personnageSexeList();
    }

    @GetMapping
    public String personnage_liste(Model model) {
        List<Personnage> list = personnageService.consulterPersonnageListe();
        model.addAttribute("persoLst", list);

        int persolistSize = list.size();
        model.addAttribute("persoListSize", persolistSize);

        return "liste_personnages";
    }

    @GetMapping("/creer")
    public String creerPerso(Model model) {
        model.addAttribute("personnage", new Personnage());

        return "creer_personnage";
    }

    @PostMapping( "/creer")
    //ajout de @Valid et du bindingResult
    /// !!!!!!! doit se placer après l'élément que l'on test
    public String ajouterPerso(@Valid @ModelAttribute("personnage") Personnage personnage,
                                BindingResult bindingResult,
                                Model model) {
        //traitement d'éventuelles erreurs
        if (bindingResult.hasErrors()) {
            return "creer_personnage";
        }

        // Création des particules en fonction des cases cochées
        if(personnage.getParticuleCheck() == true){
            personnage.setParticule('D');
        }
        else {
            personnage.setParticule(' ');
        }

        // Met le sexe en fonction de la radio-bouton
        if (personnage.getGenreCheck().equals("Femme")) {
            personnage.setSexe('F');
        }
        if (personnage.getGenreCheck().equals("Homme"))  {
            personnage.setSexe('H');
        }
        if (personnage.getGenreCheck().equals("Autre")) {
            personnage.setSexe('?');
        }

        this.personnageService.creerPersonnage(personnage);
        System.out.println(personnage);

        // Met les fiches en place dans la page de détail
        String fichePrime = personnageService.personnagePrimeAffiche(personnage);
        model.addAttribute("fichePrime", fichePrime);
        String ficheSexe = personnageService.personnageSexeAffiche(personnage);
        model.addAttribute("ficheSexe", ficheSexe);
        String ficheAge = personnageService.personnageAgeAffiche(personnage);
        model.addAttribute("ficheAge", ficheAge);

        return "detail_personnage";

    }

    @GetMapping("/details")
    public String afficherDetails(@RequestParam("idPerso")long id, Model model){
        Personnage personnage = this.personnageService.consulterPersonnageParId(id);
        model.addAttribute("personnage", personnage);

        // Mets les fiches en place dans la fiche personnage
        String fichePrime = this.personnageService.personnagePrimeAffiche(personnage);
        model.addAttribute("fichePrime", fichePrime);
        String ficheSexe = this.personnageService.personnageSexeAffiche(personnage);
        model.addAttribute("ficheSexe", ficheSexe);
        String ficheAge = this.personnageService.personnageAgeAffiche(personnage);
        model.addAttribute("ficheAge", ficheAge);
        List<Groupe> groupeList = this.personnageService.personnageAfficheListEquipage(personnage);
        model.addAttribute("equipages", groupeList);

        return "detail_personnage";
    }

    @GetMapping("/miseajour")
    public String afficherDetailsPerso(@RequestParam("idPerso")long id, Model model){

        Personnage personnage = this.personnageService.consulterPersonnageParId(id);
        model.addAttribute("personnage", personnage);

        if(personnage.getSexe() == 'H'){
            personnage.setGenreCheck("Homme");
        }
        else if(personnage.getSexe() == 'F'){
            personnage.setGenreCheck("Femme");
        }
        else{
            personnage.setGenreCheck("Autre");
        }


        // Fait en sorte que la coche des personnages soit checké
        if(personnage.getParticule() == 'D'){
            personnage.setParticuleCheck(true);
        }
        else{
        personnage.setParticuleCheck(false);}

        List<Groupe> groupeList = this.personnageService.personnageAfficheListEquipage(personnage);
        model.addAttribute("groupelist", groupeList);

        return "modifier_personnage";
    }

    @PostMapping("/miseajour")
    public String miseajourPerso(@Valid @ModelAttribute("personnage") Personnage personnage,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "modifier_personnage";
        }

        // Met la particule si la particule est cochée
        if(personnage.getParticuleCheck() == true){
            personnage.setParticule('D');
        }
        else {
            personnage.setParticule(' ');
        }

        // Met le sexe en fonction de la radio-bouton
        if (personnage.getGenreCheck().equals("Femme")) {
            personnage.setSexe('F');
        }
        if (personnage.getGenreCheck().equals("Homme"))  {
            personnage.setSexe('H');
        }
        if (personnage.getGenreCheck().equals("Autre")) {
            personnage.setSexe('?');
        }

        this.personnageService.mettreajourPersonnage(personnage);

        // Met les fiches en place dans la page de détail
        String fichePrime = this.personnageService.personnagePrimeAffiche(personnage);
        model.addAttribute("fichePrime", fichePrime);
        String ficheSexe = this.personnageService.personnageSexeAffiche(personnage);
        model.addAttribute("ficheSexe", ficheSexe);
        String ficheAge = this.personnageService.personnageAgeAffiche(personnage);
        model.addAttribute("ficheAge", ficheAge);

        return "detail_personnage";

    }

    @GetMapping("/miseajour_attribution")
    public String afficherDetailsmiseajourAttribution(@RequestParam("idPerso") long id, Model model) {
        Personnage personnage = personnageService.consulterPersonnageParId(id)  ;
        model.addAttribute("personnage", personnage);

        List<Groupe> groupeList = this.personnageService.personnageAfficheListEquipage(personnage);
        model.addAttribute("groupelist", groupeList);

        return "modifier_attribution_groupe";
    }

    @PostMapping("/miseajour_attribution")
    public String miseajourAttribution(@ModelAttribute("personnage") Personnage personnage,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            long id = personnage.getId();
            model.addAttribute("personnage", personnageService.consulterPersonnageParId(id));
            return "modifier_attribution_groupe";
        }

        System.out.println(personnage.getGroupeEnPlus());
        this.groupeService.ajouterPersonnageGroupe (personnage.getGroupeEnPlus(), personnage);
        return "redirect:/personnages/miseajour?idPerso="+personnage.getId();
    }

    @GetMapping("/suppr_perso_equipage")
    public String PageSupprPersoEquipage(@RequestParam("idGroupe") Groupe groupe,
                                         @RequestParam("idPerso") Personnage personnage) {

        this.groupeService.supprimerPersonnageGroupe(groupe, personnage);

        return "redirect:/personnages/miseajour?idPerso=" + personnage.getId();

    }

    @GetMapping("/suppr_personnage")
    public String PageSupprPerso(@RequestParam("idPerso") long id,
                                   Model model) {

        model.addAttribute("personnage", personnageService.consulterPersonnageParId(id));

        return "delete_personnage";

    }

    @PostMapping("/suppr_personnage")
    public String PageSupprPersoValider(@ModelAttribute("personnage") Personnage personnage) {

        personnageService.effacerPersonnage(personnage);

        return "redirect:/personnages" ;

    }


}
