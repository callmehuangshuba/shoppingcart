package com.android.LitangPrince.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.LitangPrince.MyApplication;
import com.android.LitangPrince.R;
import com.android.LitangPrince.dao.DatabaseHelper;
import com.android.LitangPrince.dao.UserDao;
import com.android.LitangPrince.dao.UserDatabaseHelper;
import com.android.LitangPrince.data.DataServer;
import com.android.LitangPrince.model.User;
import com.android.LitangPrince.utils.Tips;
import com.android.LitangPrince.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginUsernameEdit)
    EditText usernameEdit;

    @BindView(R.id.loginPasswordEdit)
    EditText passwordEdit;

    private static UserDatabaseHelper dbHelper = new UserDatabaseHelper(Utils.getContext(),1);
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("登录");

        ButterKnife.bind(this);

        // 恢复账号
        String username = UserDao.getUsername();
        usernameEdit.setText(username);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    /**
     * 登录按钮点击事件
     */
    @OnClick(R.id.loginBtn)
    void login() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        User user = new User(username, password);

        User loginUser = checkUserFromAccountList(user);
        if (loginUser != null) {
            // 登录成功
            Tips.show("登录成功");

            MyApplication.isLogin(true);
            MyApplication.setUser(loginUser);

            // 持久化已登录用户数据
            UserDao.saveUser(loginUser);
            UserDao.isLogin(true);

            // 持久化账号，以便退出登录后不用再输入账号
            UserDao.saveUsername(username);

            // 关闭Activity
            finish();
        } else {
            // 登录失败
            Tips.show("登录失败");
            passwordEdit.setText("");
        }
    }

    /**
     * 登录
     *
     * @return 登录成功: 查询到的用户对象, 登录失败: null
     */
    public User checkUserFromAccountList(User u) {

        for (User tmp : DataServer.getAccountList()) {
            if (tmp.equals(u)) {
                return tmp;
            }
        }

        return null;
    }



    /**
     * 注册按钮点击事件
     */
    @OnClick(R.id.registerBtn)
    void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}