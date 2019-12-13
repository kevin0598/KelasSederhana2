package com.example.kelassederhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference userdatabase;
    TextView view1,view2,view3;
    EditText text1,text2,text3,text4,text5;
    Spinner spinner1,spinner2,spinner3;
    RadioGroup grup1;
    Button button1,button2;
    String[] status={"Siswa","Guru"};
    String[] kelas={"Kelas X","Kelas XI","Kelas XII"};
    String[] bidang={"IPA","IPS"};
    RadioButton radio1;
    //FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //firebaseFirestore=FirebaseFirestore.getInstance();
        view1=(TextView) findViewById(R.id.view1);
        view2=(TextView) findViewById(R.id.view2);
        view3=(TextView) findViewById(R.id.view3);
        text1=(EditText) findViewById(R.id.nama);
        text2=(EditText) findViewById(R.id.user);
        text3=(EditText) findViewById(R.id.password);
        text4=(EditText) findViewById(R.id.konfirmasi);
        text5=(EditText) findViewById(R.id.email);
        spinner1=(Spinner) findViewById(R.id.status);
        spinner2=(Spinner) findViewById(R.id.kelas);
        spinner3=(Spinner) findViewById(R.id.bidang);
        button1=(Button) findViewById(R.id.batal);
        button2=findViewById(R.id.simpan);
        grup1=(RadioGroup) findViewById(R.id.grup1);
        ArrayAdapter<String> a= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,status);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(a);
        ArrayAdapter<String> b= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,kelas);
        b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(b);
        ArrayAdapter<String> c= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,bidang);
        c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(a);
        spinner2.setAdapter(b);
        spinner3.setAdapter(c);
        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batal();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadUser();
            }
        });
    }

    private void Batal(){
        finish();
    }

    private void UploadUser(){
        if(text1.getText().toString().length()==0){
            text1.setError("Tidak boleh kosong");
        }else {
            if(text2.getText().toString().length()==0){
                text2.setError("Tidak boleh kosong");
            }else {
                if(text5.getText().toString().length()==0){
                    text5.setError("Tidak boleh kosong");
                }else {
                    if(text3.getText().toString().length()==0){
                        text3.setError("Tidak boleh kosong");
                    }else {
                        if (text3.getText().toString().length() <8){
                            text3.setError("Password harus lebih dari 8");
                        } else {
                            if(text4.getText().toString().length()==0){
                                text4.setError("Tidak boleh kosong");
                            }else {
                                if (grup1.getCheckedRadioButtonId()==-1){
                                    Toast.makeText(RegisterActivity.this, "Jenis Kelamin Harus Pilih salah satu", Toast.LENGTH_SHORT).show();
                                }else {
                                    userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.child(text2.getText().toString()).exists()) {
                                                Toast.makeText(RegisterActivity.this, "Data Sudah Ada", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (text3.getText().toString().equals(text4.getText().toString())) {
                                                    final int selectedid = grup1.getCheckedRadioButtonId();
                                                    radio1 = (RadioButton) findViewById(selectedid);
                                                    String Nomor,posisi,kelas,bidang;
                                                    User userid = new User();
                                                    Nomor = text2.getText().toString();
                                                    posisi = spinner1.getSelectedItem().toString();
                                                    kelas = spinner2.getSelectedItem().toString();
                                                    bidang = spinner3.getSelectedItem().toString();
                                                    userid.setNama(text1.getText().toString());
                                                    userid.setId(text2.getText().toString());
                                                    userid.setPass1(text3.getText().toString());
                                                    userid.setPass2(text4.getText().toString());
                                                    userid.setEmail(text5.getText().toString());
                                                    userid.setStatus(posisi);
                                                    userid.setKelas(kelas);
                                                    userid.setBidang(bidang);
                                                    userid.setJk(radio1.getText().toString());
                                                    userid.setBidangkelas(kelas+" "+bidang);
                                                    userdatabase.child(Nomor).setValue(userid);
                                                    Toast.makeText(RegisterActivity.this, "Data Berhasil di input", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
