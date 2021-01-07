package ro.ase.proiect_draft.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ro.ase.proiect_draft.Student;

@Dao
public interface StudentDao {

    @Insert
    long insert(Student stud);

    @Update
    int update(Student stud);

    @Insert
    void insert(List<Student> studenti);

    @Query("Select * from studenti")
    List<Student> getAll();

    @Query("Delete from studenti")
    void deleteAll();

    @Query("Delete from studenti where id = :id1")
    void deleteWhere(long id1);
    @Query("select * from studenti where email=:email")
    List<Student> getAllByCategory(String email);
    @Delete
    Integer delete(Student stud);

}
