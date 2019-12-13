package com.example.kelassederhana;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    TextView textview;
    Button button1,button2;
    EditText text1,text2;
    ImageView image1;
    CheckBox checkBox;
    DatabaseReference userdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");
        textview=findViewById(R.id.textView1);
        image1=findViewById(R.id.logo);
        text1=findViewById(R.id.id);
        text2=findViewById(R.id.password);
        checkBox=findViewById(R.id.tampilkan);
        button1=findViewById(R.id.register);
        button2=findViewById(R.id.login);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    text2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    text2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    register();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(text1.getText().toString().length()==0){
                    text1.setError("Tidak boleh kosong");
                }else {
                    if(text2.getText().toString().length()==0){
                        text2.setError("Tidak boleh kosong");
                    }else {
                        userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(text1.getText().toString()).exists()){
                                    User User = dataSnapshot.child(text1.getText().toString()).getValue(User.class);
                                    if (User.getPass1().equals(text2.getText().toString())){
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        String user = text1.getText().toString();
                                        intent.putExtra("key", user);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, "Berhasil Log in", Toast.LENGTH_SHORT).show();
                                    } else{
                                        Toast.makeText(LoginActivity.this,"Password anda salah",Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    Toast.makeText(LoginActivity.this, "Data Belum Ada", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });
        }

    private void register(){
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    };


}
