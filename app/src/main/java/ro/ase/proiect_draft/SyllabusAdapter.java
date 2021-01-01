package ro.ase.proiect_draft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ro.ase.proiect_draft.data.Syllabus;

public class SyllabusAdapter extends ArrayAdapter<Syllabus> {

    private Context context;
    private int resource;
    private List<Syllabus> syllabusList;
    private LayoutInflater layoutInflater;


    public SyllabusAdapter(@NonNull Context context, int resource, List<Syllabus> syllabusList, LayoutInflater layoutInflater) {
        super(context, resource, syllabusList);
        this.context = context;
        this.resource = resource;
        this.syllabusList = syllabusList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Syllabus syllabus = syllabusList.get(position);

        if (syllabus!=null)
        {
            TextView tv1 = view.findViewById(R.id.nr);
            tv1.setText(String.valueOf(syllabus.getNr()));

            TextView tv2 = view.findViewById(R.id.capacitate);
            tv2.setText(String.valueOf(syllabus.getCapacitate()));

            TextView tv3 = view.findViewById(R.id.sef);
            tv3.setText(syllabus.getSef());

            TextView tv4 = view.findViewById(R.id.tip);
            tv4.setText(syllabus.getTip());

            TextView tv5 = view.findViewById(R.id.zi);
            tv5.setText(syllabus.getZi());

            TextView tv6 = view.findViewById(R.id.ora);
            tv6.setText(syllabus.getOra());

            TextView tv7 = view.findViewById(R.id.nume);
            tv7.setText(syllabus.getNume());

            TextView tv8 = view.findViewById(R.id.durata);
            tv8.setText(syllabus.getDurata());

            TextView tv9 = view.findViewById(R.id.profesor);
            tv9.setText(syllabus.getProfesor());
        }

        return view;
    }
}
