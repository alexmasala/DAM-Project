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
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static ro.ase.proiect_draft.Add_Journal_Note_Activity.ADD_JNOTE;

public class MyJournalFragment extends Fragment {

    public MyJournalFragment() {
        // Required empty public constructor
    }


    private FloatingActionButton floatingActionButton;
    private Intent intent;
    public static final int REQUEST_CODE = 200;

    public static final int REQUEST_CODE_EDIT = 300;

    public static final String EDIT_JNOTE = "editJNote";

    public int poz;

    private ListView listView;
    List<JournalNote> notesList = new ArrayList<JournalNote>();

    //Ce era in onCreate ~= onCreateView
    //nu E setOnContentView pt fragmente, ai alte metode
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_journal, container, false);

        //in activity la seminar era pus in onCreate, dupa startActivity
        listView = (ListView) view.findViewById(R.id.listView);
        //set the adapter, etc

      //  FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

         //de ce punem intentul in onClickView?
            //Aici punem intentul si start activity
         //this is what runs when you click the button
            @Override
            public void onClick(View view) {

                intent = new Intent(getActivity().getApplicationContext(), Add_Journal_Note_Activity.class);
                Serializable data = intent.getSerializableExtra(Add_Journal_Note_Activity.ADD_JNOTE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getActivity().getApplicationContext(), Add_Journal_Note_Activity.class);
                intent.putExtra(EDIT_JNOTE, notesList.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                final JournalNote notes = notesList.get(position);

                final JournalAdapter adapter = (JournalAdapter) listView.getAdapter();

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
                                notesList.remove(notes);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "S-a sters filmul: "+notes.toString(),
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
            JournalNote notes = (JournalNote) data.getSerializableExtra(Add_Journal_Note_Activity.ADD_JNOTE);

            if (notes != null) {

                notesList.add(notes);

                JournalAdapter adapter = new JournalAdapter(getActivity(), R.layout.journal_listview,
                        notesList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        JournalNote notes =  notesList.get(position);
                        System.out.println(notes.getCurNote().toString());
                        TextView tvMessage = view.findViewById(R.id.mesaj);
                        if(notes.getCurNote().toString() == "LECTURE" )
                            tvMessage.setTextColor(Color.GREEN);
                        else
                            System.out.println(notes.getCurNote().toString());
                          if(notes.getCurNote().toString()== "LAB")
                            tvMessage.setTextColor(Color.BLUE);
                       else
                              System.out.println(notes.getCurNote().toString());
                        if(notes.getCurNote().toString() == "OTHERS")
                            tvMessage.setTextColor(Color.RED);

                        return view;

                    }
                };

                listView.setAdapter(adapter);
            }
        }
        else
        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null) {
            JournalNote notes = (JournalNote) data.getSerializableExtra(ADD_JNOTE);
            {
                if (notes!=null)
                {
                    notesList.get(poz).setTitle(notes.getTitle());
                    notesList.get(poz).setData(notes.getData());
                    notesList.get(poz).setMessage(notes.getMessage());
                    notesList.get(poz).setNotetype(notes.getNotetype());
                    notesList.get(poz).setCurNote(notes.getCurNote());

                    JournalAdapter adapter = new JournalAdapter(getActivity(), R.layout.journal_listview,
                            notesList, getLayoutInflater()){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            JournalNote notes =  notesList.get(position);

                            TextView tvMessage = view.findViewById(R.id.mesaj);
                            if(notes.getCurNote().toString() == "LECTURE" )
                                tvMessage.setTextColor(Color.GREEN);
                         else if(notes.getCurNote().toString()== "LAB")
                                tvMessage.setTextColor(Color.BLUE);
                         else
                         if(notes.getCurNote().toString() == "OTHERS")
                             tvMessage.setTextColor(Color.RED);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);
                }
            }
        }
    }
}