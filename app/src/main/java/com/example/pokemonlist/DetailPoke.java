package com.example.pokemonlist;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.pokemonlist.Adapter.PokeTypeAdapter;
import com.example.pokemonlist.model.Pokemon;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailPoke#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailPoke extends Fragment {


    ImageView pokemonImg;
    TextView pokemonName,PokemonHeight,PokemonWeight;
    RecyclerView recyclerType,recyclerWeakness;


    static DetailPoke instance;
    public static DetailPoke getInstance(){
        if(instance == null)
            instance = new DetailPoke();
        return instance;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailPoke() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailPoke.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailPoke newInstance(String param1, String param2) {
        DetailPoke fragment = new DetailPoke();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView= inflater.inflate(R.layout.fragment_detail_poke, container, false);

        Pokemon p;
        if(getArguments().get("num")==null)
            p = Common.commonPokemonList.get(getArguments().getInt("position"));
        else
            p = null;

        pokemonImg = (ImageView) itemView.findViewById(R.id.pokemonImg);
        pokemonName = (TextView) itemView.findViewById(R.id.namePokemon);
        PokemonHeight = (TextView) itemView.findViewById(R.id.height);
        PokemonWeight = (TextView) itemView.findViewById(R.id.weight);
        recyclerType = (RecyclerView) itemView.findViewById(R.id.recycleType);
        recyclerType.setHasFixedSize(true);
        recyclerType.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        recyclerWeakness = (RecyclerView) itemView.findViewById(R.id.recycleWeaknesses);
        recyclerWeakness.setHasFixedSize(true);
        recyclerWeakness.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        setDetailPokemon(p);

        return itemView;
    }

    private void setDetailPokemon(Pokemon p) {
        Glide.with(getActivity()).load(p.getImg()).into(pokemonImg);
        pokemonName.setText(p.getName());
        PokemonHeight.setText("Height : "+p.getHeight());
        PokemonWeight.setText("Weight : "+p.getWeight());
        PokeTypeAdapter typeAdapter = new PokeTypeAdapter(getActivity(),p.getType());
        recyclerType.setAdapter(typeAdapter);
        PokeTypeAdapter weakAdapter = new PokeTypeAdapter(getActivity(),p.getWeaknesses());
        recyclerWeakness.setAdapter(weakAdapter);
    }
}