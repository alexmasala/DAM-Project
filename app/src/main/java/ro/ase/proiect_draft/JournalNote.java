package ro.ase.proiect_draft;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.Date;

enum noteType{FAVORITE, IMPORTANT, REMINDMELATER}

enum curricularNote{LECTURE, LAB, OTHERS}

public class JournalNote implements Serializable {

private String title;
private Date data;
private String message;
private noteType notetype;
private curricularNote curNote;

    public JournalNote(String title, Date data, String message, noteType notetype, curricularNote curNote) {
        this.title = title;
        this.data = data;
        this.message = message;
        this.notetype = notetype;
        this.curNote = curNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public noteType getNotetype() {
        return notetype;
    }

    public void setNotetype(noteType notetype) {
        this.notetype = notetype;
    }

    public curricularNote getCurNote() {
        return curNote;
    }

    public void setCurNote(curricularNote curNote) {
        this.curNote = curNote;
    }

    @Override
    public String toString() {
        return "JournalNote{" +
                "title='" + title + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", notetype=" + notetype +
                ", curNote=" + curNote +
                '}';
    }
}
