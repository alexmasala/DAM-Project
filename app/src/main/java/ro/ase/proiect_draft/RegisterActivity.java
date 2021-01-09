package ro.ase.proiect_draft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText password, email, faculty, specialization, firstName, lastName;
    private Button signUp;
    private TextView existingUser;
    private ProgressBar progbar;
     private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUp = findViewById(R.id.btnSignUp);
        existingUser = findViewById(R.id.AlreadyRegistered);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.Email);
        faculty = findViewById(R.id.faculty);
        specialization = findViewById(R.id.specialization);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        progbar =  findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        existingUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void registerUser(){
        String emailEt = email.getText().toString().trim();
        String passwordEt = password.getText().toString().trim();
        String facultyEt = faculty.getText().toString().trim();
        String specializtionEt = specialization.getText().toString().trim();
        String firstNameEt = firstName.getText().toString().trim();
        String lastNameEt = lastName.getText().toString().trim();

        if(firstNameEt.isEmpty())
            Toast.makeText(this, "This field is requried!",
                    Toast.LENGTH_SHORT).show();

        if(lastNameEt.isEmpty())
            Toast.makeText(this, "This field is requried!",
                    Toast.LENGTH_SHORT).show();

        if(passwordEt.isEmpty())
            Toast.makeText(this, "This field is requried!",
                    Toast.LENGTH_SHORT).show();

        if(facultyEt.isEmpty())
            Toast.makeText(this, "This field is requried!",
                    Toast.LENGTH_SHORT).show();

        if(specializtionEt.isEmpty())
            Toast.makeText(this, "This field is requried!",
                    Toast.LENGTH_SHORT).show();

        if(emailEt.isEmpty())
            Toast.makeText(this, "This field is requried!",
                    Toast.LENGTH_SHORT).show();

        if(!Patterns.EMAIL_ADDRESS.matcher(emailEt).matches())
            Toast.makeText(this, "Provide a valid email",
                    Toast.LENGTH_SHORT).show();

        if(passwordEt.length() < 6)
            Toast.makeText(this, "Password should be at least 6 characters!",
                    Toast.LENGTH_SHORT).show();

        progbar.setVisibility(View.VISIBLE);

        //Creare si inserare user Adaugare user in firebase
        mAuth.createUserWithEmailAndPassword(emailEt, passwordEt)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  User user = new User(lastNameEt, firstNameEt, facultyEt, specializtionEt, emailEt, passwordEt);

                  FirebaseDatabase.getInstance().getReference("Users")
                          .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                          .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(RegisterActivity.this,
                                       "User has been registered successfully!", Toast.LENGTH_SHORT).show();
                              progbar.setVisibility(View.GONE);
                              startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                          } else{
                              Toast.makeText(RegisterActivity.this, "Faild to register!", Toast.LENGTH_SHORT).show();
                              progbar.setVisibility(View.GONE);
                          }
                      }
                  });
              } else{
                  Toast.makeText(RegisterActivity.this, "Faild to register!", Toast.LENGTH_SHORT).show();
                  progbar.setVisibility(View.GONE);
              }
            }
        });
    }
}