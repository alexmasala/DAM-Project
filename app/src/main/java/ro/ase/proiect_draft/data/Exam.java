package ro.ase.proiect_draft.data;

import java.io.Serializable;

public class Exam implements Serializable, Indexable {
    String idSerie;
    String id;
    String numeMaterie;
    String tipExam;
    String dataSustinere;
    String ora;
    int durataOre;

    public Exam(String idSerie, String idExam, String numeMaterie, String tipExam, String dataSustinere, String ora, int durataOre) {
        this.idSerie = idSerie;
        this.id = idExam;
        this.numeMaterie = numeMaterie;
        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeMaterie() {
        return numeMaterie;
    }

    public void setNumeMaterie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
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
        return "Exames{" +
                "idSerie='" + idSerie + '\'' +
                ", idExam='" + id + '\'' +
                ", numeMaterie='" + numeMaterie + '\'' +
                ", tipExam='" + tipExam + '\'' +
                ", dataSustinere='" + dataSustinere + '\'' +
                ", ora='" + ora + '\'' +
                ", durataOre=" + durataOre +
                '}';
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    public String getInsertQuery() {
        return null;
    }
}
