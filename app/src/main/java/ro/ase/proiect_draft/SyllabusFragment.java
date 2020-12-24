package ro.ase.proiect_draft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ro.ase.proiect_draft.Syllabus;

import static ro.ase.proiect_draft.Add_Syllabus_Activity.ADD_SYLLABUS;

public class SyllabusFragment extends Fragment {


    public SyllabusFragment() {
        // Required empty public constructor
    }

    public static final int REQUEST_CODE = 200;

    public static final int REQUEST_CODE_EDIT = 300;
    private Intent intent;
    private Button btnJSON;
    private Button btnJSONRetea;
    List<Syllabus> syllabusList = new ArrayList<Syllabus>();
    Syllabus syll;
    private ListView listView;
    public static final String EDIT_SYLLABUS = "editSyllabus";
    public int poz;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        MERGE: !!!
//        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);
//
//        listView = (ListView) view.findViewById(R.id.listViewsyl);

        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);

//        listView = (ListView) view.findViewById(R.id.listViewSyllabus);

//        ListAdapter adapter = new SimpleAdapter(Syllabus.class,
//                syllabusList,
//                R.layout.syllabus_listview,
//                new String[]{, TAG_CAPACITATE, TAG_SEF, TAG_TIP, TAG_ZI,
//                        TAG_ORA, TAG_NUME, TAG_DURATA, TAG_PROFESOR},
//                new int[]{R.id.nr, R.id.capacitate, R.id.sef}){}

        btnJSON = (Button) view.findViewById(R.id.buttonStartJSONActivity);

        btnJSON.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                intent = new Intent(getActivity(), JSONActivity.class);
                startActivity(intent);

            }

        });

        btnJSONRetea = (Button) view.findViewById(R.id.buttonStartExtractSyllabusJSON);

        btnJSONRetea.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                intent = new Intent(getActivity(), Add_Syllabus_Activity.class);
                startActivity(intent);

//                intent = new Intent(getActivity(), ExtractSyllabusJSON.class);
//                startActivity(intent);
//
////                intent = new Intent(getActivity().getApplicationContext(), Add_Syllabus_Activity.class);
////                Serializable data = intent.getSerializableExtra(Add_Syllabus_Activity.ADD_SYLLABUS);
////                startActivityForResult(intent, REQUEST_CODE);
//
////                new Syllabus(this);
//
//                ExtractSyllabusJSON extractSyllabusJSON = new ExtractSyllabusJSON(){
//
//                    @Override
//                    protected void onPostExecute(String s) {
//                        syllabusList.addAll(ExtractSyllabusJSON.listaOrar);
//
//                        SyllabusAdapter adapter = new SyllabusAdapter(getActivity().getApplicationContext(), R.layout.syllabus_listview,
//                                syllabusList, getLayoutInflater()){
//                            @NonNull
//                            @Override
//                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                                View view = super.getView(position, convertView, parent);
//
//                                Syllabus syllabus =  syllabusList.get(position);
//
////                        TextView tvProfit = view.findViewById(R.id.profit);
////                        if(movie.getProfit() > 100000)
////                            tvProfit.setTextColor(Color.GREEN);
////                        else
////                            tvProfit.setTextColor(Color.RED);
//
//                                return view;
//                            }
//                        };
//                        listView.setAdapter(adapter);
//                    }
//                };
//                try {
//                    extractSyllabusJSON.execute(new URL("https://pastebin.com/raw/MbbLUNsA"));
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }

            }

        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//                poz = position;
//                intent = new Intent(getActivity().getApplicationContext(), Add_Syllabus_Activity.class);
//                intent.putExtra(EDIT_SYLLABUS, syllabusList.get(position));
//                startActivityForResult(intent, REQUEST_CODE_EDIT);
//            }
//        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//                final Syllabus syllabus = syllabusList.get(position);
//
//                final SyllabusAdapter adapter = (SyllabusAdapter) listView.getAdapter();
//
//                AlertDialog dialog = new AlertDialog.Builder(getActivity())
//                        .setTitle("Confirmare stergere")
//                        .setMessage("Sigur doriti stergerea?")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(getActivity(), "Nu s-a sters nimic!",
//                                        Toast.LENGTH_LONG).show();
//                                dialogInterface.cancel();
//                            }
//                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                syllabusList.remove(syllabus);
//                                adapter.notifyDataSetChanged();
//                                Toast.makeText(getActivity(), "S-a sters: "+syllabus.toString(),
//                                        Toast.LENGTH_LONG).show();
//                                dialogInterface.cancel();
//                            }
//                        }).create();
//
//                dialog.show();
//
//                return true;
//            }
//        });

        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            Syllabus syllabus = (Syllabus) data.getSerializableExtra(Add_Syllabus_Activity.ADD_SYLLABUS);
//
//            if (syllabus != null) {
//
//                syllabusList.add(syllabus);
//
//                SyllabusAdapter adapter = new SyllabusAdapter(getActivity(), R.layout.syllabus_listview,
//                        syllabusList, getLayoutInflater()){
//                    @NonNull
//                    @Override
//                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                        View view = super.getView(position, convertView, parent);
//
//                        Syllabus syllabus =  syllabusList.get(position);
////                        System.out.println(notes.getCurNote().toString());
////                        TextView tvMessage = view.findViewById(R.id.mesaj);
////                        if(notes.getCurNote().toString() == "LECTURE" )
////                            tvMessage.setTextColor(Color.GREEN);
////                        else
////                            System.out.println(notes.getCurNote().toString());
////                        if(notes.getCurNote().toString()== "LAB")
////                            tvMessage.setTextColor(Color.BLUE);
////                        else
////                            System.out.println(notes.getCurNote().toString());
////                        if(notes.getCurNote().toString() == "OTHERS")
////                            tvMessage.setTextColor(Color.RED);
//
//                        return view;
//
//                    }
//                };
//
//                listView.setAdapter(adapter);
//            }
//        }
//        else
//        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null) {
//            Syllabus syllabus = (Syllabus) data.getSerializableExtra(ADD_SYLLABUS);
//            {
//                if (syllabus!=null)
//                {
//                    syllabusList.get(poz).setNr(syllabus.getNr());
//                    syllabusList.get(poz).setCapacitate(syllabus.getCapacitate());
//                    syllabusList.get(poz).setSef(syllabus.getSef());
//
//                    SyllabusAdapter adapter = new SyllabusAdapter(getActivity(), R.layout.syllabus_listview,
//                            syllabusList, getLayoutInflater()){
//                        @NonNull
//                        @Override
//                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                            View view = super.getView(position, convertView, parent);
//
//                            Syllabus syllabus =  syllabusList.get(position);
//
////                            TextView tvMessage = view.findViewById(R.id.mesaj);
////                            if(notes.getCurNote().toString() == "LECTURE" )
////                                tvMessage.setTextColor(Color.GREEN);
////                            else if(notes.getCurNote().toString()== "LAB")
////                                tvMessage.setTextColor(Color.BLUE);
////                            else
////                            if(notes.getCurNote().toString() == "OTHERS")
////                                tvMessage.setTextColor(Color.RED);
//
//                            return view;
//                        }
//                    };
//                    listView.setAdapter(adapter);
//                }
//            }
//        }
//    }

}