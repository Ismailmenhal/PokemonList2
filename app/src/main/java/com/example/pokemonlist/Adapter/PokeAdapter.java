package com.example.pokemonlist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pokemonlist.Common;
import com.example.pokemonlist.ItemEventListner;
import com.example.pokemonlist.R;
import com.example.pokemonlist.model.Pokemon;

import java.util.List;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.Holder1> {

    Context context;
    List<Pokemon> pokemonList;

    public PokeAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public Holder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_item,parent,false);
        return new Holder1(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder1 holder, int position) {
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemonImage);
        holder.pokemonTexte.setText(pokemonList.get(position).getName());
        holder.setItemEventListner(new ItemEventListner() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(context,"Click On : "+pokemonList.get(position).getName(),Toast.LENGTH_LONG).show();

                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("position",position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static public class Holder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pokemonImage;
        TextView pokemonTexte;

        ItemEventListner itemEventListner;

        public void setItemEventListner(ItemEventListner itemEventListner) {
            this.itemEventListner = itemEventListner;
        }

        public Holder1(View itemView) {
            super(itemView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.pokemonImage);
            pokemonTexte = (TextView) itemView.findViewById(R.id.pokemonText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemEventListner.onClick(view,getAdapterPosition());
        }
    }

}

