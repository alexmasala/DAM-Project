package ro.ase.proiect_draft;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ExamFragment extends Fragment {

    Button showExams;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_exam, container, false);

        showExams = view.findViewById(R.id.btnShowExam);
        showExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent examIntent = new Intent(getActivity(), ExamListActivity.class);
                startActivity(examIntent);
            }
        });


        return view;
    }
}