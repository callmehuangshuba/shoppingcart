package com.android.LitangPrince.adaptor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.LitangPrince.R;
import com.android.LitangPrince.model.Smoking;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 下单页面购物车列表适配器
 */
public class PlaceOrderAdapter extends BaseQuickAdapter<Smoking, BaseViewHolder> {

    public PlaceOrderAdapter(List<Smoking> smokings) {
        super(R.layout.item_place_order, smokings);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Smoking smoking) {
        baseViewHolder.setImageResource(R.id.placeOrderImage, smoking.getImage())
                .setText(R.id.placeOrderName, smoking.getName())
                .setText(R.id.placeOrderPrice, "￥" + smoking.getPrice())
                .setText(R.id.orderCountBtn, String.valueOf(smoking.getCount()));
    }
}
