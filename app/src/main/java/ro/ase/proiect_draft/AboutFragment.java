package ro.ase.proiect_draft;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }


//    @Bind(R.id.textViewLink)
    TextView linkText;
    Button rating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);
        linkText = view.findViewById(R.id.textViewLink);
        linkText.setMovementMethod(LinkMovementMethod.getInstance());
        rating = view.findViewById(R.id.btnRating);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            linkText.setText(Html.fromHtml("<a href=https://google.com> Read more here</a>", Html.FROM_HTML_MODE_LEGACY));
        }
        else {
            linkText.setText(Html.fromHtml("<a href=https://google.com> Read more here</a>"));
        }

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateInt = new Intent(getActivity(), RateActivity.class);
                startActivity(rateInt);
            }
        });


        return view;
    }
}