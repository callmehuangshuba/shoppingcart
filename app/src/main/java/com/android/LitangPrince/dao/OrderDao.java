package com.android.LitangPrince.dao;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.android.LitangPrince.model.Order;
import com.android.LitangPrince.utils.Utils;

import java.util.ArrayList;
import java.util.List;
/*
这段代码实现了一个简单的订单数据库访问类，其中包含保存订单数据、通过用户名查询订单数据和删除指定 ID 的订单等方法。
它使用 Android 提供的 SQLite 数据库来存储和管理订单数据。

具体而言，该类提供了以下几个方法：

saveOrder(List<Order> orders)：用于保存一组订单数据到数据库中。

findAllByUsername(String username)：用于根据指定的用户名从数据库中查询所有符合条件的订单数据，并返回一个订单对象列表。

deleteOrderById(int id)：用于从数据库中删除指定 ID 的订单数据。

在实现上述功能时，该类使用了 Android 提供的 SQLiteOpenHelper 类来创建和管理数据库。它还使用了 SQLiteDatabase 类来执行 SQL 语句进行数据查询和更新操作。此外，为了方便调试和日志输出，该类还使用了 Android 提供的 Log 类进行日志输出。
* */
public class OrderDao {

    private static DatabaseHelper dbHelper = new DatabaseHelper(Utils.getContext(), 1);

    static {
        dbHelper.getWritableDatabase();
    }

    /**
     * 保存订单数据
     */
    public static void saveOrder(List<Order> orders) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Order order : orders) {
            ContentValues values = new ContentValues();
            values.put("name", order.getName());
            values.put("image", order.getImage());
            values.put("money", order.getMoney());
            values.put("time", order.getTime());
            values.put("username", order.getUsername());

            db.insert("orders", null, values);
        }
    }

    /**
     * 通过用户名查询订单数据
     */
    public static List<Order> findAllByUsername(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Order> orders = new ArrayList<>();

        // 查询指定用户名订单
        Cursor cursor = db.query("orders", null, "username=?", new String[]{username}, null, null, "time desc");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int image = cursor.getInt(cursor.getColumnIndex("image"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Order order = new Order(name, image, money, time);
                order.setId(id);
                orders.add(order);
                // 在控制台输出每一个订单的详细信息
                Log.d("Order Info", "Id: " + id+", Name: " + name + ", Image: " + image + ", Money: " + money + ", Time: " + time);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // 在控制台输出总共查询到的订单数量
        Log.d("Order Count", "Total Orders Found: " + orders.size());

        // 删除所有订单
//        db.delete("orders", null, null);
//        Log.d("Order Delete", "All orders have been deleted from the database.");



        return orders;
    }

    public static void deleteOrderById(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("orders", "id = ?", new String[] { String.valueOf(id) });
        Log.d("Order Delete", "Order  ID = " + id + " has been deleted from the database.");
    }

    public static void deleteOrderByIdWithConfirmation(Context context, int id, OnDeleteOrderListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除订单");
        builder.setMessage("您确定要删除此订单吗？");
        builder.setPositiveButton("是", (dialog, which) -> {
            deleteOrderById(id);
            Toast.makeText(context, "订单删除成功。", Toast.LENGTH_SHORT).show();

            // 在删除成功后调用回调接口
            listener.onSuccess();
        });
        builder.setNegativeButton("否", (dialog, which) -> {
            dialog.dismiss();
            listener.onFailure();
        });
        builder.show();
    }

    public interface OnDeleteOrderListener {
        void onSuccess();
        void onFailure();
    }

}
