package ro.ase.proiect_draft;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="examene", foreignKeys = @ForeignKey(entity = Student.class,
        parentColumns = "id", childColumns = "foreignIdStudent"), indices = @Index("foreignIdStudent"))
public class Exam implements Serializable {

@PrimaryKey(autoGenerate = true)
    private int  id;
    public int getIdExam() {
        return id;
    }

    public void setIdExam(int idExam) {
        this.id = idExam;
    }

    private String numeMaterie;
    private int numarCredite;
    private String tipExam;
    private String dataSustinere;
    private String ora;
    private int durataOre;


    @ColumnInfo(name="foreignIdStudent")
    private int foreignIdStudent;

    public int getForeignIdStudent() {
        return foreignIdStudent;
    }
    public void setForeignIdStudent(int foreignIdStudent) {
        this.foreignIdStudent = foreignIdStudent;
    }

    @Ignore
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }



    public Exam(String numeMaterie, int numarCredite, String tipExam, String dataSustinere, String ora, int durataOre, int foreignIdStudent) {
        this.numeMaterie = numeMaterie;
        this.numarCredite = numarCredite;
        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
        this.foreignIdStudent = foreignIdStudent;
    }

    @Ignore
    public Exam(int idExam, String numeMaterie, int numarCredite, String tipExam, String dataSustinere, String ora, int durataOre) {
        this.id = idExam;
        this.numeMaterie = numeMaterie;
        this.numarCredite = numarCredite;
        this.tipExam = tipExam;
        this.dataSustinere = dataSustinere;
        this.ora = ora;
        this.durataOre = durataOre;
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
                "idExam='" + id + '\'' +
                ", numeMaterie='" + numeMaterie + '\'' +
                ", numarCredite=" + numarCredite +
                ", tipExam='" + tipExam + '\'' +
                ", dataSustinere='" + dataSustinere + '\'' +
                ", ora='" + ora + '\'' +
                ", durataOre=" + durataOre +
                '}';
    }
}