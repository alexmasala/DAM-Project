package ro.ase.proiect_draft;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class ExamDB extends RoomDatabase {

    public static final String DB_NAME = "exams.db";
    private static ExamDB instanta;

    public static ExamDB getInstanta(Context context){
        if(instanta == null)
            instanta = Room.databaseBuilder(context, ExamDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        return instanta;
    }

    public abstract ExamDao getExamDao();
    public abstract StudentDao getStudentDao();
}
