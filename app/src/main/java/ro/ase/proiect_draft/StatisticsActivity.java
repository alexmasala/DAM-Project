package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static ro.ase.proiect_draft.SettingsFragment.IS_CHECKED;
import static ro.ase.proiect_draft.SettingsFragment.SAVE_SWITCH;

public class StatisticsActivity extends AppCompatActivity {

    private EditText nrCrediteMax;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static final String NR_CREDITE = "nrCredit";
    Button saveCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_statistics);
        nrCrediteMax = findViewById(R.id.ntCrediteEt);
        saveCredit = findViewById(R.id.btnSaveCredite);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        saveCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = preferences.edit();
                editor.putInt("nrCrediteNecesare", Integer.parseInt(nrCrediteMax.getText().toString()));
                editor.apply();
                editor.commit();
            }
        });
        int credite = preferences.getInt("nrCrediteNecesare", 0);
        nrCrediteMax.setText(String.valueOf(credite));
    }
}