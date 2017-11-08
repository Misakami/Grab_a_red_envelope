package com.huwang;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        General_red_packets get = new General_red_packets();
        Lucky_red_packets get1=new Lucky_red_packets();
        System.out.println("请输入抢红包的人数:");
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        String[] name=new String[n];
        for (int i=0;i<n;i++){
            System.out.println("请输入第"+(i+1)+"个人的姓名");
            name[i]= scanner.next();
        }
        for (int i=0;i<n;i++) {
            get.getter(name[i]);
        }
        for (int i=0;i<n;i++) {
            get1.getter(name[i]);;
        }
    }
}

class General_red_packets {
    private final double money = 50;
    private final int number = 5;
    private DecimalFormat df = new DecimalFormat("0.00");
    static int i = 0;

    void getter(String name) {
        if (i < number)
            System.out.println(name + "拿了" + df.format(money / 5));
        else
            System.out.println(name + "来晚了");
        i++;
    }
}

class Lucky_red_packets {
    private final int number = 5;
    private final double money=50;
    static int i=5;
    static double min=0.01;
    static double enter_money=0;
    void getter(String name){
        double rand=Math.random();
        if (i>1) {
            double rate=(money-enter_money-min*i)*rand+min;
            BigDecimal df = new  BigDecimal(rate);
            rate = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(name + "拿了" +rate);
            enter_money+=rate;
        }
        else if (i==1) {
            BigDecimal de = new  BigDecimal(money-enter_money);
            enter_money=de.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(name + "拿了" + enter_money);
        }
        else
            System.out.println(name+"来晚了");
        i--;
    }
}
