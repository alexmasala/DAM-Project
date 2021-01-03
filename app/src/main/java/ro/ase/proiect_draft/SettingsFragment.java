package ro.ase.proiect_draft;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static final String SAVE_SWITCH = "saveSwitch";
    public static final String IS_CHECKED = "isChecked";
    private Switch switchTheme;
    private SharedPreferences sharedPreferences;
    private boolean darkMode = false;

    //Language setting
   // public static final String PREF_LANGUAGE = "language_pref";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        switchTheme = (Switch) view.findViewById(R.id.switchMode);
        //Spinner spLanguage = view.findViewById(R.id.spinnerSelectLanguage);
        //Array Adapter pt spinner
//        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
//                R.array.language_setting, R.layout.fragment_settings);
//        spLanguage.setAdapter(adapterLanguage);


        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(SAVE_SWITCH, Context.MODE_PRIVATE);

        loadFromSharedPreferences();
        switchTheme.setChecked(sharedPreferences.getBoolean(IS_CHECKED, false));
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //Scriere in fis SharedPreferences
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                darkMode = isChecked;
                storeToSharedPreferences();
            }
        });


        //Spinner Limba Setting
//
//        ArrayAdapter<String> adaptor = (ArrayAdapter<String>)spLanguage.getAdapter();
//        for(int i=0;i<adaptor.getCount();i++)
//            if(adaptor.getItem(i).equals(jnote.getNotetype()))
//            {
//                spLanguage.setSelection(i);
//                break;
//            }

       // Landscape  Setting


        return view;
    }

    //Citire din fis SharedPreferences Dark/Light mode
    private void loadFromSharedPreferences() {
        darkMode = sharedPreferences.getBoolean(IS_CHECKED, false);
        updateInterface();
    }

    private void storeToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_CHECKED, darkMode);
        editor.apply();
        editor.commit();
        updateInterface();
    }

    private void updateInterface() {
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.toggleTheme(darkMode);
        }
    }

//    //Setare limba SharedPreferences
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
//    }
//
//    @Override
//    public void onPause() {
//        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
//        super.onPause();
//    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if(key.equals("PREF_LANGUAGE"))
//        {
//            Preference connectionPref = findPreference(key);
//            connectionPref.setSummary(sharedPreferences.getString(key, ""));
//            changeLanguagePref( getActivity().getApplicationContext() ,sharedPreferences.getString(key, ""));
//        }
//
//    }
//
//    private void changeLanguagePref(Context context, String lang){
//        Locale locale = null;
//        if (lang.equals("English")){
//            locale = new Locale("en_");
//        }else if (lang.equals("Française")){
//            locale = new Locale("fr_");
//        }else{
//            locale = new Locale("sv_SE");
//        }
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        context.getResources().updateConfiguration(config, null);
//    }
//
//    public static void updateLanguage(Context context, String selectedLanguage) {
//        if (!"".equals(selectedLanguage)) {
//            if ("English".equals(selectedLanguage)) {
//                selectedLanguage = "en";
//            } else if ("Française".equals(selectedLanguage)) {
//                selectedLanguage = "fr";
//            }
//            else if ("Svenska".equals(selectedLanguage)) {
//                selectedLanguage = "sv_SE";
//            }
//            Locale locale = new Locale(selectedLanguage);
//            Locale.setDefault(locale);
//            Configuration config = new Configuration();
//            config.locale = locale;
//            context.getResources().updateConfiguration(config, null);
//        }
//    }
}