package com.suixin.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //1、绘制静态窗口
        JFrame jFrame = new JFrame("贪吃蛇小游戏");
        //2、设置界面的大小
        jFrame.setBounds(100,100,900,720);
        //3、窗口大小不可改变
        jFrame.setResizable(false);
        //4、设计关闭事件
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加面板
        jFrame.add(new GamePanel());
        //让窗口展现出来
        jFrame.setVisible(true);
    }
}
