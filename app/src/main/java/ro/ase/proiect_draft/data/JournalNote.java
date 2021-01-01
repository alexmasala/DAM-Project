package ro.ase.proiect_draft.data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;



public class JournalNote implements Serializable, Indexable {

    private String id;
    private String title;
    private Date data;
    private String message;
    private NoteType notetype;
    private CurricularNote curNote;

    public JournalNote(String title, Date data, String message, NoteType notetype, CurricularNote curNote) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.data = data;
        this.message = message;
        this.notetype = notetype;
        this.curNote = curNote;
    }

    @Override
    public String getId() {
        return id;
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

    public NoteType getNotetype() {
        return notetype;
    }

    public void setNotetype(NoteType notetype) {
        this.notetype = notetype;
    }

    public CurricularNote getCurNote() {
        return curNote;
    }

    public void setCurNote(CurricularNote curNote) {
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

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    public String getInsertQuery() {
        return null;
    }

    public enum NoteType {FAVORITE, IMPORTANT, REMINDMELATER}

    public enum CurricularNote {LECTURE, LAB, OTHERS}
}
