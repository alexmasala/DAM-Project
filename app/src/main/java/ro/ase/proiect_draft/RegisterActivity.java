package ro.ase.proiect_draft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText password, email, faculty, specialization, firstName, lastName;
    private Button signUp;
    private TextView existingUser;
    private FirebaseDatabase firebaseUser;


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
        firebaseUser = FirebaseDatabase.getInstance();

        //Firebase tabela user

        //Creare user Adaugare user in firebase
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(email.getText().toString() , password.getText().toString());
                salvareFireBase(user);
                //Salvare in room tabela student

                //Redirectionare LogIn Activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //Baza de date room tabela studenti

        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //Functie de salvare in FireBase
    public  void salvareFireBase( User user){
        DatabaseReference refFireBase = firebaseUser.getReference("Users");
       // DatabaseReference refChild =  refFireBase.getRef("pagina-personala-student-default-rtdb");
        refFireBase.keepSynced(true);
        user.setIdUser(refFireBase.child("Users").push().getKey());
        refFireBase.child("Users").child(user.getIdUser()).setValue(user);
        Toast.makeText(RegisterActivity.this, "Registered User", Toast.LENGTH_SHORT).show();
//        refFireBase.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user.setIdUser(refFireBase.child("pagina-personala-student-default-rtdb").push().getKey());
//                refFireBase.child("pagina-personala-student-default-rtdb").child(user.getIdUser()).setValue(user);
//                Toast.makeText(RegisterActivity.this, "Registered User", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}