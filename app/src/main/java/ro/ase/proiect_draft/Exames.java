package ro.ase.proiect_draft;

public class Exames {
    String idSerie;
    String idExam;
    String numeMaterie;
    String tipExam;
    String dataSustinere;
    String ora;
    int durataOre;

    public Exames(String idSerie, String idExam, String numeMaterie, String tipExam, String dataSustinere, String ora, int durataOre) {
        this.idSerie = idSerie;
        this.idExam = idExam;
        this.numeMaterie = numeMaterie;
        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
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
                ", idExam='" + idExam + '\'' +
                ", numeMaterie='" + numeMaterie + '\'' +
                ", tipExam='" + tipExam + '\'' +
                ", dataSustinere='" + dataSustinere + '\'' +
                ", ora='" + ora + '\'' +
                ", durataOre=" + durataOre +
                '}';
    }
}
