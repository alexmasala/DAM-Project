package ro.ase.proiect_draft;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    //Constructor cu parametrii
//    public SettingsFragment(SharedPreferences sp){
//        this.sharedPreferences = sp;
//       // this.switchTheme = sw;
//    }
    public static final String SAVE_SWITCH = "saveSwitch";
    public static final String IS_CHECKED = "isChecked";
    Switch switchTheme;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        switchTheme = (Switch) view.findViewById(R.id.switchMode);
        sharedPreferences = getActivity().getApplicationContext()
                .getSharedPreferences( SAVE_SWITCH , Context.MODE_PRIVATE);

       loadFromSharedPreferences();
        switchTheme.setChecked(sharedPreferences.getBoolean(IS_CHECKED,false));
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //Scriere in fis SharedPreferences
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                  //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean(IS_CHECKED,true);
                    switchTheme.setChecked(true);
                    editor.apply();
                    ((MainActivity) getActivity()).ToggleTheme(isChecked);
                }
                else{
                  //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean(IS_CHECKED, false);
                    switchTheme.setChecked(false);
                    editor.apply();
                    ((MainActivity) getActivity()).ToggleTheme(isChecked);
                }
            }
        });


        //Spinner Limba Setting
//        Spinner spLanguage = view.findViewById(R.id.spinnerSelectLanguage);
//        //Array Adapter pt spinner
//        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
//                R.array.language_setting, R.layout.fragment_settings);
//        spLanguage.setAdapter(adapterLanguage);

        //Landscape  Setting


        return view;
    }
    //Citire din fis SharedPreferences
    public void loadFromSharedPreferences() {

        boolean ischecked = sharedPreferences.getBoolean(IS_CHECKED, false);
        //sw.setChecked(ischecked);
        ((MainActivity) getActivity()).ToggleTheme(ischecked);
    }
//    public void loadFromSharedPreferences(SharedPreferences sp ) {
//
//        boolean ischecked = sp.getBoolean(IS_CHECKED, false);
//        //sw.setChecked(ischecked);
//        ((MainActivity) getActivity()).ToggleTheme(ischecked);
//    }
}