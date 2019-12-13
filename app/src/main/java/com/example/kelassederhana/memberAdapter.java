package com.example.kelassederhana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class memberAdapter extends RecyclerView.Adapter<memberAdapter.memberViewHolder> {
    private Context context;
    private List<member> memberdata;

    public memberAdapter(Context context, List<member> members) {
        this.memberdata=members;
        this.context=context;
    }

    @NonNull
    @Override
    public memberAdapter.memberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member,parent,false);
        return new memberAdapter.memberViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull memberAdapter.memberViewHolder holder, int position) {
            final member Member=memberdata.get(position);
            holder.kode.setText(Member.getKode());
            holder.nama.setText(Member.getNama());
            holder.status.setText(Member.getStatus());
            holder.kelas.setText(Member.getKelas());
            holder.bidang.setText(Member.getBidang());
    }

    @Override
    public int getItemCount() {
        return memberdata.size();
    }

    public class memberViewHolder extends RecyclerView.ViewHolder {
        TextView kode,nama,status,kelas,bidang;
        public memberViewHolder(@NonNull View itemView) {
            super(itemView);
            kode=itemView.findViewById(R.id.Kode);
            nama=itemView.findViewById(R.id.Nama);
            status=itemView.findViewById(R.id.Status);
            kelas=itemView.findViewById(R.id.Kelas);
            bidang=itemView.findViewById(R.id.Bidang);
        }
    }

}
