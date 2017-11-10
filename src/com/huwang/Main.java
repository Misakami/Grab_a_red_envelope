package com.huwang;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("选择红包种类:1.普通红包 2.手气红包 3.口令红包");
        int j = scanner.nextInt();
        System.out.println("请输入抢红包的人数:");
        int n = scanner.nextInt();
        String[] name = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.println("请输入第" + (i + 1) + "个人的姓名");
            name[i] = scanner.next();
        }
        System.out.println("请输入红包金额:");
        double money = scanner.nextDouble();
        System.out.println("请输入红包个数:");
        int number = scanner.nextInt();
        if (money / number < 0.01) {
            System.out.println("金额不足");
            return;
        }
        switch (j) {
            case 1:
                General_red_packets get = new General_red_packets(money, number);

                for (int i = 0; i < n; i++) {
                    get.get(name[i]);
                }
                break;
            case 2:
                Lucky_red_packets get1 = new Lucky_red_packets(money, number);
                for (int i = 0; i < n; i++) {
                    get1.get(name[i]);
                }
                if (n<number)
                    System.out.println((money- Lucky_red_packets.enter_money)+"元红包都没人要了？");
                break;
            case 3:
                Password_red_packets get2=new Password_red_packets(money,number);
                System.out.println("请输入红包口令:");
                String pass=scanner.next();
                for (int i = 0; i < n; i++) {
                    System.out.print(name[i]+"输入口令：");
                    String password=scanner.next();
                    if (Objects.equals(pass, password)) {
                        get2.get(name[i],pass);
                    }
                }
                if (n<number)
                    System.out.println((money- Lucky_red_packets.enter_money)+"元红包都没人要了？");
                break;
            default:
                System.out.println("你皮？");
                break;
        }
    }
}

class General_red_packets {
    private final double money;
    private final int number;
    private DecimalFormat df = new DecimalFormat("0.00");
    static int i = 0;

    General_red_packets(double money, int number) {
        this.money = money;
        this.number = number;
    }

    void get(String name) {
        if (i < number)
            System.out.println(name + "拿了" + df.format(money / 5));
        else
            System.out.println(name + "来晚了");
        i++;
    }
}

class Lucky_red_packets {
    private final int number;
    private final double money;
    static int i = 0;
    static double min = 0.01;
    static double enter_money = 0;
    static String lucky_name;
    static double lucky_money = 0;

    Lucky_red_packets(double money, int number) {
        this.money = money;
        this.number = number;
    }
    void get(String name) {
        double rand = Math.random();
        i++;
        if (i < number) {
            double rate = (money - enter_money - min * i) * rand + min;
            BigDecimal df = new BigDecimal(rate);
            rate = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (rate > lucky_money) {
                lucky_name = name;
                lucky_money = rate;
            }
            System.out.println(name + "拿了" + rate);
            enter_money += rate;
        } else if (i == number) {
            BigDecimal de = new BigDecimal(money - enter_money);
            enter_money = de.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (enter_money > lucky_money) {
                lucky_name = name;
                lucky_money = enter_money;
            }
            System.out.println(name + "拿了" + enter_money);
            System.out.println("运气王是" + lucky_name);
        } else
            System.out.println(name + "来晚了");
    }
}
class Password_red_packets{
    private final int number;
    private final double money;
    static int i = 0;
    static double min = 0.01;
    static double enter_money = 0;
    static String lucky_name;
    static double lucky_money = 0;

    Password_red_packets(double money, int number) {
        this.money = money;
        this.number = number;
    }
    void get(String name,String password) {
        i++;
        double rand = Math.random();
        if (i < number) {
            double rate = (money - enter_money - min * i) * rand + min;
            BigDecimal df = new BigDecimal(rate);
            rate = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (rate > lucky_money) {
                lucky_name = name;
                lucky_money = rate;
            }
            System.out.println(name +"大喊一声"+password+ "拿了" + rate);
            enter_money += rate;
        } else if (i == number) {
            BigDecimal de = new BigDecimal(money - enter_money);
            enter_money = de.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (enter_money > lucky_money) {
                lucky_name = name;
                lucky_money = enter_money;
            }
            System.out.println(name +"大喊一声"+password+ "拿了" + enter_money);
            System.out.println("运气王是" + lucky_name);
        } else
            System.out.println(name + "来晚了");
    }
}
