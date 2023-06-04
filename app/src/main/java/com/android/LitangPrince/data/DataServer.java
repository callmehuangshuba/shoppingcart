package com.android.LitangPrince.data;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.LitangPrince.dao.DatabaseHelper;
import com.android.LitangPrince.dao.UserDatabaseHelper;
import com.android.LitangPrince.model.Order;
import com.android.LitangPrince.model.Smoking;
import com.android.LitangPrince.R;
import com.android.LitangPrince.model.User;
import com.android.LitangPrince.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataServer {

    private static List<String> snackOrderList;

    private static List<Smoking> homeList;

    private static List<Smoking> dzyList;

    private static List<Smoking> ydList;

    private static List<Smoking> xyList;

    private static List<User> accountList;
    private static DatabaseHelper dbHelper = new DatabaseHelper(Utils.getContext(), 1);
    /**
     * 首页数据
     */
    public static List<Smoking> getHomeList() {
        if (homeList == null) {
            homeList = new ArrayList<Smoking>() {{
                add(new Smoking("中华", 229, R.mipmap.zh, "中华是一种来自中国的传统香烟品牌，由中国烟草总公司生产。该品牌的历史可以追溯到1951年，被誉为中国最著名的香烟品牌之一。中华采用优质的云南烟叶和特定的工艺制作而成，口感细腻、香气浓郁，深受国内外吸烟者的喜爱。它在中国市场上长期保持领先地位，并在国际市场上也有相当的影响力，被认为是具有代表性的中国品牌之一。"));
                add(new Smoking("Logic Compact", 8699, R.mipmap.logic, "Logic Compact是一种烟弹品牌，由Logic Vapes公司生产。它使用预填充的烟油盒子（pod）来提供尼古丁和口味，这些盒子可以很容易地插入和拆下。该品牌的产品在欧洲市场上比较受欢迎，特别是英国。Logic Compact有多种口味可供选择，包括经典烟草、薄荷、水果、甜品等，适合不同口味偏好的吸烟者。"));
                add(new Smoking("Renova Zero", 8999, R.mipmap.zero, "Renova Zero是一种烟弹品牌，由Vaporesso公司生产。该品牌的烟弹采用可重复填充的设计，使用者可以自己添加电子烟液。Renova Zero采用了高性能芯片控制系统，提供多种防护功能，包括防干烧、低电压保护和短路保护等，保障用户的安全。此外，Renova Zero的口感和吸气阻力也受到许多用户的好评，适合寻找类似传统卷烟口感的用户。"));
                add(new Smoking("Vuse Alto", 8999, R.mipmap.vs, "Vuse Alto是一种广受欢迎的烟弹品牌，由英国烟草公司（British American Tobacco）旗下的美国子公司Vuse创造。它使用预填充液体盒子（pod）来提供尼古丁，这些盒子包含了高浓度的尼古丁盐和口味添加剂。Vuse Alto的口感在市场上获得较高评价，适合寻找更柔和口感的吸烟者。"));
                add(new Smoking("Logic Compact", 8699, R.mipmap.logic, "Logic Compact是一种烟弹品牌，由Logic Vapes公司生产。它使用预填充的烟油盒子（pod）来提供尼古丁和口味，这些盒子可以很容易地插入和拆下。该品牌的产品在欧洲市场上比较受欢迎，特别是英国。Logic Compact有多种口味可供选择，包括经典烟草、薄荷、水果、甜品等，适合不同口味偏好的吸烟者。"));
                add(new Smoking("Renova Zero", 8999, R.mipmap.zero, "Renova Zero是一种烟弹品牌，由Vaporesso公司生产。该品牌的烟弹采用可重复填充的设计，使用者可以自己添加电子烟液。Renova Zero采用了高性能芯片控制系统，提供多种防护功能，包括防干烧、低电压保护和短路保护等，保障用户的安全。此外，Renova Zero的口感和吸气阻力也受到许多用户的好评，适合寻找类似传统卷烟口感的用户。"));
                add(new Smoking("Suorin Air", 8699, R.mipmap.air, "Suorin Air是一种烟弹品牌，由Suorin公司生产。它采用可重复填充的设计，用户可以自己添加电子烟液。Suorin Air的外观小巧轻便，易于携带，并有多种颜色和款式可供选择。该品牌的烟弹使用金属材质制成，结构坚固耐用，适合长期使用。Suorin Air的口感相对温和柔和，比较适合刚开始接触电子烟的吸烟者或寻找类似轻度香烟的味道的用户。"));
            }};
        }
        return homeList;
    }

    /**
     * 点菜页面分类左边列表数据
     */
    public static List<String> getSnackOrderList() {
        if (snackOrderList == null) {
            snackOrderList = new ArrayList<String>() {{
                add("电子烟");
                add("烟弹");
                add("传统香烟");
            }};
        }
        return snackOrderList;
    }

    /**
     * 电子烟
     */
    public static List<Smoking> getdzyList() {
        if (dzyList == null) {
            dzyList = new ArrayList<Smoking>() {{
                add(new Smoking("锐克五代", 5999, R.mipmap.sp1, "锐克五代是锐克品牌的一款高端香烟，采用高品质烟叶精心制作而成。其烟气清新、口感细腻，入口后可以感受到淡雅的果香和微妙的甜味，回味悠长。锐克五代的烟气柔和且不刺激，让人享受舒适的吸食体验。同时，这款香烟的外观设计简洁大方，彰显了其高贵的身份，深受广大消费者喜爱。无论是自用还是送礼，锐克五代都是一个不错的选择。"));
                add(new Smoking("锐克溢彩", 5888, R.mipmap.sp2, "锐克溢彩是锐克品牌的一款烟气浓郁、香气四溢的高端香烟，采用天然的烤烟和柔和的烟叶精心混合而成。其独特的配方和制作工艺，让其具有香气丰富、口感醇厚的特点，入口后可以感受到浓郁的焦糖和坚果的味道，回味悠长。锐克溢彩的烟气柔和且不刺激，让人享受舒适的吸食体验。同时，这款香烟的外观设计简洁大方，彰显了其高贵的身份，深受广大消费者喜爱。无论是自用还是送礼，锐克溢彩都是一个不错的选择。"));
                add(new Smoking("锐克1代", 5555, R.mipmap.sp3, "锐克一代是一款高品质的电子烟产品，采用先进的加热技术和精湛的工艺制作而成。该产品提供多种口味选择，每种口味均通过严格的品质检测，确保无毒害、无异味、无漏油等问题。此外，锐克2代还拥有较大的电池容量和更优秀的抗干烧能力，让用户能够更长时间地享受到电子烟的乐趣。"));
                add(new Smoking("锐克2代", 5555, R.mipmap.sp4, "锐克2代是一款高品质、高性能的电子烟产品。它采用先进的加热技术和优质的原材料制作而成，能够提供稳定、纯净的口感体验。锐克2代还具有较大的电池容量和快速充电功能，让用户不用频繁充电就能长时间使用。同时，该产品可根据个人喜好调节气流和功率，满足各种吸食需求。无论您是新手还是老手，锐克2代都可以为您带来更愉悦的电子烟体验。"));
            }};
        }
        return dzyList;
    }

    /**
     * 烟弹
     */
    public static List<Smoking> getydList() {
        if (ydList == null) {
            ydList = new ArrayList<Smoking>() {{
                add(new Smoking("Vuse Alto", 8999, R.mipmap.vs, "Vuse Alto是一种广受欢迎的烟弹品牌，由英国烟草公司（British American Tobacco）旗下的美国子公司Vuse创造。它使用预填充液体盒子（pod）来提供尼古丁，这些盒子包含了高浓度的尼古丁盐和口味添加剂。Vuse Alto的口感在市场上获得较高评价，适合寻找更柔和口感的吸烟者。"));
                add(new Smoking("Logic Compact", 8699, R.mipmap.logic, "Logic Compact是一种烟弹品牌，由Logic Vapes公司生产。它使用预填充的烟油盒子（pod）来提供尼古丁和口味，这些盒子可以很容易地插入和拆下。该品牌的产品在欧洲市场上比较受欢迎，特别是英国。Logic Compact有多种口味可供选择，包括经典烟草、薄荷、水果、甜品等，适合不同口味偏好的吸烟者。"));
                add(new Smoking("Renova Zero", 8999, R.mipmap.zero, "Renova Zero是一种烟弹品牌，由Vaporesso公司生产。该品牌的烟弹采用可重复填充的设计，使用者可以自己添加电子烟液。Renova Zero采用了高性能芯片控制系统，提供多种防护功能，包括防干烧、低电压保护和短路保护等，保障用户的安全。此外，Renova Zero的口感和吸气阻力也受到许多用户的好评，适合寻找类似传统卷烟口感的用户。"));
                add(new Smoking("Suorin Air", 8699, R.mipmap.air, "Suorin Air是一种烟弹品牌，由Suorin公司生产。它采用可重复填充的设计，用户可以自己添加电子烟液。Suorin Air的外观小巧轻便，易于携带，并有多种颜色和款式可供选择。该品牌的烟弹使用金属材质制成，结构坚固耐用，适合长期使用。Suorin Air的口感相对温和柔和，比较适合刚开始接触电子烟的吸烟者或寻找类似轻度香烟的味道的用户。"));
            }};
        }
        return ydList;
    }

    /**
     * 传统香烟
     */
    public static List<Smoking> getxyList() {
        if (xyList == null) {
            xyList = new ArrayList<Smoking>() {{
                add(new Smoking("芙蓉王", 1299, R.mipmap.frw, "芙蓉王是一种来自中国的传统香烟品牌，由湖南省的中国烟草公司生产。该品牌的历史可以追溯到1950年代，是中国最早的香烟品牌之一。芙蓉王采用高质量的烟叶和特定的工艺制作而成，口感醇厚、余味悠长。它在中国市场上享有很高的知名度和美誉度，被认为是中国文化和传统的象征之一。"));
                add(new Smoking("中华", 229, R.mipmap.zh, "中华是一种来自中国的传统香烟品牌，由中国烟草总公司生产。该品牌的历史可以追溯到1951年，被誉为中国最著名的香烟品牌之一。中华采用优质的云南烟叶和特定的工艺制作而成，口感细腻、香气浓郁，深受国内外吸烟者的喜爱。它在中国市场上长期保持领先地位，并在国际市场上也有相当的影响力，被认为是具有代表性的中国品牌之一。"));
                add(new Smoking("利群", 1299, R.mipmap.wycy, "利群是一种来自中国的传统香烟品牌，由中国烟草总公司生产。该品牌的历史可以追溯到1950年代，是中国最早的香烟品牌之一。利群采用高质量的云南、广西等优质烟叶和特定的工艺制作而成，口感清爽、气味芳香怡人，深受国内外吸烟者的喜爱。它在中国市场上保持领先地位，并在国际市场上也有相当的影响力。同时，利群还推出了多个系列的产品，以满足不同口味偏好的消费者需求。"));
                add(new Smoking("理塘王子联名香烟", 229, R.mipmap.dzdy, "理塘王子联名香烟是一款专为烟民精心打造的高品质香烟。每支香烟都选用了优质烟草，并采用最先进的生产工艺制作而成，保证了其独特的口感和香味，深受各年龄层次的消费者喜爱。\n" +
                        "\n" +
                        "这款香烟的名字“理塘王子”寓意着勇气、坚韧和不屈不挠的精神。理塘王子代表着中国青年的力量和活力，在市场上也取得很好的反响。与此同时，理塘王子联名香烟还以其独特的包装设计赢得了众多消费者的关注，使您在享受美妙的烟草体验的同时，更能感受到它所传达的代表着青春与活力的品牌形象。"));
            }};
        }
        return xyList;
    }



    /**
     * 用户账号信息
     */

    public static List<User> getAccountList() {
        if (accountList == null) {
            accountList = new ArrayList<User>() {{
                add(new User("1200113220", "123", "hpls", R.drawable.hpls));
            }};
        }
        return accountList;
    }



//public List<User> getAccountList1() {
//
//        List<User> accountList = new ArrayList<>();
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                String username = cursor.getString(cursor.getColumnIndex("username"));
//                String password = cursor.getString(cursor.getColumnIndex("password"));
//                String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
//                byte[] pictureBytes = cursor.getBlob(cursor.getColumnIndex("picture"));
//                Bitmap pictureBitmap = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);
//
//                User user = new User(username, password, nickname, pictureBitmap);
//                accountList.add(user);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return accountList;
//    }



    public static List<Order> getOrderTest() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new ArrayList<Order>() {{
            add(new Order("是我", R.mipmap.user1_head, 10.9, simpleDateFormat.format(new Date())));
            add(new Order("是我", R.mipmap.user1_head, 10.9, simpleDateFormat.format(new Date())));
            add(new Order("是我", R.mipmap.user1_head, 10.9, simpleDateFormat.format(new Date())));
        }};
    }
}
