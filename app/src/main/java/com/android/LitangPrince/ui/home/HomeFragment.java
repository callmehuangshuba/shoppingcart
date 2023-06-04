package com.android.LitangPrince.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.android.LitangPrince.R;
import com.android.LitangPrince.activity.DetailActivity;
import com.android.LitangPrince.adaptor.HomeAdapter;
import com.android.LitangPrince.adaptor.ViewPagerAdapter;
import com.android.LitangPrince.animator.MyAnimation3;
import com.android.LitangPrince.data.DataServer;
import com.android.LitangPrince.model.Smoking;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    @BindView(R.id.homeRecyclerView)
    RecyclerView homeRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 首页瀑布流列表
        homeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        initHomeAdapter();
    }

    private void initHomeAdapter() {
        // 实例化购物车列表适配器对象
        HomeAdapter adapter = new HomeAdapter(DataServer.getHomeList());
        // 设置动画效果
        adapter.setAnimationEnable(true);
//        adapter.setAnimationFirstOnly(false);
//        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom);
        adapter.setAdapterAnimation(new MyAnimation3());
        // 设置头部
        adapter.setHeaderView(getHeadView(), 1);
        // 设置尾部
        adapter.setFooterView(getFooterView(), 1);

        // 初始化轮播图
//        initBanner();

        // 点击事件监听器
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Smoking smoking = (Smoking) adapter1.getItem(position);
            DetailActivity.actionStart(getContext(), smoking);
        });

        // 设置适配器
        homeRecyclerView.setAdapter(adapter);
    }


    /**
     * 初始化轮播图
     */
//    private void initBanner() {
//        // 找到 ViewPager 的 ID
//        viewPager = getView().findViewById(R.id.view_pager);
//        // 添加轮播图图片资源
//        imageList.add(R.drawable.hpls);
//        imageList.add(R.drawable.advertisement);
//        imageList.add(R.drawable.one4);
//        // 创建适配器并设置给 ViewPager
//        adapter = new ViewPagerAdapter(requireContext(), imageList);
//        viewPager.setAdapter(adapter);
//        // 设置自动滑动定时器
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        currentItem++;
//                        if (currentItem >= imageList.size()) {
//                            currentItem = 0;
//                        }
//                        viewPager.setCurrentItem(currentItem);
//                    }
//                });
//            }
//        }, 2000, 2000);
//    }
    /**
     * 首页RecyclerView头部View
     */
    private View getHeadView() {
        return getLayoutInflater().inflate(R.layout.head_home_image, homeRecyclerView, false);
    }

    /**
     * 首页RecyclerView尾部View
     */
    private View getFooterView() {
        return getLayoutInflater().inflate(R.layout.footer_no_item, homeRecyclerView, false);
    }
}