package com.android.LitangPrince.adaptor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.android.LitangPrince.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmokingLeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SmokingLeftAdapter(List<String> types) {
        super(R.layout.item_smoking_left, types);
    }

    /**
     * 设置item数据
     */
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        // 第一个item默认选中状态
        if (baseViewHolder.getLayoutPosition() == 0) {
            baseViewHolder.setBackgroundResource(R.id.snackLeftType, R.color.colorBgWhite);
        }
        baseViewHolder.setText(R.id.snackLeftType, s);
    }

}
