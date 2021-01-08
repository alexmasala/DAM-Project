package ro.ase.proiect_draft;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect_draft.database.AsyncTaskRunner.Callback;
import ro.ase.proiect_draft.database.services.ExamService;

public class ExamListActivity extends AppCompatActivity {

    public static final String EDIT_EXAM = "editExam";
    private static final int ADD_EXAM_REQUEST_CODE = 201;
    private static final int UPDATE_EXAM_REQUEST_CODE = 222;

    private ListView lvExam;
    private FloatingActionButton fabAddExams;

    private final List<Exam> listaExamene = new ArrayList<>();

    private ExamService examService;

    private SharedPreferences shp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);

        initComponents();
        examService = new ExamService(getApplicationContext());
        examService.getAll(getAllCallback());

    }

    private Callback<List<Exam>> getAllCallback() {
        return new Callback<List<Exam>>() {
            @Override
            public void runResultOnUiThread(
                    List<Exam> result) {
                if (result != null) {
                    listaExamene.clear();
                    listaExamene.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Exam> insertCallback() {
        return new Callback<Exam>() {
            @Override
            public void runResultOnUiThread(Exam exam) {
                if (exam != null) {
                    listaExamene.add(exam);
                    notifyAdapter();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.failedMessage,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Callback<Exam> updateCallback() {
        return new Callback<Exam>() {
            @Override
            public void runResultOnUiThread(Exam exam) {
                if (exam != null) {
                    for (Exam e : listaExamene) {
                        if (e.getId() == exam.getId()) {
                            e.setNumarCredite(exam.getNumarCredite());
                            e.setTipExam(exam.getTipExam());
                            e.setDataSustinere(exam.getDataSustinere());
                            e.setOra(exam.getOra());
                            e.setDurataOre(exam.getDurataOre());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    listaExamene.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EXAM_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            Exam exam = (Exam) data.getSerializableExtra(AddExamActivity.ADD_EXAM);
            examService.insert(exam, insertCallback());

        } else if (requestCode == UPDATE_EXAM_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            Exam exam = (Exam) data
                    .getSerializableExtra(AddExamActivity.ADD_EXAM);
            examService.update(exam, updateCallback());
        }
    }

    private void initComponents() {
        lvExam = findViewById(R.id.lv_exams);
        fabAddExams= findViewById(R.id.fabAddExam);

        addAdapter();
        fabAddExams.setOnClickListener(addExamEventListener());

        lvExam.setOnItemClickListener(updateExamEventListener());
        lvExam.setOnItemLongClickListener(deleteExamEventListener());

        //sharedPreferences = getSharedPreferences(ProfileActivity.PROFILE_SHARED, MODE_PRIVATE);
        //displayMessage();
    }

//    private void displayMessage() {
//        String name = sharedPreferences.getString(ProfileActivity.NAME, null);
//        if (name != null) {
//            Toast.makeText(getApplicationContext(),
//                    getString(R.string.display_param_message, name),
//                    Toast.LENGTH_SHORT)
//                    .show();
//
//            AlertDialog dialog = new AlertDialog.Builder(MainActivity4.this)
//                    .setTitle(R.string.main_my_title)
//                    .setMessage(getString(R.string.display_param_message, name))
//                    .create();
//            dialog.show();
//        }
//    }

//    private View.OnClickListener profileEventListener() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),
//                        ProfileActivity.class);
//                startActivity(intent);
//            }
//        };
//    }

    private AdapterView.OnItemLongClickListener deleteExamEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view,
                                           int position,
                                           long id) {
                examService.delete(listaExamene.get(position),
                        deleteCallback(position));
                return true;
            }
        };
    }

    private AdapterView.OnItemClickListener updateExamEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(),
                        AddExamActivity.class);
                intent.putExtra(AddExamActivity.ADD_EXAM,
                        listaExamene.get(position));
                startActivityForResult(intent, UPDATE_EXAM_REQUEST_CODE);

            }
        };
    }

    private View.OnClickListener addExamEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ExamListActivity.this, AddExamActivity.class);
                startActivityForResult(intent, ADD_EXAM_REQUEST_CODE);
            }
        };
    }

    private void addAdapter() {
//        ArrayAdapter<User> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, expens);
        ExamAdapter adapter = new ExamAdapter(getApplicationContext(), R.layout.exams_listview,
                listaExamene, getLayoutInflater());
        lvExam.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ExamAdapter adapter = (ExamAdapter) lvExam.getAdapter();
//        ArrayAdapter<User> adapter = (ArrayAdapter<User>) lvExpenses.getAdapter();
        adapter.notifyDataSetChanged();
    }
}