package com.android.LitangPrince.adaptor;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;

import com.android.LitangPrince.dao.OrderDao;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.LitangPrince.R;
import com.android.LitangPrince.model.Order;

import org.jetbrains.annotations.NotNull;



import java.util.List;
/*
这段代码是一个RecyclerView的适配器，用于将订单列表渲染到界面上。通过继承 BaseQuickAdapter 类，它可以更快速地实现适配器的基本功能，并且可以使用更便捷的方式来设置数据和视图。

在 `OrderAdapter` 的构造函数中，指定了每个订单项的布局文件 `R.layout.item_order`，以及要显示的订单列表数据 `orders`。

在 `convert()` 方法中，通过 `BaseViewHolder` 对象设置了每个订单项的名称、图片、下单时间和总价等信息，其中下单时间和总价需要根据订单对象的属性动态设置。而 `@SuppressLint("SimpleDateFormat")` 注解是为了消除使用 SimpleDateFormat 类时的警告。

最终，当该适配器与 RecyclerView 控件关联时，每个订单项都将按照所指定的布局文件进行渲染，并显示相应的订单信息。
* */
public class OrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderAdapter(List<Order> orders) {
        super(R.layout.item_order, orders);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Order order) {
        baseViewHolder.setText(R.id.orderName, order.getName());
        baseViewHolder.setImageResource(R.id.orderImage, order.getImage());
        baseViewHolder.setText(R.id.orderId, "订单号: " + order.getId());
        baseViewHolder.setText(R.id.orderTime, "下单时间: " + order.getTime());
        baseViewHolder.setText(R.id.orderMoney, "总价: ￥" + order.getMoney());

        // 添加删除订单的功能
        Button button = baseViewHolder.itemView.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            int id = order.getId(); // 获取要删除的订单的 ID
            OrderDao.deleteOrderByIdWithConfirmation(getContext(), id, new OrderDao.OnDeleteOrderListener() {
                @Override
                public void onSuccess() {
                    removeAt(getData().indexOf(order)); // 在数据源中删除该订单
                }

                @Override
                public void onFailure() {

                }
            }); // 调用 OrderDao 的方法删除订单
        });

    }
}
