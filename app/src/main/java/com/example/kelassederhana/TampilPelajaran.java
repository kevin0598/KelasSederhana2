package com.example.kelassederhana;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TampilPelajaran extends AppCompatActivity implements PelajranHolder.ItemClickListener {

    static FirebaseDatabase database=FirebaseDatabase.getInstance();
    static DatabaseReference databaseReference=database.getReference();

    List<POJOmatapelajaran> pojOmatapelajaransarray=new ArrayList<>();

    PelajaranAdapter pelajaranAdapter;
    RecyclerView view1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        //tampilkan();
    }



    @Override
    public void onItemClick(View view, int position) {

    }
}
