package ro.ase.proiect_draft.data;

public class DataManager {
    private static final DataManager ourInstance = new DataManager();
    private DataManager() { }
    public static DataManager getInstance() {
        return ourInstance;
    }

    Syllabus syllabus = null;
    Student student = null;
    Repository<Course> courses = new Repository<>();
    Repository<Exam> exams = new Repository<>();
    Repository<JournalNote> notes = new Repository<>();

    public Repository<Course> getCourses() {
        return courses;
    }

    public Repository<Exam> getExams() {
        return exams;
    }

    public Repository<JournalNote> getNotes() {
        return notes;
    }

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
