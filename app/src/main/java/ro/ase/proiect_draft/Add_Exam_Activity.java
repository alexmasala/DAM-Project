package ro.ase.proiect_draft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Add_Exam_Activity extends AppCompatActivity {

    public static final String ADD_EXAM = "adaugaExamen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);


        final EditText etNumeMaterie = findViewById(R.id.editNumeMaterie);
        final EditText etNumarCredite = findViewById(R.id.editNumarCredite);
        final EditText etDataSustinere = findViewById(R.id.editdataSustinere);
        final String DATE_FORMAT = "MM/dd/yyyy";
        final EditText etOra = findViewById(R.id.editOra);
        final EditText etDurataOre = findViewById(R.id.editdurataOre);

        final Intent intent = getIntent();

        if (intent.hasExtra(ExamFragment.EDIT_EXAM)) {

            Exam exam = (Exam) intent.getSerializableExtra(ExamFragment.EDIT_EXAM);

            etNumeMaterie.setText(exam.getNumeMaterie());
            etNumarCredite.setText("" + exam.getNumarCredite());
            etDataSustinere.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(exam.getDataSustinere()));
            etOra.setText(exam.getOra());
            etDurataOre.setText(exam.getDurataOre());
        }

        Button btnSalvareExam = (Button) findViewById(R.id.btnSaveExam);
        btnSalvareExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNumeMaterie.getText().toString().isEmpty()) {
                    etNumeMaterie.setError("Introduceti numele materiei");
                    return;
                }
                if (etNumarCredite.getText().toString().isEmpty()) {
                    etNumarCredite.setError("Introduceti numarul de credite");
                    return;
                }
                if (etDataSustinere.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Introduceti data examenului", Toast.LENGTH_LONG).show();
                    return;
                }
                if (etOra.getText().toString().isEmpty()) {
                    etOra.setError("Scrieti ora la care incepe examenul");
                    return;
                }
                if (etDurataOre.getText().toString().isEmpty()) {
                    etDurataOre.setError("Scrieti cat timp dureaza examenul");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);

                try {
                    sdf.parse(etDataSustinere.getText().toString());

                    String name = etNumeMaterie.getText().toString();
                    int numarCredite = Integer.parseInt(etNumarCredite.getText().toString());
                    Date data = new Date(etDataSustinere.getText().toString());
                    String ora = etOra.getText().toString();
                    String durataOra = etDurataOre.getText().toString();

                    ExamDB examDb = ExamDB.getInstanta(getApplicationContext());

                    Random random = new Random();

                    Student student = new Student(random.nextInt(99) + 1, "Paraschiv","Ionel","ASE", "CSIE", "paraschiv@ase.ro","1234");

                    Exam examen = new Exam(name, numarCredite, data, ora, durataOra);
                    examen.setIdStudent(student.getId());

                    examDb.getStudentDao().insert(student);
                    examDb.getExamDao().insert(examen);


                    intent.putExtra(ADD_EXAM, examen);
                    setResult(RESULT_OK, intent);
                    finish();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}

