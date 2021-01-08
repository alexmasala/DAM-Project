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

public class ExamAdapter extends ArrayAdapter<Exam> {
    private Context context;
    private int resource;
    private List<Exam> examList;
    private LayoutInflater layoutInflater;
    public ExamAdapter(@NonNull Context context, int resource,
                          List<Exam> examList, LayoutInflater layoutInflater) {
        super(context, resource, examList);
        this.context = context;
        this.resource = resource;
        this.examList = examList;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Exam notes = examList.get(position);

        if (notes!=null)
        {
            TextView id1 = view.findViewById(R.id.titlu);
            id1.setText(notes.getNumeMaterie());

            TextView id2 = view.findViewById(R.id.data);
            id2.setText(String.valueOf(notes.getNumarCredite()));

            TextView id3 = view.findViewById(R.id.mesaj);
            id3.setText(notes.getTipExam());

            TextView tv4 = view.findViewById(R.id.tipNotita);
            tv4.setText(notes.getOra());

            TextView tv5 = view.findViewById(R.id.data);
            tv5.setText(notes.getDataSustinere().toString());

            TextView tv6 = view.findViewById(R.id.curricularNote);
            tv6.setText(String.valueOf(notes.getDurataOre()));
        }

        return view;
    }
}
