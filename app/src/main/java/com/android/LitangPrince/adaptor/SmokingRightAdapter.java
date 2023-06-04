package com.android.LitangPrince.adaptor;

import com.android.LitangPrince.model.Smoking;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.LitangPrince.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmokingRightAdapter extends BaseQuickAdapter<Smoking, BaseViewHolder> {

    public SmokingRightAdapter(List<Smoking> smokings) {
        super(R.layout.item_smoking_right, smokings);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Smoking smoking) {
        baseViewHolder.setImageResource(R.id.snackRightImage, smoking.getImage())
                .setText(R.id.snackRightName, smoking.getName())
                .setText(R.id.snackRightPrice, "￥" + smoking.getPrice());
    }
}
