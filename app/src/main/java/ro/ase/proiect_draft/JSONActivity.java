package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JSONActivity extends ListActivity {

    public static final String  TAG_GRUPA = "grupa";

    public static final String TAG_NR = "nr";
    public static final String TAG_CAPACITATE = "capacitate";
    public static final String TAG_SEF = "sef";

    public static final String  TAG_ACTIVITATE = "activitate";

    public static final String TAG_TIP = "tip";
    public static final String TAG_ZI = "zi";
    public static final String TAG_ORA = "ora";

    public static final String TAG_DISCIPLINA = "disciplina";

    public static final String TAG_NUME = "nume";
    public static final String TAG_DURATA= "durata";
    public static final String TAG_PROFESOR = "profesor";

    //Afisare incarcare
   // private ProgressDialog pDialog;

    JSONArray syllabus1 = null;
    JSONArray syllabus2 = null;
    JSONArray syllabus3 = null;

    ArrayList<HashMap<String, String>> syllabusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }

        syllabusList = new ArrayList<>();

        URL url = null;
        try {
            url = new URL("https://pastebin.com/raw/Z09WUsZW");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        GetSyllabus syll = new GetSyllabus();
       syll.setOnTaskFinishedEvent(new OnTaskExecutionFinished() {
            @Override
            public void onTaskFinishedEvent(String result) {

                //Adapter listview json
                ListAdapter adapter = new SimpleAdapter(JSONActivity.this,
                        syllabusList,
                        R.layout.list_syllabus_item,
                        new String[]{TAG_NR, TAG_CAPACITATE, TAG_SEF, TAG_TIP, TAG_ZI,
                                TAG_ORA, TAG_NUME, TAG_DURATA, TAG_PROFESOR},
                        new int[]{R.id.nr, R.id.capacitate, R.id.sef, R.id.tip, R.id.zi, R.id.ora,
                                    R.id.nume, R.id.durata, R.id.profesor}){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        HashMap<String, String> currentRow = syllabusList.get(position);

                        return view;
                    }
                };

                setListAdapter(adapter);
            }
        });
        syll.execute(url);

    }

    public interface OnTaskExecutionFinished
    {
        void onTaskFinishedEvent(String result);
    }

    public class GetSyllabus extends AsyncTask<URL, Void, String>
    {
        private OnTaskExecutionFinished event;

        public void setOnTaskFinishedEvent(OnTaskExecutionFinished event)
        {
            if (event!=null)
                this.event = event;
        }

        @Override
        protected String doInBackground(URL... urls) {
            HttpURLConnection conn = null;

            try{
                conn = (HttpURLConnection)urls[0].openConnection();
                conn.setRequestMethod("GET");
                InputStream ist = conn.getInputStream();

                InputStreamReader isr = new InputStreamReader(ist);
                BufferedReader br = new BufferedReader(isr);
                String linie = null;
                String sbuf = "";
                while ((linie = br.readLine())!=null)
                {
                    sbuf +=linie;
                }

                //parsare JSON
                loadJSON(sbuf);

                return sbuf;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(this.event!=null)
                this.event.onTaskFinishedEvent(s);
            else
                Log.e("GetSyllabus", "event is null");
        }

        public void loadJSON(String jsonStr)
        {
            if(jsonStr!=null)
            {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    //baga atributele ale fiecarui nod
                    syllabus1 = jsonObject.getJSONArray(TAG_GRUPA);

                    for(int i=0;i<syllabus1.length();i++)
                    {
                        JSONObject s1 = syllabus1.getJSONObject(i);
                        String nr = s1.getString(TAG_NR);
                        String capacitate = s1.getString(TAG_CAPACITATE);
                        String sef = s1.getString(TAG_SEF);

                        HashMap<String, String> sh1  = new HashMap<>();
                        sh1.put(TAG_NR, nr);
                        sh1.put(TAG_CAPACITATE, capacitate);
                        sh1.put(TAG_SEF, sef);

                        syllabusList.add(sh1);
                    }

                    syllabus2 = jsonObject.getJSONArray(TAG_ACTIVITATE);

                    for(int i=0;i<syllabus2.length();i++)
                    {
                        JSONObject s2 = syllabus2.getJSONObject(i);
                        String tip = s2.getString(TAG_TIP);
                        String zi = s2.getString(TAG_ZI);
                        String ora = s2.getString(TAG_ORA);

                        HashMap<String, String> sh2  = new HashMap<>();
                        sh2.put(TAG_TIP, tip);
                        sh2.put(TAG_ZI, zi);
                        sh2.put(TAG_ORA, ora);

                        syllabusList.add(sh2);
                    }

                    syllabus3 = jsonObject.getJSONArray(TAG_DISCIPLINA);

                    for(int i=0;i<syllabus3.length();i++)
                    {
                        JSONObject s3 = syllabus3.getJSONObject(i);
                        String nume = s3.getString(TAG_NUME);
                        String durata = s3.getString(TAG_DURATA);
                        String profesor = s3.getString(TAG_PROFESOR);

                        HashMap<String, String> sh3  = new HashMap<>();
                        sh3.put(TAG_NUME, nume);
                        sh3.put(TAG_DURATA, durata);
                        sh3.put(TAG_PROFESOR, profesor);

                        syllabusList.add(sh3);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
                Log.e("loadJSON", "JSON object is null");
        }
    }

}