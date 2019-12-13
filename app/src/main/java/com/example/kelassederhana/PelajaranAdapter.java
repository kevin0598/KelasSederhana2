package com.example.kelassederhana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PelajaranAdapter extends RecyclerView.Adapter<PelajranHolder> {
    private Context context;
    private List<POJOmatapelajaran> pojOmatapelajarans;
    private ItemClickListener itemClickListener;
    public PelajaranAdapter(Context context, List<POJOmatapelajaran> pojOmatapelajarans) {
        this.pojOmatapelajarans=pojOmatapelajarans;
        this.context=context;
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PelajranHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pelajaran,parent,false);

        return new PelajranHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PelajranHolder holder, int i) {
        holder.view1.setText(pojOmatapelajarans.get(i).getKode());
        holder.view2.setText(pojOmatapelajarans.get(i).getNamapelajaran());
        holder.view3.setText(pojOmatapelajarans.get(i).getNamaguru());
        holder.view4.setText(pojOmatapelajarans.get(i).getKelas());
        holder.view5.setText(pojOmatapelajarans.get(i).getBidang());

        final POJOmatapelajaran pojOmatapelajaran= pojOmatapelajarans.get(i);
        if (itemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(pojOmatapelajaran);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return pojOmatapelajarans.size();
    }


    public interface ItemClickListener {
        public void onItemClick(POJOmatapelajaran pojOmatapelajaran);
    }
}
