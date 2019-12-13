package com.example.kelassederhana;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {
    EditText text;
    Button button,button2;
    TextView view2,view3;
    Uri pdf;
    View view;
    Spinner spinner1;
    SpinnerAdapter spinnerAdapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage storage;
    static FirebaseDatabase database=FirebaseDatabase.getInstance();
    static DatabaseReference userdatabase=database.getReference();
    ProgressDialog progressDialog;
    String[] jenisGuru={"Jenis Upload","Materi","Tugas"};
    String[] jenisMurid={"Jenis Upload","Hasil Tugas"};
    String status;

    public UploadFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String user = getArguments().getString("status");
        view=inflater.inflate(R.layout.fragment_upload,container,false);
        button=view.findViewById(R.id.Select);
        button2=view.findViewById(R.id.Upload);
        view2=view.findViewById(R.id.Fileid);
        view3=view.findViewById(R.id.view2);
        text=view.findViewById(R.id.textnamasubjek);
        spinner1=view.findViewById(R.id.bidang);
        if (user.equals("Guru")){
            ArrayAdapter<String> a= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,jenisGuru);
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(a);
        } else {
            ArrayAdapter<String> b= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,jenisMurid);
            b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(b);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                } else{
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().toString().length()==0){
                    text.setError("Tidak boleh kosong");
                }else {
                    if (spinner1.getSelectedItem().equals("Jenis Upload")){
                        Toast.makeText(getContext(), "Pilih Jenis Upload", Toast.LENGTH_SHORT).show();
                    } else {
                        if (pdf!=null){
                            UploadFile(pdf);
                        } else {
                            Toast.makeText(getContext(),"Select FIle",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
        return view;
    }

    private void UploadFile(final Uri pdf ){
        storage=FirebaseStorage.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file...");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String filename=System.currentTimeMillis()+"";
        final StorageReference storageReference=storage.getReference();
        storageReference.child("Uploads").child(filename).putFile(pdf)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.child("Uploads").child(filename).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                    final String nama = getArguments().getString("userid");
                                    final String namapelajaran1 = getArguments().getString("namapelajaran");
                                    String link,name,jenis,jenisnama,namapelajaran;
                                    link=uri.toString();
                                    name=nama;
                                    namapelajaran=namapelajaran1;
                                    jenis=spinner1.getSelectedItem().toString();
                                    jenisnama=text.getText().toString();
                                    CollectionReference collectionReference=firebaseFirestore.collection("files");

                                    DownloadModel downloadmodel=new DownloadModel(link,name,jenis,jenisnama,namapelajaran);
                                    collectionReference.add(downloadmodel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(getContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(),"Tidak Berhasil",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Gagal",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Upload File Tidak Sukses",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentprogress = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentprogress);

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==9 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }else {
            Toast.makeText(getContext(),"Please Provide Permission",Toast.LENGTH_SHORT).show();
        }

    }

    private void selectPdf() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdf=data.getData();
            view2.setText("A File is Selected: "+data.getData().getLastPathSegment());
        }else {
            Toast.makeText(getContext(),"Please select file",Toast.LENGTH_SHORT).show();
        }

    }

}
