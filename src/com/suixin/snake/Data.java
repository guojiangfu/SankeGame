package com.suixin.snake;

import javax.swing.*;
import java.net.URL;
//存放外部数据
public class Data {
    //顶栏图片：url
    public  static  URL headerurl = Data.class.getResource("/static/header.png");
    public static ImageIcon header = new ImageIcon(headerurl);

    //头部
    public static  URL upUrl = Data.class.getResource("/static/head_up.jpg");
    public static ImageIcon head_up = new ImageIcon(upUrl);
    public static  URL downUrl = Data.class.getResource("/static/head_down.jpg");
    public static ImageIcon head_down = new ImageIcon(downUrl);
    public static  URL leftUrl = Data.class.getResource("/static/head_left.jpg");
    public static ImageIcon head_left = new ImageIcon(leftUrl);
    public static  URL rightUrl = Data.class.getResource("/static/head_right.jpg");
    public static ImageIcon head_right = new ImageIcon(rightUrl);
    //身体
    public static  URL bodyUrl = Data.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public static  URL foodUrl = Data.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
