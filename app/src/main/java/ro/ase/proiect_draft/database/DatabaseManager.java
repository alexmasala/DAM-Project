package ro.ase.proiect_draft.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import ro.ase.proiect_draft.DateConverter;
import ro.ase.proiect_draft.Exam;
import ro.ase.proiect_draft.Student;

@Database(entities = {Exam.class, Student.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class DatabaseManager extends RoomDatabase{

    private final static String DB_Exams = "exams.db";
    private static DatabaseManager instanta;

    public static DatabaseManager getInstance(Context context) {
        if (instanta == null) {
            instanta = Room.databaseBuilder(context, DatabaseManager.class, DB_Exams)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }

    public abstract ExamDao getExamDao();

    public abstract StudentDao getStudentDao() ;

}
