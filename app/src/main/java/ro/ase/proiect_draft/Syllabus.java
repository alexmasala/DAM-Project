package ro.ase.proiect_draft;

import android.content.Context;

import java.io.Serializable;
import java.util.Date;

public class Syllabus implements Serializable {

    private int nr;
    private int capacitate;
    private String sef;
//    // variable to hold context
////    private Context context;
////
//////save the context recieved via constructor in a local variable
////    public Syllabus(Context context){
////        this.context=context;
////    }

    public Syllabus(int nr, int capacitate, String sef) {
        this.nr = nr;
        this.capacitate = capacitate;
        this.sef = sef;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public String getSef() {
        return sef;
    }

    public void setSef(String sef) {
        this.sef = sef;
    }

    @Override
    public String toString() {
        return "Syllabus{" +
                "nr=" + nr +
                ", capacitate=" + capacitate +
                ", sef='" + sef + '\'' +
                '}';
    }
}