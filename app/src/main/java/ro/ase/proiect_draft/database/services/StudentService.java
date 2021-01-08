package ro.ase.proiect_draft.database.services;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Callable;

import ro.ase.proiect_draft.Student;
import ro.ase.proiect_draft.database.AsyncTaskRunner.AsyncTaskRunner;
import ro.ase.proiect_draft.database.AsyncTaskRunner.Callback;
import ro.ase.proiect_draft.database.DatabaseManager;
import ro.ase.proiect_draft.database.StudentDao;

public class StudentService {

    private final StudentDao studentDao;
    private final AsyncTaskRunner asyncTask;

    public StudentService(Context context) {
        studentDao = DatabaseManager.getInstance(context)
                .getStudentDao();
        asyncTask = new AsyncTaskRunner();
    }

    public void getAllByCategory(final String username, final Callback<List<Student>> callback) {
        Callable<List<Student>> callable = new Callable<List<Student>>() {
            @Override
            public List<Student> call() {
                if (username == null || username.trim().isEmpty()) {
                    return null;
                }
                return studentDao.getAllByCategory(username);
            }
        };

        asyncTask.executeAsync(callable, callback);
    }

    public void getAll(Callback<List<Student>> callback) {
        Callable<List<Student>> callable = new Callable<List<Student>>() {
            @Override
            public List<Student> call() {
                return studentDao.getAll();
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void insert(final Student stud,
                       Callback<Student> callback) {
        Callable<Student> callable = new Callable<Student>() {
            @Override
            public Student call() {
                if (stud == null) {
                    return null;
                }
                long id = studentDao.insert(stud);
                if (id == -1) {
                    return null;
                }
                stud.setId(id);
                return stud;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void update(final Student stud,
                       Callback<Student> callback) {
        Callable<Student> callable = new Callable<Student>() {
            @Override
            public Student call() {
                if (stud == null) {
                    return null;
                }
                int count = studentDao.update(stud);
                if (count != 1) {
                    return null;
                }
                return stud;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void delete(final Student student,
                       Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (student == null) {
                    return -1;
                }
                return studentDao.delete(student);
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

}
