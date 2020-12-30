package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Add_Journal_Note_Activity extends AppCompatActivity
{
    public static final String ADD_JNOTE = "adaugaNotita";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_add__journal__note_);



        final Spinner spinnerNoteType = findViewById(R.id.spinnerJournal);

        //Leg Spinner-Enum
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.add_journal_note, R.layout.support_simple_spinner_dropdown_item);
        spinnerNoteType.setAdapter(adapter);

        final EditText etTitle = findViewById(R.id.editTextTitle);
        final EditText etDate = findViewById(R.id.editTextDate);
        final EditText etMessage = findViewById(R.id.editTextMessage);
        final String DATE_FORMAT = "MM/dd/yyyy";

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final Intent intent = getIntent();



        if(intent.hasExtra(MyJournalFragment.EDIT_JNOTE)){

            JournalNote jnote = (JournalNote) intent.getSerializableExtra(MyJournalFragment.EDIT_JNOTE);

             etTitle.setText(jnote.getTitle());
            etDate.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(jnote.getData()));
            etMessage.setText(jnote.getMessage());

            ArrayAdapter<String> adaptor = (ArrayAdapter<String>)spinnerNoteType.getAdapter();
            for(int i=0;i<adaptor.getCount();i++)
                if(adaptor.getItem(i).equals(jnote.getNotetype()))
                {
                    spinnerNoteType.setSelection(i);
                    break;
                }
            if(jnote.getCurNote().equals("Lecture"))
                radioGroup.check(R.id.radioBtnLecture);
            else
            if(jnote.getCurNote().equals("Lab"))
                radioGroup.check(R.id.radioBtnLab);
            else
                radioGroup.check(R.id.radioBtnOthers);
        }

            Button btnSalvare = (Button) findViewById(R.id.buttonSave);
            btnSalvare.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View view)
            {
                if(etTitle.getText().toString().isEmpty())
                {
                    etTitle.setError("Introduceti titlul");
                    return;
                }
                if (etDate.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Introduceti data in jurnal", Toast.LENGTH_LONG).show();
                    return;
                }
                if(etMessage.getText().toString().isEmpty())
                {
                    etMessage.setError("Scrieti notita");
                    return;
                }

                //creare obiect clasa JournalNote

                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);

                try
                {
                    sdf.parse(etDate.getText().toString());

                    String title = etTitle.getText().toString();
                    Date data = new Date(etDate.getText().toString());
                    String mesaj = etMessage.getText().toString();
                    noteType type = noteType.valueOf(spinnerNoteType.getSelectedItem().toString().toUpperCase().replace(" ", ""));

                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    curricularNote crnote = curricularNote.valueOf(radioButton.getText().toString().toUpperCase());
                    JournalNote notita = new JournalNote(title, data, mesaj, type, crnote);

                    intent.putExtra(ADD_JNOTE, notita);
                    setResult(RESULT_OK, intent);
                    finish();

                } catch (ParseException e)
                {
                    e.printStackTrace();
                }


            }

        });
      }

    public void ToggleTheme( boolean isChecked ){
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        finish();
        startActivity(new Intent(Add_Journal_Note_Activity.this, Add_Journal_Note_Activity.this.getClass()));

    }

    }
