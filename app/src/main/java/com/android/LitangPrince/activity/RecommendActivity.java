package com.android.LitangPrince.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.LitangPrince.MyApplication;
import com.android.LitangPrince.R;
import com.android.LitangPrince.adaptor.RecommendAdapter;
import com.android.LitangPrince.dao.OrderDao;
import com.android.LitangPrince.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendActivity extends AppCompatActivity {

    @BindView(R.id.RecommendRecyclerView)
    RecyclerView RecommendRecyclerView;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecommendActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        setTitle("待评价");

        ButterKnife.bind(this);

        RecommendRecyclerView.setLayoutManager(new LinearLayoutManager(RecommendActivity.this));

        initAdapter();
    }

    /**
     * 初始化订单页面
     */
    private void initAdapter() {
        // 获取数据库数据
        List<Order> orders = OrderDao.findAllByUsername(MyApplication.getUser().getUsername());

        RecommendAdapter adapter = new RecommendAdapter(orders);

        // 设置空布局
        adapter.setEmptyView(getEmptyView());

        RecommendRecyclerView.setAdapter(adapter);
    }

    public View getEmptyView() {
        return getLayoutInflater().inflate(R.layout.empty_order_view, RecommendRecyclerView, false);
    }
}