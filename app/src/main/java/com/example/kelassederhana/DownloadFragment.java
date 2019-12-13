package com.example.kelassederhana;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {

    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("files");

    RecyclerView recyclerView;
    List<DownloadModel> downloadModels=new ArrayList<>();
    DownloadAdapter downloadAdapter;

    View view;


    public DownloadFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_member,container,false);
        recyclerView=view.findViewById(R.id.recycler2);
        downloadAdapter=new DownloadAdapter(getContext(),downloadModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(downloadAdapter);
        DividerItemDecoration divider=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpFB();
        dataFromFirebase();
    }

    private void dataFromFirebase() {
        final String namapelajaran = getArguments().getString("namapelajaran");
        if(downloadModels.size()>0){
            downloadModels.clear();
        }

        collectionReference.whereEqualTo("namapelajaaran",namapelajaran).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot: task.getResult()){

                    DownloadModel downloadmodel=new DownloadModel(documentSnapshot.getString("link"),documentSnapshot.getString("name"),documentSnapshot.getString("jenis"),documentSnapshot.getString("namajenis"),documentSnapshot.getString("namapelajaran"));
                    downloadModels.add(downloadmodel);
                }

                downloadAdapter=new DownloadAdapter(getContext(),downloadModels);
                recyclerView.setAdapter(downloadAdapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpFB() {
        firebaseFirestore=firebaseFirestore.getInstance();
    }

}
