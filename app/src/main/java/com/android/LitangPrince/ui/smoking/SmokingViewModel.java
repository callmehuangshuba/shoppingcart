package com.android.LitangPrince.ui.smoking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class SmokingViewModel extends ViewModel {

    private MediatorLiveData<String> mText;

    public SmokingViewModel() {
        mText = new MediatorLiveData<>();
        mText.setValue("点单页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}