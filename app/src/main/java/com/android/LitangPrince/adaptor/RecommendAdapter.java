package com.android.LitangPrince.adaptor;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.LitangPrince.R;
import com.android.LitangPrince.model.Order;

import org.jetbrains.annotations.NotNull;

import java.util.List;
public class RecommendAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public RecommendAdapter(List<Order> orders) {
        super(R.layout.item_order_recommend, orders);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Order order) {
        baseViewHolder.setText(R.id.orderName_rmd, order.getName());
        baseViewHolder.setImageResource(R.id.orderImage_rmd, order.getImage());
        baseViewHolder.setText(R.id.orderId_rmd, "订单号: " + order.getId());
        baseViewHolder.setText(R.id.orderTime_rmd, "下单时间: " + order.getTime());
        baseViewHolder.setText(R.id.orderMoney_rmd, "总价: ￥" + order.getMoney());
    }
}
