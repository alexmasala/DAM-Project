package ro.ase.proiect_draft;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button showExams;
    Button statistics, logOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        statistics = view.findViewById(R.id.btnStatistics);
        logOut = view.findViewById(R.id.btnLogOut);

        showExams = view.findViewById(R.id.btnShowExam);
        showExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent examIntent = new Intent(getActivity(), ExamListActivity.class);
                startActivity(examIntent);
               // startActivity(new Intent(getActivity().getApplicationContext(), ExamListActivity.class));
            }
        });


        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statInt = new Intent(getActivity(), StatisticsActivity.class);
                startActivity(statInt);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }

}