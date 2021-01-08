package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import ro.ase.proiect_draft.database.DatabaseManager;

public class AddExamActivity extends AppCompatActivity {

    private Intent intent;
    public static final String ADD_EXAM = "addExam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        final Spinner spinner = findViewById(R.id.spinnerGenExam);

        final EditText etnumeMaterie = findViewById(R.id.editNumeMaterie);
        final EditText etnrCredite = findViewById(R.id.editTextNumarCredite);
        final EditText etdataSustinere= findViewById(R.id.editTextDate);
        final EditText etoraSustinere = findViewById(R.id.editTextOra);
        final EditText etdurataExamen = findViewById(R.id.editTextDurataExam);


        ArrayAdapter<CharSequence> adaptor = ArrayAdapter.createFromResource(this, R.array.tip_examen,
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptor);

        SharedPreferences preferences=getSharedPreferences("dateExam",0);
        int id=preferences.getInt("id",0);
        String numeMaterie=preferences.getString("materie",null);
        int nrCredite=preferences.getInt("credite",0);
        String dataSustinere=preferences.getString("data",null);
        int durataExamen =preferences.getInt("durata",0);
        String tipExamen=preferences.getString("tipExamen",null);
        String ora=preferences.getString("ora",null);

        Exam exam = new Exam(id, numeMaterie, nrCredite, tipExamen, new Date(), ora, durataExamen);
        Toast.makeText(getApplicationContext(),exam.toString(),Toast.LENGTH_LONG).show();
        final  String DATE_FORMAT = "dd/MM/yyyy";
        intent = getIntent();

        if(intent.hasExtra(ExamListActivity.EDIT_EXAM))
        {
            Exam ex = (Exam)intent.getSerializableExtra(ExamListActivity.EDIT_EXAM);
            etnumeMaterie.setText(ex.getNumeMaterie());
            etoraSustinere.setText(ex.getOra());
            etdataSustinere.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(ex.getDataSustinere()));
            etnrCredite.setText(ex.getNumarCredite());
            etdurataExamen.setText(ex.getDurataOre());
            ArrayAdapter<String> adapter1 = (ArrayAdapter<String>)spinner.getAdapter();
            for(int i=0;i<adapter1.getCount();i++)
                if(adapter1.getItem(i).toUpperCase().equals(ex.getTipExam().toUpperCase()))
                {
                    spinner.setSelection(i, true);
                    break;
                }
        }


        Button save = findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etnumeMaterie.getText()==null || etnumeMaterie.getText().toString().isEmpty() ||
                        etnumeMaterie.getText().toString().trim().isEmpty())
                    etnumeMaterie.setError("Introduceti disciplina!");
                else
                if (etoraSustinere.getText()==null || etoraSustinere.getText().toString().isEmpty() ||
                        etoraSustinere.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(),
                            "Introduceti ora sustinerii examenului!", Toast.LENGTH_LONG).show();
                else
                if (etdataSustinere.getText().toString().isEmpty())
                    etdataSustinere.setError("Introduceti data sustinerii examenului!");
                else
                if (etnrCredite.getText().toString().isEmpty())
                    etnrCredite.setError("Introduceti numarul de credite!");
                else
                if (etdurataExamen.getText().toString().isEmpty())
                    etdurataExamen.setError("Introduceti durata examenului!");

                else
                {
                    //creare obiect Carte
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);

                    try {
                        sdf.parse(etdataSustinere.getText().toString());

                        String materie = etnumeMaterie.getText().toString();
                        String ora = etoraSustinere.getText().toString();
                        Date data = new Date(etdataSustinere.getText().toString());
                        int numarCrdite = Integer.parseInt(etnrCredite.getText().toString());
                        int durata = Integer.parseInt(etdataSustinere.getText().toString());
                        String tip = spinner.getSelectedItem().toString().toUpperCase();



                        DatabaseManager databaseManager = DatabaseManager.getInstance(getApplicationContext());

                        Random random=new Random();
                        long randomNumber = (long) random.nextInt(50);
                        Student stud=new Student( randomNumber,"Alexandru","Andra",
                                "Csie", "Ie",
                                "andra@yahoo.com","vampir");

                        Exam examInserat=new Exam(materie, numarCrdite, tip, data, ora, durata);



                        examInserat.setForeignIdStudent((int)stud.getId());

                        SharedPreferences dateExamen=getSharedPreferences("dateExam",0);
                        SharedPreferences.Editor editor=dateExamen.edit();
                        editor.putInt("id",examInserat.getId());
                        editor.putString("materie",examInserat.getNumeMaterie());
                        editor.putInt("credite", examInserat.getNumarCredite());
                        editor.putString("data",examInserat.getDataSustinere().toString());
                        editor.putInt("durata", examInserat.getDurataOre());
                        editor.putString("tipExamen", examInserat.getTipExam());
                        editor.putString("ora",examInserat.getOra());

                        editor.apply();




                        //Toast.makeText(getApplicationContext(), carte.toString(), Toast.LENGTH_LONG).show();
                        intent.putExtra(ADD_EXAM, examInserat);
                        setResult(RESULT_OK,intent);
                        finish();

                    } catch (ParseException e) {
                        e.printStackTrace();

                        Toast.makeText(getApplicationContext(), "Data invalida!", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
    }
}