package ro.ase.proiect_draft;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect_draft.data.Syllabus;

public class Add_Syllabus_Activity extends AppCompatActivity {

    public static final String ADD_SYLLABUS = "adaugaSyllabus";
    List<Syllabus> syllabusList = new ArrayList<Syllabus>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_add_syllabus);

        listView = findViewById(R.id.listViewSyllabus);

        ExtractSyllabusJSON extractSyllabusJSON = new ExtractSyllabusJSON(){

            @Override
            protected void onPostExecute(String s) {
                syllabusList.addAll(ExtractSyllabusJSON.listaOrar);

                SyllabusAdapter adapter = new SyllabusAdapter(getApplicationContext(), R.layout.syllabus_listview,
                        syllabusList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Syllabus syllabus =  syllabusList.get(position);

//                        TextView tvProfit = view.findViewById(R.id.profit);
//                        if(movie.getProfit() > 100000)
//                            tvProfit.setTextColor(Color.GREEN);
//                        else
//                            tvProfit.setTextColor(Color.RED);

                        return view;
                    }
                };
                listView.setAdapter(adapter);
            }
        };
        try {
//            extractSyllabusJSON.execute(new URL("https://pastebin.com/raw/MbbLUNsA"));
            extractSyllabusJSON.execute(new URL("https://pastebin.com/raw/xniuuyZX"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void ToggleTheme( boolean isChecked ){
        if (isChecked) {
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        else{
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        finish();
        startActivity(new Intent(Add_Syllabus_Activity.this, Add_Syllabus_Activity.this.getClass()));
    }
}