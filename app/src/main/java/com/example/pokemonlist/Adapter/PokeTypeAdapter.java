package com.example.pokemonlist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonlist.ItemEventListner;
import com.example.pokemonlist.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class PokeTypeAdapter extends RecyclerView.Adapter<PokeTypeAdapter.Holder> {

    Context context;
    List<String> typeList;

    public PokeTypeAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.item_chip,parent,false);
        return new Holder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.chip.setText(typeList.get(position));
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        Chip chip;
        ItemEventListner itemEventListner;
        public Holder(View itemView) {
            super(itemView);
            chip = (Chip) itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemEventListner.onClick(view,getAdapterPosition());
                }
            });
        }
    }
}

