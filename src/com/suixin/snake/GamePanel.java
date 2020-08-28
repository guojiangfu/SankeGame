package com.suixin.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener,ActionListener {
    int lenth;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    String direction;
    boolean isStart = false;
    Timer timer = new Timer(100,this);

    //定义一个食物
    int foodx;
    int foody;
    Random random = new Random();

    //死亡判断
    boolean isFail = false;

    //积分系统
    int score;
    //构造器
    public GamePanel(){
        init();
        //获取键盘监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();//让时间动起来
    }
    //初始化
    public void init(){
        lenth = 3;
        snakeX[0] = 100; snakeY[0] = 100;
        snakeX[1] = 75; snakeY[1] = 100;
        snakeX[2] = 50; snakeY[2] = 100;
        direction = "R";
        foodx = 25 + 25*random.nextInt(34);
        foody = 75 + 25*random.nextInt(24);
        score = 0;
    }
    //画板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        //绘制头部广告栏
       // Data.header.paintIcon(this,g,120,11);
        //绘制游戏区域
        g.fillRect(20,75,850,600);

        //画一条静态小蛇
        if (direction.equals("R")){
            Data.head_right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction.equals("L")){
            Data.head_left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction.equals("U")){
            Data.head_up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction.equals("D")){
            Data.head_down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);//通过length来控制长度
        }
        //画积分
        g.setColor(Color.CYAN);
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("长度："+ lenth +"     积分："+ score,30,50);
        //画食物
        Data.food.paintIcon(this,g,foodx,foody);

        //游戏提示
        if (isStart == false){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }
        //失败提醒
        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败，按下空格重新开始！",200,300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            if (isFail){//游戏重开
                isFail = false;
                init();
            }else {
                isStart = !isStart;
            }
            repaint();//刷新界面
        }
        //键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT){
            direction = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT){
            direction = "R";
        }else if (keyCode == KeyEvent.VK_UP){
            direction = "U";
        }else if (keyCode == KeyEvent.VK_DOWN){
            direction = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail == false){
            for (int i = lenth -1; i > 0; i--) {//除了头部，身体都向前移动
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //通过控制方向让头部移动
            if (direction.equals("R")){
                snakeX[0] = snakeX[0] + 25;//移动头部
                //边界判断
                if (snakeX[0] > 850){
                    snakeX[0] = 25;
                }
            }else if (direction.equals("L")){
                snakeX[0] = snakeX[0] - 25;//移动头部
                //边界判断
                if (snakeX[0] < 25){
                    snakeX[0] = 850;
                }
            }else if (direction.equals("U")){
                snakeY[0] = snakeY[0] - 25;//移动头部
                //边界判断
                if (snakeY[0] < 75){
                    snakeY[0] = 650;
                }
            }else if (direction.equals("D")){
                snakeY[0] = snakeY[0] + 25;//移动头部
                //边界判断
                if (snakeY[0] > 650){
                    snakeY[0] = 75;
                }
            }
            //如果food和head重合
            if (snakeX[0] == foodx && snakeY[0] == foody){
                lenth++;
                score = score+10;
                //重新生成食物
                foodx = 25 + 25*random.nextInt(34);
                foody = 75 + 25*random.nextInt(24);
            }
            //结束判断
            for (int i = 1; i < lenth; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                    isFail = true;
                }
            }
            repaint();
        }
        timer.start();
    }
}
