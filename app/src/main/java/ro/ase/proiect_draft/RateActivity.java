package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RatingBar;

public class RateActivity extends AppCompatActivity {

    public static final String RATING = "rate";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    RatingBar rateUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rateUs = findViewById(R.id.ratingBar);

        rateUs.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                final int numStars = ratingBar.getNumStars();
                editor = preferences.edit();
                editor.putFloat("numStars", rating);
                editor.apply();
                editor.commit();
                int ratings = preferences.getInt("numStars", 0);
                //rate.setText(rating + "/" + String.valueOf(ratings));
            }
        });
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        float rating = preferences.getFloat("numStars", 0f);
        rateUs.setRating(rating);

    }
}

//        rateUs.getOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
//            @Override
//            public  void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
//                int numStars = ratingBar.getNumStars();
//                editor = preferences.edit();
//                editor.putFloat("numStars", rating);
//                editor.commit();
//                int ratings = preferences.getInt("numStars", 0);
//            }
//        });
//
//        preferences = getApplicationContext().getSharedPreferences(RATING, MODE_PRIVATE);
//        editor = preferences.edit();
//
//    }