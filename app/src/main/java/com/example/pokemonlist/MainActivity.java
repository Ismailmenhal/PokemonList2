package com.example.pokemonlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pokemonlist.model.Pokemon;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver showDetails = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                Fragment detailFragment = DetailPoke.getInstance();
                int position = intent.getIntExtra("position",-1);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.pokemon_list,detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                Pokemon pokemon = Common.commonPokemonList.get(position);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetails,new IntentFilter(Common.KEY_ENABLE_HOME));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
            default:
                break;
        }

        return true;
    }
}