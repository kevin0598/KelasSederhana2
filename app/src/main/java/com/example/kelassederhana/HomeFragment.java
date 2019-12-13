package com.example.kelassederhana;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PelajranHolder.ItemClickListener{
    static FirebaseDatabase database=FirebaseDatabase.getInstance();
    static DatabaseReference databaseReference=database.getReference();
    static DatabaseReference pelajarandatabase=database.getReference();
    static DatabaseReference userdatabase=database.getReference();

    private String kelaspelajaran,bidangpelajaran,kelas,bidang,userdata,nama,status,bidangkelas;
    RecyclerView view1;
    List<POJOmatapelajaran> pojOmatapelajaransarray=new ArrayList<>();
    View view;
    PelajaranAdapter pelajaranAdapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String user = getArguments().getString("user");
        view=inflater.inflate(R.layout.fragment_home,container,false);
        view1=view.findViewById(R.id.recycler);
        pelajaranAdapter=new PelajaranAdapter(getContext(),pojOmatapelajaransarray);
        pelajaranAdapter.setItemClickListener(new PelajaranAdapter.ItemClickListener() {

            @Override
            public void onItemClick(final POJOmatapelajaran pojOmatapelajaran) {
                userdatabase = FirebaseDatabase.getInstance().getReference().child("User");
                userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User User = dataSnapshot.child(user).getValue(User.class);
                        nama=User.getNama();
                        status=User.getStatus();
                        bidangkelas=User.getBidangkelas();
                        Intent intent=new Intent(getActivity(),HalamanMain.class);

                        intent.putExtra("id",pojOmatapelajaran.getIdguru());
                        intent.putExtra("kode",pojOmatapelajaran.getKode());
                        intent.putExtra("namapelajaran",pojOmatapelajaran.getNamapelajaran());
                        intent.putExtra("namaguru",pojOmatapelajaran.getNamaguru());
                        intent.putExtra("kelas",pojOmatapelajaran.getKelas());
                        intent.putExtra("bidang",pojOmatapelajaran.getBidang());
                        intent.putExtra("status",status);
                        intent.putExtra("bidangkelas",bidangkelas);
                        intent.putExtra("userid",nama);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        view1.setHasFixedSize(true);
        view1.setLayoutManager(new LinearLayoutManager(getActivity()));
        view1.setAdapter(pelajaranAdapter);
        DividerItemDecoration divider=new DividerItemDecoration(view1.getContext(),DividerItemDecoration.VERTICAL);
        view1.addItemDecoration(divider);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tampilkan();

    }

    public void tampilkan(){
        userdatabase = FirebaseDatabase.getInstance().getReference().child("User");
        userdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String user = getArguments().getString("user");
                User User = dataSnapshot.child(user).getValue(User.class);
                bidangkelas=User.getBidangkelas();
                Query query=FirebaseDatabase.getInstance().getReference("MataPelajaran").orderByChild("bidangkelas").equalTo(bidangkelas);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        pojOmatapelajaransarray.clear();
                        if (dataSnapshot.exists()){
                        for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                            POJOmatapelajaran pojOmatapelajaran=new POJOmatapelajaran();
                            pojOmatapelajaran.setIdguru(postsnapshot.child("idguru").getValue().toString());
                            pojOmatapelajaran.setKode(postsnapshot.getKey());
                            pojOmatapelajaran.setNamapelajaran(postsnapshot.child("namapelajaran").getValue().toString());
                            pojOmatapelajaran.setNamaguru(postsnapshot.child("namaguru").getValue().toString());
                            pojOmatapelajaran.setKelas(postsnapshot.child("kelas").getValue().toString());
                            pojOmatapelajaran.setBidang(postsnapshot.child("bidang").getValue().toString());
                            pojOmatapelajaransarray.add(pojOmatapelajaran);
                        }
                        pelajaranAdapter.notifyDataSetChanged();




                    }}

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

    @Override
    public void onItemClick(View view, int position) {

    }

}
