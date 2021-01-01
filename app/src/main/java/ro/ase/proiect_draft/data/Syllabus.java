package ro.ase.proiect_draft.data;

import android.content.Context;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Syllabus implements Serializable, Indexable {

    private String id = UUID.randomUUID().toString();
    private int nr;
    private int capacitate;
    private String sef;
    private String tip;
    private String zi;
    private String ora;
    private String nume;
    private String durata;
    private String profesor;

//    // variable to hold context
////    private Context context;
////
//////save the context recieved via constructor in a local variable
////    public Syllabus(Context context){
////        this.context=context;
////    }


    @Override
    public String getId() {
        return id;
    }

    public Syllabus(int nr, int capacitate, String sef) {
        this.nr = nr;
        this.capacitate = capacitate;
        this.sef = sef;
    }

    public Syllabus(int nr, int capacitate, String sef, String tip, String zi, String ora, String nume, String durata, String profesor) {
        this.nr = nr;
        this.capacitate = capacitate;
        this.sef = sef;
        this.tip = tip;
        this.zi = zi;
        this.ora = ora;
        this.nume = nume;
        this.durata = durata;
        this.profesor = profesor;
    }

    public Syllabus(String tip, String zi, String ora) {
        this.tip = tip;
        this.zi = zi;
        this.ora = ora;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public String getSef() {
        return sef;
    }

    public void setSef(String sef) {
        this.sef = sef;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Syllabus{" +
                "nr=" + nr +
                ", capacitate=" + capacitate +
                ", sef='" + sef + '\'' +
                ", tip='" + tip + '\'' +
                ", zi='" + zi + '\'' +
                ", ora='" + ora + '\'' +
                ", nume='" + nume + '\'' +
                ", durata='" + durata + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }

    @java.lang.Override
    public String getDeleteQuery() {
        return null;
    }

    @java.lang.Override
    public String getInsertQuery() {
        return null;
    }
}