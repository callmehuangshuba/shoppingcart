package com.android.LitangPrince.ui.smoking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.android.LitangPrince.adaptor.SmokingLeftAdapter;
import com.android.LitangPrince.adaptor.SmokingRightAdapter;
import com.android.LitangPrince.data.DataServer;
import com.android.LitangPrince.model.Smoking;
import com.android.LitangPrince.utils.Tips;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmokingFragment extends Fragment {

    private SmokingViewModel smokingViewModel;

    // 乐器页面左边列表已选择的Position
    private int leftSelectPosition = 0;

    @BindView(R.id.snackLeftRecyclerView)
    RecyclerView leftRecyclerview;

    @BindView(R.id.snackRightRecyclerView)
    RecyclerView rightRecyclerView;

    // 右边适配器
    private SmokingRightAdapter rightAdapter;

    public static SmokingFragment newInstance() {
        return new SmokingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        smokingViewModel = ViewModelProviders.of(this).get(SmokingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_snack, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        leftRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initLeftAdapter();
        initRightAdapter();
    }

    /**
     * 初始化左边适配器
     */
    @SuppressLint("ResourceAsColor")
    private void initLeftAdapter() {
        // 实例化左边适配器对象
        SmokingLeftAdapter leftAdapter = new SmokingLeftAdapter(DataServer.getSnackOrderList());
        // 设置动画效果
        leftAdapter.setAnimationEnable(true);
        leftAdapter.setAnimationFirstOnly(false);
        leftAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);

        // 触发点击按钮
        leftAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position != leftSelectPosition) {
                String item = (String) adapter.getItem(position);

                // 原本选中的item变成未选中颜色
                Objects.requireNonNull(adapter.getViewByPosition(leftSelectPosition, R.id.snackLeftType)).setBackgroundResource(R.color.colorContent);
                // 当前item变成选中颜色
                Objects.requireNonNull(adapter.getViewByPosition(position, R.id.snackLeftType)).setBackgroundResource(R.color.colorBgWhite);
                leftSelectPosition = position;

                // 刷新右边列表
//                    rightAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom);
                rightAdapter.setAnimationEnable(false);
                switch (position) {
                    case 1:
                        rightAdapter.setNewInstance(DataServer.getydList());
                        break;
                    case 2:
                        rightAdapter.setNewInstance(DataServer.getxyList());
                        break;
                    default:
                        rightAdapter.setNewInstance(DataServer.getdzyList());
                        break;
                }
            }
        });

        // 设置左边列表适配器
        leftRecyclerview.setAdapter(leftAdapter);
    }

    /**
     * 初始化右边适配器
     */
    public void initRightAdapter() {
        // 实例化右边适配器对象
        rightAdapter = new SmokingRightAdapter(DataServer.getdzyList());
        // 设置动画效果
        rightAdapter.setAnimationEnable(true);
//        rightAdapter.setAnimationFirstOnly(false);
        rightAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        // 设置尾部
        rightAdapter.addFooterView(getFooterView());

        // 点击item事件
        rightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Smoking smoking = (Smoking) adapter.getItem(position);
                DetailActivity.actionStart(getContext(), smoking);
            }
        });

        // 左边列表加入购物车点击事件
        rightAdapter.addChildClickViewIds(R.id.snackRightAddBtn);
        rightAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Smoking smoking = (Smoking) adapter.getItem(position);
            if (view.getId() == R.id.snackRightAddBtn) {
                if (!MyApplication.getCartSmokings().contains(smoking)) {
                    // 添加到购物车
                    smoking.setCount(1);
                    MyApplication.getCartSmokings().add(smoking);
                    Tips.show("已添加" + smoking.getName() + "到购物车");
                } else {
                    Tips.show("已在购物车中，不能重复添加");
                }
            }
        });

        // 设置右边列表适配器
        rightRecyclerView.setAdapter(rightAdapter);
    }

    /**
     * 乐器页面右边RecyclerView尾部View
     */
    private View getFooterView() {
        return getLayoutInflater().inflate(R.layout.footer_no_item, rightRecyclerView, false);
    }
}