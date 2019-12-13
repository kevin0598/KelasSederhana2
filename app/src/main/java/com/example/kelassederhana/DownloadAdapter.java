package com.example.kelassederhana;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder> {
    private Context context;
    List<DownloadModel> downloadmodel;

    public DownloadAdapter(Context context, List<DownloadModel> downloadmodel) {
        this.context = context;
        this.downloadmodel = downloadmodel;
    }

    @NonNull
    @Override
    public DownloadAdapter.DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_download,parent,false);
        return new DownloadAdapter.DownloadViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadAdapter.DownloadViewHolder holder, final int position) {
        holder.view1.setText(downloadmodel.get(position).getJenis());
        holder.view2.setText(downloadmodel.get(position).getNamajenis());
        holder.view3.setText(downloadmodel.get(position).getName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadfile(holder.view2.getContext(),downloadmodel.get(position).getNamajenis(),".pdf",DIRECTORY_DOWNLOADS,downloadmodel.get(position).getLink());
            }
        });
    }

    public void downloadfile(Context context,String filename,String fileExtension,String destinationDirectory,String url){

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);

        DownloadManager.Request request= new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,filename+fileExtension);

        downloadManager.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return downloadmodel.size();
    }

    public class DownloadViewHolder extends RecyclerView.ViewHolder {
        TextView view1,view2,view3;
        Button button;
        public DownloadViewHolder(@NonNull View itemView) {
            super(itemView);
            view1=itemView.findViewById(R.id.JenisSubjek);
            view2=itemView.findViewById(R.id.NamaSubjek);
            view3=itemView.findViewById(R.id.NamaSiswa);
            button=itemView.findViewById(R.id.Download);

        }
    }
}
