package com.example.dllnews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button reg,login;
    private EditText email,password,name;
    private DatabaseReference databaseReference;
    String mail,pass,nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email= (EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.pass);
        reg=(Button)findViewById(R.id.register);
        name=(EditText)findViewById(R.id.name);
        //login=(Button)findViewById(R.id.login);

        databaseReference= FirebaseDatabase.getInstance().getReference();



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mail=email.getText().toString().trim();
                pass=password.getText().toString().trim();
                nm=name.getText().toString().trim();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!(dataSnapshot.child("Users").child(mail).exists())) {



                            HashMap<String, Object> hashMap = new HashMap<String, Object>();
                            hashMap.put("Email", mail);
                            hashMap.put("pasword", pass);
                            hashMap.put("Name",nm);

                            databaseReference.child("Users").child(mail).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),NewsActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Id allready Exists",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
