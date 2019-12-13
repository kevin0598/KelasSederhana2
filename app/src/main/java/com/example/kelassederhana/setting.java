package com.example.kelassederhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class setting extends AppCompatActivity {
    DatabaseReference userdatabase;
    String userdata,emailuser,namauser,bidang,kelas,status,id,jk,kelasuser,user;
    EditText text1,text2;
    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        text1=findViewById(R.id.password);
        text2=findViewById(R.id.konfirmasi);
        button1=findViewById(R.id.batal);
        button2=findViewById(R.id.simpan);

        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batal();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(text1.getText().toString().length()==0){
                    text1.setError("Tidak boleh kosong");
                }else {
                    if (text1.getText().toString().length() <8){
                        text1.setError("Password harus lebih dari 8");
                    } else {
                        if(text2.getText().toString().length()==0){
                            text2.setError("Tidak boleh kosong");
                        }else {
                            update();
                        }
                    }
                }
            }
        });

    }

    private void update(){
        Intent receivedIntent = getIntent();

        if (receivedIntent.hasExtra("key")){
            final User userid = new User();
            final String username= receivedIntent.getStringExtra("key");
            userdata=getIntent().getStringExtra("key");
            Log.d("My_DATA",username);
            userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User User = dataSnapshot.child(userdata).getValue(User.class);
                    emailuser=User.getEmail();
                    namauser=User.getNama();
                    bidang=User.getBidang();
                    kelas=User.getKelas();
                    status=User.getStatus();
                    id=User.getId();
                    jk=User.getJk();
                    kelasuser=User.getKelas();
                    user=User.getUser();
                    if (text1.getText().toString().equals(text2.getText().toString())) {
                        userid.setNama(namauser);
                        userid.setId(id);
                        userid.setPass1(text1.getText().toString());
                        userid.setPass2(text2.getText().toString());
                        userid.setEmail(emailuser);
                        userid.setStatus(status);
                        userid.setKelas(kelas);
                        userid.setBidang(bidang);
                        userid.setJk(jk);
                        userdatabase.child(username).setValue(userid);
                        Toast.makeText(setting.this, "Password berhasil di ubah", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(setting.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void batal(){
        finish();
    }

}
