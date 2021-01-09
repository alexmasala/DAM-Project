package ro.ase.proiect_draft;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);

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

            }

        });

        return view;
    }

}