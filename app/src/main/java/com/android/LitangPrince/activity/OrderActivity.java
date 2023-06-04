package com.android.LitangPrince.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.LitangPrince.MyApplication;
import com.android.LitangPrince.R;
import com.android.LitangPrince.adaptor.OrderAdapter;
import com.android.LitangPrince.dao.OrderDao;
import com.android.LitangPrince.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.orderRecyclerView)
    RecyclerView orderRecyclerView;



    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("订单");

        ButterKnife.bind(this);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));

        initAdapter();


    }

    /**
     * 初始化订单页面
     */
    private void initAdapter() {
        // 获取数据库数据
        List<Order> orders = OrderDao.findAllByUsername(MyApplication.getUser().getUsername());

        OrderAdapter adapter = new OrderAdapter(orders);

        // 设置空布局
        adapter.setEmptyView(getEmptyView());

        orderRecyclerView.setAdapter(adapter);
    }

    public View getEmptyView() {
        return getLayoutInflater().inflate(R.layout.empty_order_view, orderRecyclerView, false);
    }
}