package com.example.kelassederhana;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HalamanMain extends AppCompatActivity {
    private TextView mTextMessage;
    private String id,kode,namapelajaran,namaguru,kelas,bidang,userdata,status,bidangkelas,nama;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.download:
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("namapelajaran",namapelajaran);
                    DownloadFragment downloadFragment=new DownloadFragment ();
                    FragmentTransaction fragmentDownloadTransaction=getSupportFragmentManager().beginTransaction();
                    downloadFragment.setArguments(bundle3);
                    fragmentDownloadTransaction.replace(R.id.contain,downloadFragment);
                    fragmentDownloadTransaction.commit();
                    return true;
                case R.id.upload:
                    Bundle bundle = new Bundle();
                    bundle.putString("kelas",kelas);
                    bundle.putString("kode",kode);
                    bundle.putString("status",status);
                    bundle.putString("userid",nama);
                    bundle.putString("namapelajaran",namapelajaran);
                    UploadFragment uploadFragment=new UploadFragment();
                    FragmentTransaction fragmentUploadTransaction=getSupportFragmentManager().beginTransaction();
                    uploadFragment.setArguments(bundle);
                    fragmentUploadTransaction.replace(R.id.contain,uploadFragment);
                    fragmentUploadTransaction.commit();
                    return true;
               case R.id.member:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("kelas",kelas);
                    bundle2.putString("bidang",bidang);
                    bundle2.putString("kode",kode);
                    bundle2.putString("status",status);
                    bundle2.putString("bidangkelas",bidangkelas);
                    MemberFragment memberFragment= new MemberFragment();
                    FragmentTransaction fragmentMemberTransaction=getSupportFragmentManager().beginTransaction();
                    memberFragment.setArguments(bundle2);
                    fragmentMemberTransaction.replace(R.id.contain,memberFragment);
                    fragmentMemberTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_main);
        Intent receivedintent=getIntent();
        if (receivedintent.hasExtra("namapelajaran")){
            this.namapelajaran=getIntent().getStringExtra("namapelajaran");
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("namapelajaran",namapelajaran);
        DownloadFragment downloadFragment=new DownloadFragment ();
        FragmentTransaction fragmentDownloadTransaction=getSupportFragmentManager().beginTransaction();
        downloadFragment.setArguments(bundle3);
        fragmentDownloadTransaction.replace(R.id.contain,downloadFragment);
        fragmentDownloadTransaction.commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (receivedintent.hasExtra("id")){
            this.id=getIntent().getStringExtra("id");
        }

        if (receivedintent.hasExtra("kode")){
            this.kode=getIntent().getStringExtra("kode");
        }

        if (receivedintent.hasExtra("namaguru")){
            this.namaguru=getIntent().getStringExtra("namaguru");
        }

        if (receivedintent.hasExtra("kelas")){
            this.kelas=getIntent().getStringExtra("kelas");
        }

        if (receivedintent.hasExtra("bidang")){
            this.bidang=getIntent().getStringExtra("bidang");
        }

        if (receivedintent.hasExtra("status")){
            this.status=getIntent().getStringExtra("status");
        }

        if (receivedintent.hasExtra("bidangkelas")){
            this.bidangkelas=getIntent().getStringExtra("bidangkelas");
        }

        if (receivedintent.hasExtra("userid")){
            this.nama=getIntent().getStringExtra("userid");
        }

        setTitle(namapelajaran);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
