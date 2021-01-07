package ro.ase.proiect_draft;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "students")
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //    private String idStudent = UUID.randomUUID().toString();
    private String nume;
    private String prenume;
    private String facultate;
    private String specializare;
    private String email;
    private String password;

    public Student(int id, String nume, String prenume, String facultate, String specializare, String email, String password) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.specializare = specializare;
        this.email = email;
        this.password = password;
    }

//    public String getIdStudent() {
//        return idStudent;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", facultate='" + facultate + '\'' +
                ", specializare='" + specializare + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
