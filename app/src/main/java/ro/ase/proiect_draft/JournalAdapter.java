package ro.ase.proiect_draft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class JournalAdapter extends ArrayAdapter<JournalNote> {
    private Context context;
    private int resource;
    private List<JournalNote> notesList;
    private LayoutInflater layoutInflater;

    public JournalAdapter(@NonNull Context context, int resource,
                          List<JournalNote> notesList, LayoutInflater layoutInflater) {
        super(context, resource, notesList);
        this.context = context;
        this.resource = resource;
        this.notesList = notesList;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        JournalNote notes = notesList.get(position);

        if (notes!=null)
        {
            TextView id1 = view.findViewById(R.id.titlu);
            id1.setText(notes.getTitle());

            TextView id2 = view.findViewById(R.id.data);
            id2.setText(notes.getData().toString());

            TextView id3 = view.findViewById(R.id.mesaj);
            id3.setText(notes.getMessage());

            TextView tv4 = view.findViewById(R.id.tipNotita);
            tv4.setText(String.valueOf(notes.getNotetype()));

            TextView tv5 = view.findViewById(R.id.curricularNote);
            tv5.setText(notes.getCurNote().toString());
        }

        return view;
    }

}
