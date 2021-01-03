package ro.ase.proiect_draft;

import java.io.Serializable;

public class Exam implements Serializable {
    String idExam;
    String numeMaterie;
    int numarCredite;
    String tipExam;
    String dataSustinere;
    String ora;
    int durataOre;

    public Exam(String idExam, String numeMaterie, int numarCredite, String tipExam, String dataSustinere, String ora, int durataOre) {
        this.idExam = idExam;
        this.numeMaterie = numeMaterie;
        this.numarCredite = numarCredite;
        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
    }

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }

    public String getNumeMaterie() {
        return numeMaterie;
    }

    public void setNumeMaterie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
    }

    public int getNumarCredite() {
        return numarCredite;
    }

    public void setNumarCredite(int numarCredite) {
        this.numarCredite = numarCredite;
    }

    public String getTipExam() {
        return tipExam;
    }

    public void setTipExam(String tipExam) {
        this.tipExam = tipExam;
    }

    public String getDataSustinere() {
        return dataSustinere;
    }

    public void setDataSustinere(String dataSustinere) {
        this.dataSustinere = dataSustinere;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getDurataOre() {
        return durataOre;
    }

    public void setDurataOre(int durataOre) {
        this.durataOre = durataOre;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "idExam='" + idExam + '\'' +
                ", numeMaterie='" + numeMaterie + '\'' +
                ", numarCredite=" + numarCredite +
                ", tipExam='" + tipExam + '\'' +
                ", dataSustinere='" + dataSustinere + '\'' +
                ", ora='" + ora + '\'' +
                ", durataOre=" + durataOre +
                '}';
    }
}