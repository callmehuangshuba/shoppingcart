package com.android.LitangPrince.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.android.LitangPrince.model.User;
import com.android.LitangPrince.utils.Utils;
/*
这段代码实现了一个简单的用户信息管理类，其中包含了保存用户信息、获取已登录用户对象、清除登录用户信息和登录状态等多个方法。它使用 Android 提供的 SharedPreferences 来存储和管理用户数据。

具体而言，该类提供了以下几个方法：

isLogin() 和 isLogin(boolean bool)：用于获取和设置当前用户是否已经登录的状态。

getUser() 和 saveUser(User user)：用于获取和保存当前已登录用户的信息对象。

removeUserAndLoginStatus()：用于清除当前已登录用户的信息对象和登录状态。

removeAll()：用于删除所有的用户数据。

saveUsername(String username) 和 getUsername()：用于保存和获取用户账号。

在实现上述功能时，该类使用了 Android 提供的 SharedPreferences 类来创建和管理用户数据。

它还使用了 Gson 库来将用户信息对象转化为 JSON 字符串以便于保存到 SharedPreferences 中，

并在需要时重新将其解析为 Java 对象。此外，为了方便调试和日志输出，该类还使用了 Android 提供的 Log 类进行日志输出。
*/
public class UserDao {

    // 实例化SharedPreferences对象
    private static SharedPreferences data = Utils.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);

    // Gson对象
    private static Gson gson = new Gson();

    public static boolean isLogin() {
        return data.getBoolean("isLogin", false);
    }

    public static void isLogin(boolean bool) {
        SharedPreferences.Editor edit = data.edit();
        edit.putBoolean("isLogin", bool);
        edit.apply();
    }

    /**
     * 获取已登录用户对象
     */
    public static User getUser() {
        String userJson = data.getString("user", "");
        return gson.fromJson(userJson, User.class);
    }

    public static void saveUser(User user) {
        String userJson = gson.toJson(user);
        SharedPreferences.Editor edit = data.edit();
        edit.putString("user", userJson);
        edit.apply();
    }

    /**
     * 清除登录用户信息和登录状态
     */
    public static void removeUserAndLoginStatus() {
        SharedPreferences.Editor edit = data.edit();
        edit.remove("user");
        edit.remove("isLogin");
        edit.apply();
    }

    public static void removeAll() {
        SharedPreferences.Editor edit = data.edit();
        edit.clear();
        edit.apply();
    }

    /**
     * 保存账号
     */
    public static void saveUsername(String username) {
        SharedPreferences.Editor editor = data.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public static String getUsername() {
        return data.getString("username", "");
    }
}
