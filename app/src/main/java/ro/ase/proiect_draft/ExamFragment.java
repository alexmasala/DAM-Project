package ro.ase.proiect_draft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ro.ase.proiect_draft.Add_Exam_Activity.ADD_EXAM;

public class ExamFragment extends Fragment {

    public static final String EDIT_EXAM = "editExam";
    private FloatingActionButton floatingActionButton;
    private Intent intent;
    public static final int REQUEST_CODE = 200;

    public static final int REQUEST_CODE_EDIT = 300;

    public int poz;

    private ListView listView;
    List<Exam> examList = new ArrayList<Exam>();

    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        listView = (ListView) view.findViewById(R.id.listViewExam);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                intent = new Intent(getActivity().getApplicationContext(), Add_Exam_Activity.class);
                Serializable data = intent.getSerializableExtra(Add_Exam_Activity.ADD_EXAM);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getActivity().getApplicationContext(), Add_Exam_Activity.class);
                intent.putExtra(EDIT_EXAM, examList.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Exam exams = examList.get(position);

                final ExamAdapter adapter = (ExamAdapter) listView.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Confirmare stergere")
                        .setMessage("Sigur doriti stergerea?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "Nu s-a sters nimic!",
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                examList.remove(exams);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "S-a sters examenul: "+exams.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Exam exams = (Exam) data.getSerializableExtra(ADD_EXAM);

            if (exams != null) {

                examList.add(exams);


                ExamAdapter adapter = new ExamAdapter(getActivity(), R.layout.exam_listview,
                        examList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Exam exams =  examList.get(position);
//                        System.out.println(notes.getCurNote().toString());
//                        TextView tvMessage = view.findViewById(R.id.mesaj);
//                        if(notes.getCurNote().toString() == "LECTURE" )
//                            tvMessage.setTextColor(Color.GREEN);
//                        else
//                            System.out.println(notes.getCurNote().toString());
//                        if(notes.getCurNote().toString()== "LAB")
//                            tvMessage.setTextColor(Color.BLUE);
//                        else
//                            System.out.println(notes.getCurNote().toString());
//                        if(notes.getCurNote().toString() == "OTHERS")
//                            tvMessage.setTextColor(Color.RED);

                        return view;

                    }
                };

                listView.setAdapter(adapter);
            }
        }
        else
        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null) {
            Exam exams = (Exam) data.getSerializableExtra(ADD_EXAM);
            {
                if (exams!=null)
                {
                    examList.get(poz).setNumeMaterie(exams.getNumeMaterie());
                    examList.get(poz).setNumarCredite(exams.getNumarCredite());
                    examList.get(poz).setDataSustinere(exams.getDataSustinere());
                    examList.get(poz).setOra(exams.getOra());
                    examList.get(poz).setDurataOre(exams.getDurataOre());

                    ExamAdapter adapter = new ExamAdapter(getActivity(), R.layout.exam_listview,
                            examList, getLayoutInflater()){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            Exam exams =  examList.get(position);

//                            TextView tvMessage = view.findViewById(R.id.mesaj);
//                            if(notes.getCurNote().toString() == "LECTURE" )
//                                tvMessage.setTextColor(Color.GREEN);
//                            else if(notes.getCurNote().toString()== "LAB")
//                                tvMessage.setTextColor(Color.BLUE);
//                            else
//                            if(notes.getCurNote().toString() == "OTHERS")
//                                tvMessage.setTextColor(Color.RED);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);
                }
            }
        }
    }
}

