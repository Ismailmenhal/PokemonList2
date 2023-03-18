package com.example.pokemonlist;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.annotations.NonNull;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int itemoffset;

    public ItemOffsetDecoration(int itemoffset) {
        this.itemoffset = itemoffset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int dimen) {
        this(context.getResources().getDimensionPixelSize(dimen));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(itemoffset,itemoffset,itemoffset,itemoffset);
    }
}

