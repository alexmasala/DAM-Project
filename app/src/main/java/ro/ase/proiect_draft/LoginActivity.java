package ro.ase.proiect_draft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText email, passw;
    private Button login;
    private TextView newHere;
    private CheckBox rememberMe;
    SharedPreferences shp;
    SharedPreferences.Editor editor;
    private FirebaseDatabase firebaseUser;
    private DataSnapshot dataSnapshot = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogIn);
        newHere = findViewById(R.id.newHere);
        email = findViewById(R.id.email3);
        passw = findViewById(R.id.password3);
        rememberMe = findViewById(R.id.chbRemember);
        firebaseUser = FirebaseDatabase.getInstance();

        //Autentificare utilizator

        //Cand aplicatia se deschide va cauta fisierul sharedpreferances
        SharedPreferences shp = getSharedPreferences( " checkbox", MODE_PRIVATE);
        String checkbox = shp.getString("remember", "");

        if(checkbox.equals("true")){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){

            Toast.makeText(this, "Please log in", Toast.LENGTH_SHORT).show();
        }



        login.setOnClickListener(new View.OnClickListener() {
            //Preluare date existente din firebase
            //Verificarea datelor
          boolean semafor = loginUserAccount();
            @Override
            public void onClick(View v) {
                if(semafor){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else Toast.makeText(LoginActivity.this, "Log In Failed", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();

                } else if( !compoundButton.isChecked()){

                    SharedPreferences shp= getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shp.edit();
                    editor.putString("rememberMe", "false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean loginUserAccount(){

        DatabaseReference ref = firebaseUser.getReference("pagina-personala-student-default-rtdb");
        String emailEt = email.getText().toString();
        String passwordEt = passw.getText().toString();
        boolean sem = false;

        //Validare email, parola
        if (TextUtils.isEmpty(emailEt)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (TextUtils.isEmpty(passwordEt)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        ref.child("table-user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        User user = dataSnapshot.getValue(User.class);
        if ((user.getEmail().equals(emailEt) && (user.getPassword().equals(passwordEt)))){
            sem = true;
        }

        return sem;
    }
}