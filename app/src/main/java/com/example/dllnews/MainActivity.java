package com.example.dllnews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllnews.Model.Users;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button reg,login;
    private EditText userid,pass;
    private String Email;
    private String pasword;
    private String parentDbname="Users";
    private TextView newuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userid=(EditText)findViewById(R.id.user_email);
        pass=(EditText)findViewById(R.id.user_password);
        login=(Button)findViewById(R.id.login);
        newuser=(TextView)findViewById(R.id.newuser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

     /*   Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });  */

    }
    private void loginUser(){
        Email=userid.getText().toString().trim();
        pasword=pass.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Write your name",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(pasword)){
            Toast.makeText(this,"Write your password",Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(this,"Try Again",Toast.LENGTH_LONG).show();
            allowUser();
        }
    }
    private void allowUser(){
        //Toast.makeText(this,"HIIIIIIII",Toast.LENGTH_LONG).show();
        final DatabaseReference RootFef;

        RootFef= FirebaseDatabase.getInstance().getReference();
        RootFef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbname).child(Email).exists()){
                    //Toast.makeText(LoginActivity.this,"Hiiiiiiii",Toast.LENGTH_SHORT).show();
                    Users usersdata=dataSnapshot.child(parentDbname).child(Email).getValue(Users.class);
                    if (usersdata.getEmail().equals(Email)){
                        if (usersdata.getPasword().equals(pasword)){
                            Toast.makeText(MainActivity.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();

                           Intent intent=new Intent(getApplicationContext(),NewsActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Account with this name Does not exists",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
