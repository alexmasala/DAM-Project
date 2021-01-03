package ro.ase.proiect_draft;

import java.io.Serializable;

public class Course implements Serializable {

    String idCurs;
    String idExam;
    String numeCurs;
    String prof;
    String tipSeminar;
    String serie;

    public Course(String idCurs, String numeCurs, String prof, String tipSeminar, String idSerie) {
        this.idCurs = idCurs;
        this.numeCurs = numeCurs;
        this.prof = prof;
        this.tipSeminar = tipSeminar;
        this.serie = idSerie;
    }

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }


    public String getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(String id) {
        this.idCurs = id;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public void setNumeCurs(String numeCurs) {
        this.numeCurs = numeCurs;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getTipSeminar() {
        return tipSeminar;
    }

    public void setTipSeminar(String tipSeminar) {
        this.tipSeminar = tipSeminar;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "idCurs='" + idCurs + '\'' +
                ", numeCurs='" + numeCurs + '\'' +
                ", idProf='" + prof + '\'' +
                ", tipSeminar='" + tipSeminar + '\'' +
                ", idSerie='" + serie + '\'' +
                '}';
    }

}