package ro.ase.proiect_draft;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "studenti")
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name = "nume")
    private String nume;
    @ColumnInfo(name = "prenume")
    private String prenume;
    @ColumnInfo(name = "facultate")
    private String facultate;
    @ColumnInfo(name = "specializare")
    private String specializare;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;

    public Student(long idStudent, String nume, String prenume, String facultate, String specializare, String email, String password) {
       this.id = idStudent;
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.specializare = specializare;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long idStudent) {
        this.id = idStudent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", facultate='" + facultate + '\'' +
                ", specializare='" + specializare + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
