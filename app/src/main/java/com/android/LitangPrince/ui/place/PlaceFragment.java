package com.android.LitangPrince.ui.place;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.android.LitangPrince.MyApplication;
import com.android.LitangPrince.R;
import com.android.LitangPrince.activity.DetailActivity;
import com.android.LitangPrince.adaptor.PlaceOrderAdapter;
import com.android.LitangPrince.dao.OrderDao;
import com.android.LitangPrince.model.Order;
import com.android.LitangPrince.model.Smoking;
import com.android.LitangPrince.utils.Tips;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceFragment extends Fragment {

    private PlaceViewModel placeViewModel;

    @BindView(R.id.placeRecyclerView)
    RecyclerView orderRecyclerView;

    @BindView(R.id.placeBuyBtn)
    Button buyButton;

    @BindView(R.id.placeMoney)
    TextView placeMoney;

    PlaceOrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        placeViewModel =
                ViewModelProviders.of(this).get(PlaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_place, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initOrderAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 每次页面显示都计算合计金额
        calcTotalMoney();
    }

    /**
     * 初始化购物车列表适配器
     */
    private void initOrderAdapter() {
        // 实例化购物车列表适配器对象
        orderAdapter = new PlaceOrderAdapter(MyApplication.getCartSmokings());

        // 设置空布局
        orderAdapter.setEmptyView(getEmptyView());

        // 设置动画效果
        orderAdapter.setAnimationEnable(true);
//        orderAdapter.setAnimationFirstOnly(false);
        orderAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);

        // 点击item事件触发
        orderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Smoking smoking = (Smoking) adapter.getItem(position);
                DetailActivity.actionStart(getContext(), smoking);
            }
        });

        // 注册item内子控件id
        orderAdapter.addChildClickViewIds(R.id.orderLessLabel, R.id.orderAddLabel);
        // 子控件点击监听
        orderAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Smoking smoking = (Smoking) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.orderLessLabel:
                    // 点击减少数量
                    if (smoking.getCount() == 1) {
                        MyApplication.getCartSmokings().remove(position);
                    } else {
                        MyApplication.getCartSmokings().get(position).setCount(smoking.getCount() - 1);
                    }

                    adapter.notifyDataSetChanged();
                    break;
                case R.id.orderAddLabel:
                    // 点击添加数量
                    MyApplication.getCartSmokings().get(position).setCount(smoking.getCount() + 1);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
            calcTotalMoney();
        });

        // 设置适配器
        orderRecyclerView.setAdapter(orderAdapter);
    }

    /**
     * 点击下单按钮事件触发器
     */
    @OnClick(R.id.placeBuyBtn)
    void initClick() {
        if (MyApplication.getCartSmokings().isEmpty()) {
            Tips.show("购物车是空的啦！！！");
        } else {
            if (MyApplication.isLogin()) {
                // 显示Dialog
                showDialog();
            } else {
                Tips.show("请先登录");
            }
        }
    }

    /**
     * 显示下单备注提示框
     */
    @SuppressLint("InflateParams")
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_view, null))
                .setTitle("确认地址(若更改请填入新地址)")
                .setPositiveButton("确认", (dialog, which) -> {
                    // 持久化订单数据
                    saveOrder();

                    // 清空购物车数据
                    MyApplication.getCartSmokings().removeAll(MyApplication.getCartSmokings());
                    // 通知适配器数据变化
                    orderAdapter.notifyDataSetChanged();
                    // 刷新总金额
                    calcTotalMoney();

                    Tips.show("下单成功");
                })
                .create()
                .show();
    }

    /**
     * 持久化订单数据
     */
    public void saveOrder() {
        List<Order> orders = new ArrayList<>();
        // 购物车数据产生订单
        for (Smoking smoking : MyApplication.getCartSmokings()) {
            Order order = new Order(smoking);
            order.setUsername(MyApplication.getUser().getUsername());
            orders.add(order);
        }

        OrderDao.saveOrder(orders);
    }

    /**
     * 点击垃圾桶事件触发器
     */
    @OnClick(R.id.deleteOrder)
    void deleteOrder() {
        if (MyApplication.getCartSmokings().isEmpty()) {
            Tips.show("购物车是空的");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示")
                    .setMessage("是否清空购物车？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        // 清空购物车数据
                        MyApplication.getCartSmokings().removeAll(MyApplication.getCartSmokings());
                        // 通知适配器数据变化
                        orderAdapter.notifyDataSetChanged();
                        // 刷新总金额
                        calcTotalMoney();

                        Tips.show("已清空购物车");
                    })
                    .create()
                    .show();
        }
    }

    // 计算合计金额
    @SuppressLint("SetTextI18n")
    private void calcTotalMoney() {
        BigDecimal totalMoney = BigDecimal.valueOf(0);

        // 遍历计算总金额（解决舍入误差）
        if (!MyApplication.getCartSmokings().isEmpty()) {
            for (Smoking smoking : MyApplication.getCartSmokings()) {
                // 乐器单价 × 乐器数量
                BigDecimal tmp = BigDecimal.valueOf(smoking.getPrice()).multiply(BigDecimal.valueOf(smoking.getCount()));
                totalMoney = totalMoney.add(tmp);
            }
        }

        placeMoney.setText("￥" + totalMoney.doubleValue());
    }

    /**
     * 下单页面购物车空布局
     */
    private View getEmptyView() {
        return getLayoutInflater().inflate(R.layout.empty_cart_view, orderRecyclerView, false);
    }
}