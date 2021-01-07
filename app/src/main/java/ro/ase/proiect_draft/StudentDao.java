package ro.ase.proiect_draft;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface StudentDao {
    @Insert
    public void insert(Student student);

    @Insert
    public void insert(List<Student> students);

    @Query("select * from students")
    public List<Student> getAll();

    @Query("delete from students")
    public void deleteAll();

    @Delete
    public void deleteStudent(Student student);
}
