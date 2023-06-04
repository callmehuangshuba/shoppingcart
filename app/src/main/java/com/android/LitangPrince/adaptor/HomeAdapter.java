package com.android.LitangPrince.adaptor;

import com.android.LitangPrince.model.Smoking;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.LitangPrince.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Smoking, BaseViewHolder> {

    public HomeAdapter(List<Smoking> smokings) {
        super(R.layout.item_home_snack, smokings);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Smoking smoking) {
        baseViewHolder.setImageResource(R.id.homeSnackImage, smoking.getImage());
        baseViewHolder.setText(R.id.homeSnackName, smoking.getName());
        baseViewHolder.setText(R.id.homeSnackPrice, "¥ " + smoking.getPrice());
    }
}
