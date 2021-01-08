package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.ase.proiect_draft.JournalNote;

public class BarChart_Journal_Activity extends AppCompatActivity {

    List<JournalNote> journalList;
    LinearLayout layout;
    Map<String, Integer> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_journal);

        Intent intent = getIntent();

        journalList = (ArrayList<JournalNote>) intent.getSerializableExtra("jounalList");

        source = getSource(journalList);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new BarChartView(getApplicationContext(), source));
        }

        private Map<String, Integer> getSource( List<JournalNote> notes)
        {
            if(notes == null || notes.isEmpty())
                return new HashMap<>();
            else{
                Map<String,Integer> results = new HashMap<>();
                for(JournalNote journalNote: notes)
                    if(results.containsKey(journalNote.getNotetype()))
                        results.put(journalNote.getNotetype().toString(),  results.get(journalNote.getNotetype())+1);

                    else { results.put(journalNote.getNotetype().toString(),1);}


                return results;
            }

        }

}