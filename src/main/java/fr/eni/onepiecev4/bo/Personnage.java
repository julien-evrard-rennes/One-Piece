package fr.eni.onepiecev4.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Personnage {
    private long id;

    @NotBlank
    @Size(max = 100)
    private String prenom;

    @Size(max = 100)
    private String nom;

    @Size(max = 150)
    private String surnom;
    private char particule;

    private char sexe;
    @Min(0)
    private int age;
    @Min(0)
    private long prime;
    private Boolean particuleCheck;
    private String genreCheck;

    List<Groupe> groupeList = new ArrayList<Groupe>();
    private Groupe groupeEnPlus;


    public Personnage() {
    }

    public Personnage(long id, String prenom, String nom, char sexe) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
    }

    public Personnage(long id, String prenom, String nom, String surnom, char particule, char sexe, int age, long prime) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.surnom = surnom;
        this.particule = particule;
        this.sexe = sexe;
        this.age = age;
        this.prime = prime;
    }


    public Personnage(String prenom, String nom, String surnom, char particule, char sexe, int age, long prime) {
        this.prenom = prenom;
        this.nom = nom;
        this.surnom = surnom;
        this.particule = particule;
        this.sexe = sexe;
        this.age = age;
        this.prime = prime;
    }

    public Personnage(String prenom, String nom, String surnom, char particule, char sexe, int age, long prime, List<Groupe> groupeList) {
        this.prenom = prenom;
        this.nom = nom;
        this.surnom = surnom;
        this.particule = particule;
        this.sexe = sexe;
        this.age = age;
        this.prime = prime;
        this.groupeList = groupeList;
    }

    public Personnage(List<Groupe> groupeList, long prime, int age, char sexe, char particule, String surnom, String nom, String prenom, long id) {
        this.groupeList = groupeList;
        this.prime = prime;
        this.age = age;
        this.sexe = sexe;
        this.particule = particule;
        this.surnom = surnom;
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public char getParticule() {
        if (particule != ' ') {return particule;}
        else return ' ';
    }

    public void setParticule(char particule) {
        this.particule = particule;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public long getPrime() {
        return prime;
    }

    public void setPrime(long prime) {
        this.prime = prime;
    }

    public List<Groupe> getGroupeList() {
        return groupeList;
    }

    public void setGroupeList(List<Groupe> groupeList) {
        this.groupeList = groupeList;
    }

    public Boolean getParticuleCheck() {
        return particuleCheck;
    }

    public void setParticuleCheck(Boolean particuleCheck) {
        this.particuleCheck = particuleCheck;
    }

    public String getGenreCheck() {
        return genreCheck;
    }

    public void setGenreCheck(String genreCheck) {
        this.genreCheck = genreCheck;
    }

    public Groupe getGroupeEnPlus() {
        return groupeEnPlus;
    }

    public void setGroupeEnPlus(Groupe groupeEnPlus) {
        this.groupeEnPlus = groupeEnPlus;
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", surnom='" + surnom + '\'' +
                ", particule=" + particule +
                ", sexe=" + sexe +
                ", age=" + age +
                ", prime=" + prime +
                ", groupeList=" + groupeList +
                ", genreCheck='" + genreCheck + '\'' +
                ", groupeEnPlus=" + groupeEnPlus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personnage that = (Personnage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


