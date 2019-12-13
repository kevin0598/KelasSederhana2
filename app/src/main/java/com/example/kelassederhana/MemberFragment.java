package com.example.kelassederhana;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
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
public class MemberFragment extends Fragment {
    List<member> members=new ArrayList<>();
    memberAdapter memberAdapter;

    View view;
    RecyclerView viewR;


    public MemberFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_member,container,false);
        viewR=view.findViewById(R.id.recycler2);
        memberAdapter=new memberAdapter(getContext(),members);
        viewR.setHasFixedSize(true);
        viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewR.setAdapter(memberAdapter);
        DividerItemDecoration divider=new DividerItemDecoration(viewR.getContext(),DividerItemDecoration.VERTICAL);
        viewR.addItemDecoration(divider);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tampilkan();

    }


    public void tampilkan(){
        final String bidangkelaspelajaran = getArguments().getString("bidangkelas");
        Query query=FirebaseDatabase.getInstance().getReference("User").orderByChild("bidangkelas").equalTo(bidangkelaspelajaran);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                members.clear();
                if (dataSnapshot.exists()){
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    member Member=new member();
                    Member.setKode(postsnapshot.getKey());
                    Member.setNama(postsnapshot.child("nama").getValue().toString());
                    Member.setStatus(postsnapshot.child("status").getValue().toString());
                    Member.setKelas(postsnapshot.child("kelas").getValue().toString());
                    Member.setBidang(postsnapshot.child("bidang").getValue().toString());
                    members.add(Member);
                }
                memberAdapter.notifyDataSetChanged();
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
