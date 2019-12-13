package com.example.kelassederhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class matapelajaran extends AppCompatActivity {
    String userdata;
    private DatabaseReference userdatabase,userdatabase1;
    TextView view;
    EditText text1,text2;
    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matapelajaran);

        view=findViewById(R.id.view1);
        text1=findViewById(R.id.kodematapelajaran);
        text2=findViewById(R.id.namapelajaran);
        button1=findViewById(R.id.batal);
        button2=findViewById(R.id.simpan);
        userdatabase = FirebaseDatabase.getInstance().getReference().child("MataPelajaran");
        userdatabase1 = FirebaseDatabase.getInstance().getReference().child("User");

        Intent receivedIntent = getIntent();

        if (receivedIntent.hasExtra("key")){
            String username= receivedIntent.getStringExtra("key");
            userdata=getIntent().getStringExtra("key");
            Log.d("My_DATA",username);
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batal();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });
    }

    private void tambah(){
        if(text1.getText().toString().length()==0){
            text1.setError("Tidak boleh kosong");
        }else {
            if(text2.getText().toString().length()==0){
                text2.setError("Tidak boleh kosong");
            }else {
                userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userdatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String matkul,namaguru,kelas,bidang,idguru;
                                User user = dataSnapshot.child(userdata).getValue(User.class);
                                matkul=text1.getText().toString();
                                idguru=user.getId();
                                namaguru=user.getNama();
                                kelas=user.getKelas();
                                bidang=user.getBidang();
                                POJOmatapelajaran pojOmatapelajaran=new POJOmatapelajaran();
                                pojOmatapelajaran.setKode(text1.getText().toString());
                                pojOmatapelajaran.setNamapelajaran(text2.getText().toString());
                                pojOmatapelajaran.setIdguru(idguru);
                                pojOmatapelajaran.setNamaguru(namaguru);
                                pojOmatapelajaran.setKelas(kelas);
                                pojOmatapelajaran.setBidang(bidang);
                                pojOmatapelajaran.setBidangkelas(kelas+" "+bidang);
                                userdatabase.child(matkul).setValue(pojOmatapelajaran);
                                Toast.makeText(matapelajaran.this, "Data Berhasil di input", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }

    }

    private void  batal(){
        finish();
    }

}
