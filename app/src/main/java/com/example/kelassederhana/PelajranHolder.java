package com.example.kelassederhana;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



class PelajranHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    LinearLayout item;
    ItemClickListener itemclick;
    TextView view1,view2,view3,view4,view5;

    public PelajranHolder(@NonNull View itemView) {
        super(itemView);
        item=itemView.findViewById(R.id.item);
        view1=itemView.findViewById(R.id.KodeKelas);
        view2=itemView.findViewById(R.id.NamaPelajaran);
        view3=itemView.findViewById(R.id.NamaGuru);
        view4=itemView.findViewById(R.id.Kelas);
        view5=itemView.findViewById(R.id.Bidang);
//        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (itemclick!=null){
            itemclick.onItemClick(v,getAdapterPosition());
        }
    }

    void setItemclick(ItemClickListener itemclick){
        this.itemclick= itemclick;
    }

    public interface ItemClickListener {
        void onItemClick(View view,int position);
    }

}
