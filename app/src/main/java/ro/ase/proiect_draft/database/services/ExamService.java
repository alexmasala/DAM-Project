package ro.ase.proiect_draft.database.services;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Callable;

import ro.ase.proiect_draft.Exam;
import ro.ase.proiect_draft.database.AsyncTaskRunner.AsyncTaskRunner;
import ro.ase.proiect_draft.database.AsyncTaskRunner.Callback;
import ro.ase.proiect_draft.database.DatabaseManager;
import ro.ase.proiect_draft.database.ExamDao;

public class ExamService {

    private final ExamDao examDao;
    private final AsyncTaskRunner asyncTask;

    public ExamService(Context context) {
        examDao = DatabaseManager.getInstance(context)
                .getExamDao();
        asyncTask = new AsyncTaskRunner();
    }

    public void getAllByCategory(final String format, final Callback<List<Exam>> callback) {
        Callable<List<Exam>> callable = new Callable<List<Exam>>() {
            @Override
            public List<Exam> call() {
                if (format == null || format.trim().isEmpty()) {
                    return null;
                }
                return examDao.getAllByCategory(format);
            }
        };

        asyncTask.executeAsync(callable, callback);
    }

    public void getAll(Callback<List<Exam>> callback) {
        Callable<List<Exam>> callable = new Callable<List<Exam>>() {
            @Override
            public List<Exam> call() {
                return examDao.getAll();
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void insert(final Exam exam,
                       Callback<Exam> callback) {
        Callable<Exam> callable = new Callable<Exam>() {
            @Override
            public Exam call() {
                if (exam == null) {
                    return null;
                }
                long id = examDao.insert(exam);
                if (id == -1) {
                    return null;
                }
                exam.setId((int) id);
                return exam;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void update(final Exam exam,
                       Callback<Exam> callback) {
        Callable<Exam> callable = new Callable<Exam>() {
            @Override
            public Exam call() {
                if (exam == null) {
                    return null;
                }
                int count = examDao.update(exam);
                if (count != 1) {
                    return null;
                }
                return exam;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void delete(final Exam exam,
                       Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (exam == null) {
                    return 1;
                }
                return examDao.deleteAll();
            }
        };
        asyncTask.executeAsync(callable, callback);
    }
}