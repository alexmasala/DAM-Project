package ro.ase.proiect_draft;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ExamDao {
    @Insert
    public void insert(Exam exam);

    @Insert
    public void insert(List<Exam> examList);

    @Query("select * from exams")
    public List<Exam> getAll();

    @Query("delete from exams")
    public void deleteAll();

    @Delete
    public void deleteExam(Exam exam);

    @Query("select * from exams where idStudent= :idStudent")
    public List<Exam> getExamFromStudent(long idStudent);
}
