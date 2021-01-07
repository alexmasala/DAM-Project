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

    public ExamAdapter(@NonNull Context context, int resource, List<Exam> examList, LayoutInflater layoutInflater) {
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
        Exam exams = examList.get(position);

        if (exams!=null)
        {
            TextView tv1 = view.findViewById(R.id.numeMaterie);
            tv1.setText(exams.getNumeMaterie());

            TextView tv2 = view.findViewById(R.id.numarCredite);
            tv2.setText(String.valueOf(exams.getNumarCredite()));

            TextView tv3 = view.findViewById(R.id.dataSustinere);
            tv3.setText(exams.getDataSustinere().toString());

            TextView tv4 = view.findViewById(R.id.ora);
            tv4.setText(exams.getOra());

            TextView tv5 = view.findViewById(R.id.durataOre);
            tv5.setText(exams.getDurataOre());
        }

        return view;
    }
}
