package ro.ase.proiect_draft;

public class Courses {
    String idCurs;
    String numeCurs;
    String idProf;
    String tipSeminar;
    String idSerie;

    public Courses(String idCurs, String numeCurs, String idProf, String tipSeminar, String idSerie) {
        this.idCurs = idCurs;
        this.numeCurs = numeCurs;
        this.idProf = idProf;
        this.tipSeminar = tipSeminar;
        this.idSerie = idSerie;
    }

    public String getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(String idCurs) {
        this.idCurs = idCurs;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public void setNumeCurs(String numeCurs) {
        this.numeCurs = numeCurs;
    }

    public String getIdProf() {
        return idProf;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }

    public String getTipSeminar() {
        return tipSeminar;
    }

    public void setTipSeminar(String tipSeminar) {
        this.tipSeminar = tipSeminar;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "idCurs='" + idCurs + '\'' +
                ", numeCurs='" + numeCurs + '\'' +
                ", idProf='" + idProf + '\'' +
                ", tipSeminar='" + tipSeminar + '\'' +
                ", idSerie='" + idSerie + '\'' +
                '}';
    }
}