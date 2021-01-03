package ro.ase.proiect_draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText email, passw;
    private Button login;
    private TextView newHere;
    private CheckBox rememberMe;
    SharedPreferences shp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogIn);
        newHere = findViewById(R.id.newHere);
        email = findViewById(R.id.email3);
        passw = findViewById(R.id.password3);

        //Autentificare utilizator

        //Cand aplicatia se deschide va cauta fisierul sharedpreferances
        SharedPreferences shp = getSharedPreferences( " checkbox", MODE_PRIVATE);
        String checkbox = shp.getString("remember", "");

        if(checkbox.equals("true")){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            Toast.makeText(this, "Please log in", Toast.LENGTH_SHORT).show();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if( compoundButton.isChecked()){

                    SharedPreferences shp= getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shp.edit();
                    editor.putString("rememberMe", "true");
                    editor.apply();
                    Toast.makeText(Login.this, "Checked", Toast.LENGTH_SHORT).show();

                } else if( !compoundButton.isChecked()){

                    SharedPreferences shp= getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shp.edit();
                    editor.putString("rememberMe", "false");
                    editor.apply();
                    Toast.makeText(Login.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}