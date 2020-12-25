package ro.ase.proiect_draft;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ExtractSyllabusJSON extends AsyncTask<URL, Void, String> {

    public static List<Syllabus> listaOrar = new ArrayList<>();

    JSONArray syllabus = null;
    JSONArray syllabus1 = null;
    JSONArray syllabus2 = null;

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
                syllabus = jsonObject.getJSONArray("grupa");

                for (int i = 0; i < syllabus.length(); i++) {
                    JSONObject c = syllabus.getJSONObject(i);

                    int nr = Integer.parseInt(c.getString("nr"));
                    int capacitate = Integer.parseInt(c.getString("capacitate"));
                    String sef = c.getString("sef");


                    Syllabus syllabus = new Syllabus(nr, capacitate, sef);

                    syllabus1 = c.getJSONArray("activitate");

                    for (int j = 0; j < syllabus1.length(); j++) {
                        c = syllabus1.getJSONObject(j);

                        String tip = c.getString("tip");
                        String zi = c.getString("zi");
                        String ora = c.getString("ora");


                        Syllabus syllabus1 = new Syllabus(tip, zi, ora);

                        syllabus2 = c.getJSONArray("disciplina");

                        for (int z = 0; z < syllabus2.length(); z++) {
                            c = syllabus2.getJSONObject(z);

                            String nume = c.getString("nume");
                            String durata = c.getString("durata");
                            String profesor = c.getString("profesor");


                            Syllabus syllabus2 = new Syllabus(nume, durata, profesor);

                            Syllabus syllabus3 = new Syllabus(nr, capacitate, sef,tip, zi, ora,nume, durata, profesor);

                            listaOrar.add(syllabus3);
                        }

                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Log.e("loadJSON", "JSON object is null");
    }
}
