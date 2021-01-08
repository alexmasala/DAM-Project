package ro.ase.proiect_draft.database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ro.ase.proiect_draft.Exam;

@Dao
public interface ExamDao {

    @Insert
    long insert(Exam exam);

    @Delete
    int delete(Exam exam);

    @Insert
    void insert(List<Exam> carti);

    @Query("Select * from examene")
    List<Exam> getAll();

    @Query("Delete from examene")
    int deleteAll();

    @Query("select * from examene where tipExam=:tipExam")
    List<Exam> getAllByCategory(String tipExam);
    @Update

    int update(Exam exam);
    @Query("Delete from examene where id = :id1")
    void deleteWhere(long id1);

    @Delete
    void deleteExam(Exam exam);

}
