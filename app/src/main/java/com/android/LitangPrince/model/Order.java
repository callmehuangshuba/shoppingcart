package com.android.LitangPrince.model;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private  int id=0;

    private String name;

    private int image;

    private double money;

    private String time;

    private String username;

    public Order() {
    }

    public Order(String name, int image, double money, String time) {
        this.name = name;
        this.image = image;
        this.money = money;
        this.time = time;
    }

    /**
     * 乐器类导入订单
     * 计算金额、加入订单产生时间
     *
     * @param smoking 产生的乐器对象
     */
    @SuppressLint("SimpleDateFormat")
    public Order(Smoking smoking) {
        this.name = smoking.getName();
        this.image = smoking.getImage();
        // 计算金额
        BigDecimal money = BigDecimal.valueOf(smoking.getPrice()).multiply(BigDecimal.valueOf(smoking.getCount()));
        this.money = money.doubleValue();
        // 订单产生时间（格式化）
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = simpleDateFormat.format(new Date());
    }
    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", money=" + money +
                ", time=" + time +
                '}';
    }
}
