package fr.eni.onepiecev4.ihm;

import fr.eni.onepiecev4.bll.GroupeService;
import fr.eni.onepiecev4.bll.PersonnageService;
import fr.eni.onepiecev4.bo.Groupe;
import fr.eni.onepiecev4.bo.Personnage;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/groupes")
@Controller
@SessionAttributes({"listePersonnages"})
public class GroupeController {

    public GroupeService groupeService;

    public PersonnageService personnageService;

    public GroupeController(GroupeService groupeService, PersonnageService personnageService) {
        this.groupeService = groupeService;
        this.personnageService = personnageService;
    }

    @ModelAttribute("listePersonnages")
    public List<Personnage> getListePersonnages() {
        return personnageService.consulterPersonnageListe();
    }

    @GetMapping
    public String groupe_liste(Model model) {
        List<Groupe> list = groupeService.consulterGroupeList();
        model.addAttribute("groupeLst", list);

        int groupeListSize = list.size();
        model.addAttribute("groupeListSize", groupeListSize);

        return "liste_groupes";
    }

    @GetMapping("/creer")
    public String creerGroupe(Model model) {
        model.addAttribute("groupe", new Groupe());

        return "creer_groupe";
    }

    @PostMapping("/creer")
    //ajout de @Valid et du bindingResult
    /// !!!!!!! doit se placer après l'élément que l'on teste
    public String ajouterGroupe(@Valid @ModelAttribute("groupe") Groupe groupe,
                                BindingResult bindingResult,
                                Model model) {
        //retraitement d'éventuelles erreurs
        if (bindingResult.hasErrors()) {
            Integer id = groupe.getId();
            model.addAttribute("groupe", groupeService.consulterGroupeParId(id));
            //on relance la page de creation avec les erreurs
            return "creer_groupe";
        }
        this.groupeService.creerGroupe(groupe);

        return "redirect:/groupes";
    }

    @GetMapping("/details")
    public String afficherDetails(@RequestParam("idGroupe") Integer id, Model model) {
        Groupe groupe = this.groupeService.consulterGroupeParId(id);
        model.addAttribute("groupe", groupe);

        List<Personnage> membreList = this.groupeService.groupeAfficheListMembre(groupe);
        model.addAttribute("membres", membreList);

        return "detail_groupe";
    }

    @GetMapping("/miseajour")
    public String afficherDetailsmiseajourGroupe(@RequestParam("idGroupe") Integer id, Model model) {
        Groupe groupe = this.groupeService.consulterGroupeParId(id);
        model.addAttribute("groupe", groupe);

        List<Personnage> membreList = this.groupeService.groupeAfficheListMembre(groupe);
        model.addAttribute("membres", membreList);
        System.out.println(groupe);

        return "modifier_groupe";
    }

    @PostMapping("/miseajour")
    public String miseajourGroupe(@ModelAttribute("groupe") Groupe groupe,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            Integer id = groupe.getId();
            model.addAttribute("groupe", groupeService.consulterGroupeParId(id));

            return "modifier_groupe";
        }

        this.groupeService.mettreajourGroupe(groupe);

        // création d'une liste de membres pour l'affichage du détail
        List<Personnage> membreList = this.groupeService.groupeAfficheListMembre(groupe);
        model.addAttribute("membres", membreList);
        return "detail_groupe";

    }

    @GetMapping("/miseajour_equipage")
    public String afficherDetailsmiseajourEquipage(@RequestParam("idGroupe") Integer id, Model model) {
        Groupe groupe = groupeService.consulterGroupeParId(id);
        model.addAttribute("groupe", groupe);

        List<Personnage> membreList = this.groupeService.groupeAfficheListMembre(groupe);
        model.addAttribute("membres", membreList);

        return "modifier_attribution_equipage";
    }

    @PostMapping("/miseajour_equipage")
    public String miseajourEquipage(@ModelAttribute("groupe") Groupe groupe,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            Integer id = groupe.getId();
            model.addAttribute("groupe", groupeService.consulterGroupeParId(id));

            return "modifier_attribution_equipage";
        }

        System.out.println(groupe.getMembreEnPlus());
        this.groupeService.ajouterPersonnageGroupe(groupe, groupe.getMembreEnPlus());
        return "redirect:/groupes/miseajour?idGroupe="+groupe.getId();
    }

    @GetMapping("/suppr_perso_equipage")
    public String PageSupprPersoEquipage(@RequestParam("idGroupe") Groupe groupe,
                                         @RequestParam("idPerso") Personnage personnage) {

        this.groupeService.supprimerPersonnageGroupe(groupe, personnage);

        return "redirect:/groupes/miseajour?idGroupe=" + groupe.getId();

    }


    @GetMapping("/suppr_groupe")
    public String PageSupprGroupe(@RequestParam("idGroupe") Integer id,
                                 Model model) {

        model.addAttribute("groupe", groupeService.consulterGroupeParId(id));

        return "delete_groupe";

    }

    @PostMapping("/suppr_groupe")
    public String PageSupprGroupeValider(@ModelAttribute("groupe") Groupe groupe) {

        System.out.println("id groupe suppr" + groupe);

        groupeService.effacerGroupe(groupe);

        return "redirect:/groupes" ;

    }




}
