package ro.ase.proiect_draft;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExtractSyllabusJSON extends AsyncTask<URL, Void, String> {

    public static List<Syllabus> listaOrar = new ArrayList<>();

    JSONArray syllabus = null;
    JSONArray syllabus1 = null;
    JSONArray syllabus2 = null;

    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) urls[0].openConnection();
            conn.setRequestMethod("GET");
            InputStream ist = conn.getInputStream();

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = null;
            StringBuilder sbuf = new StringBuilder();
            while ((linie = br.readLine()) != null) {
                sbuf.append(linie);
            }

            //parsare JSON
            loadJSON(sbuf.toString());

            return sbuf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Error: cannot reach resource '" + urls[0] + "'");
    }

    public void loadJSON(String jsonStr) {
        if (jsonStr != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                throw new RuntimeException("JSON is invalid");
            }

            try {
                syllabus = jsonObject.getJSONArray("grupa");

                for (int i = 0; i < syllabus.length(); i++) {
                    JSONObject c = syllabus.getJSONObject(i);

                    int nr = Integer.parseInt(c.getString("nr"));
                    int capacitate = Integer.parseInt(c.getString("capacitate"));
                    String sef = c.getString("sef");


                    syllabus1 = c.getJSONArray("activitate");

                    for (int j = 0; j < syllabus1.length(); j++) {
                        c = syllabus1.getJSONObject(j);

                        String tip = c.getString("tip");
                        String zi = c.getString("zi");
                        String ora = c.getString("ora");

                        syllabus2 = c.getJSONArray("disciplina");

                        for (int z = 0; z < syllabus2.length(); z++) {
                            c = syllabus2.getJSONObject(z);

                            String nume = c.getString("nume");
                            String durata = c.getString("durata");
                            String profesor = c.getString("profesor");

                            Syllabus syllabus3 = new Syllabus(nr, capacitate, sef, tip, zi, ora, nume, durata, profesor);

                            listaOrar.add(syllabus3);
                        }

                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException("JSON is invalid");
            }
        } else throw new RuntimeException("JSON is invalid");
    }
}
