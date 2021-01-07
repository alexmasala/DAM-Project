package ro.ase.proiect_draft;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "exams", foreignKeys = @ForeignKey(entity = Student.class, parentColumns = "id",
        childColumns = "idStudent", onDelete = CASCADE), indices = @Index("idStudent"))
public class Exam implements Serializable {

    //se genereaza automat pt ca e cheie primara si nu mai trb trecuta in constructor
    @PrimaryKey(autoGenerate = true)
    private int idExam;

    private String numeMaterie;
    private int numarCredite;
    //    private String tipExam;
    private Date dataSustinere;
    private String ora;
    private String durataOre;

    private  int idStudent;

    //we must ignore the default constructor
    public Exam(){

    }

    //nu stergem cosntructorul pt ca il mai folosim in alte activitati
    public Exam(String numeMaterie, int numarCredite, Date dataSustinere, String ora, String durataOre) {
        this.numeMaterie = numeMaterie;
        this.numarCredite = numarCredite;
//        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
    }

    //constructorul apelat de room
    public Exam(String numeMaterie, int numarCredite, Date dataSustinere, String ora, String durataOre, int idStudent) {
        this.numeMaterie = numeMaterie;
        this.numarCredite = numarCredite;
//        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
        this.idStudent = idStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
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

//    public String getTipExam() {
//        return tipExam;
//    }
//
//    public void setTipExam(String tipExam) {
//        this.tipExam = tipExam;
//    }

    public Date getDataSustinere() {
        return dataSustinere;
    }

    public void setDataSustinere(Date dataSustinere) {
        this.dataSustinere = dataSustinere;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getDurataOre() {
        return durataOre;
    }

    public void setDurataOre(String durataOre) {
        this.durataOre = durataOre;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "idExam=" + idExam +
                ", numeMaterie='" + numeMaterie + '\'' +
                ", numarCredite=" + numarCredite +
//                ", tipExam='" + tipExam + '\'' +
                ", dataSustinere='" + dataSustinere + '\'' +
                ", ora='" + ora + '\'' +
                ", durataOre=" + durataOre +
                ", idStudent=" + idStudent +
                '}';
    }
}