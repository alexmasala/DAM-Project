package ro.ase.proiect_draft;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "courses", foreignKeys = @ForeignKey(entity = Student.class, parentColumns = "id",
        childColumns = "idStudent", onDelete = CASCADE), indices = @Index("idStudent"))

public class Course implements Serializable {

    //se genereaza automat pt ca e cheie primara si nu mai trb trecuta in constructor
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String idExam;
    private String numeCurs;
    private String prof;
    private String tipSeminar;
    private String serie;

    //we must ignore the default constructor
    @Ignore
    public Course(){

    }

    public Course(String numeCurs, String prof, String tipSeminar, String idSerie) {
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




    public int getIdCurs() {
        return id;
    }

    public void setIdCurs(int id) {
        this.id = id;
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
                "idCurs='" + id + '\'' +
                ", numeCurs='" + numeCurs + '\'' +
                ", idProf='" + prof + '\'' +
                ", tipSeminar='" + tipSeminar + '\'' +
                ", idSerie='" + serie + '\'' +
                '}';
    }

}