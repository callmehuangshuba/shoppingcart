package com.android.LitangPrince;

import android.app.Application;

import com.android.LitangPrince.model.Smoking;
import com.android.LitangPrince.model.User;
import com.android.LitangPrince.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    /**
     * Application类上下文
     */
    private static MyApplication appContext;

    /**
     *
     */
    private static List<Smoking> cartSmoking;

    /**
     * 登录用户
     */
    private static User user;

    /**
     * 登录状态
     */
    private static boolean isLogin;

    public static MyApplication getInstance() {
        return appContext;
    }

    public static List<Smoking> getCartSmokings() {
        return cartSmoking;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    /**
     * 是否登录
     *
     * @return <tt>true</tt>: 已经登录, <tt>false</tt>: 未登录
     */
    public static boolean isLogin() {
        return isLogin;
    }

    public static void isLogin(boolean isLogin) {
        MyApplication.isLogin = isLogin;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        // 初始化购物车集合
        cartSmoking = new ArrayList<>();

        // 初始化工具类
        Utils.init(this);
    }
}
