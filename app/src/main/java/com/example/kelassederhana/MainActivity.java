package com.example.kelassederhana;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    DatabaseReference userdatabase;
    String userdata,emailuser,namauser,status,id,kelas,bidang,bidangkelas;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");
        Intent receivedIntent = getIntent();
        if (receivedIntent.hasExtra("key")){
            String username= receivedIntent.getStringExtra("key");
            userdata=getIntent().getStringExtra("key");
            Log.d("My_DATA",username);
        }

        Bundle bundle = new Bundle();
        bundle.putString("user",userdata);
        HomeFragment homeFragment= new HomeFragment();
        homeFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame1,homeFragment);
        fragmentTransaction.commit();

        userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            NavigationView navigationView=findViewById(R.id.nav_view);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FloatingActionButton fab = findViewById(R.id.fab);
                User User = dataSnapshot.child(userdata).getValue(User.class);
                kelas=User.getKelas();
                bidang=User.getBidang();
                emailuser=User.getEmail();
                namauser=User.getNama();
                id=User.getId();
                status=User.getStatus();
                bidangkelas=User.getBidangkelas();
                if (status.equals("Siswa")){
                    fab.hide();
                }
                View header= navigationView.getHeaderView(0);
                TextView nama= header.findViewById(R.id.nama);
                TextView email=header.findViewById(R.id.email);
                nama.setText(namauser);
                email.setText(emailuser);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, matapelajaran.class);
                        String user = userdata;
                        intent.putExtra("key", user);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting){
            Intent intent = new Intent(MainActivity.this, setting.class);
            String user = userdata;
            intent.putExtra("key", user);
            startActivity(intent);
        } else if(id == R.id.nav_keluar){
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
