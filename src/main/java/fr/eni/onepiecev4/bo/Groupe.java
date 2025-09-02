package fr.eni.onepiecev4.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class Groupe {
    private Integer id;

    @NotBlank
    @Size(max = 200)
    private String nom;

    private Personnage capitaine;
    private List<Personnage> membresListe;

    private Personnage membreEnPlus;

    /**
     * Constructeurs
     */

    public Groupe() {
    }

    public Groupe(String nom, List<Personnage> membresListe) {
        this.nom = nom;
        this.capitaine = capitaine;
        this.membresListe = membresListe;
    }

    public Groupe(Integer id, String nom, List<Personnage> membresListe) {
        this.id = id;
        this.nom = nom;
        this.membresListe = membresListe;
    }

    public Groupe(String nom, Personnage capitaine, List<Personnage> membresListe) {
        this.nom = nom;
        this.capitaine = capitaine;
        this.membresListe = membresListe;
    }

    public Groupe(Integer id, String nom, Personnage capitaine, List<Personnage> membresListe) {
        this.id = id;
        this.nom = nom;
        this.capitaine = capitaine;
        this.membresListe = membresListe;
    }

/**

    /**
     * Getter pour nom.
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * Setter pour nom.
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Personnage> getMembresListe() {
        return membresListe;
    }

    public void setMembresListe(List<Personnage> membresListe) {
        this.membresListe = membresListe;
    }

    /**
     * Getter pour id.
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter pour id.
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Personnage getCapitaine() {
        return capitaine;
    }

    public void setCapitaine(Personnage capitaine) {
        this.capitaine = capitaine;
    }

    public Personnage getMembreEnPlus() {
        return membreEnPlus;
    }

    public void setMembreEnPlus(Personnage membreEnPlus) {
        this.membreEnPlus = membreEnPlus;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Groupe{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", capitaine=").append(capitaine);
        //sb.append(", membresListe=").append(membresListe);
        sb.append(", membresListe=").append(membresListe);
        sb.append(", membreEnPlus=").append(membreEnPlus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Groupe groupe = (Groupe) o;
        return Objects.equals(id, groupe.id) && Objects.equals(membresListe, groupe.membresListe) && Objects.equals(nom, groupe.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, membresListe, nom);
    }
}
