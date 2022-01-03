package com.example.viikko8;

import android.widget.TextView;

import java.util.ArrayList;

public class BottleDispenser {

    private int bottles;
    private double money;
    private ArrayList<Bottle> bottleList;

    private static BottleDispenser bd = new BottleDispenser();

    private BottleDispenser() {
        bottles = 5;
        money = 0;
        bottleList = new ArrayList();
        bottleList.add(new Bottle("Pepsi Max", "Pepsi", 1.0, 0.5, 1.8));
        bottleList.add(new Bottle("Pepsi Max", "Pepsi", 1.0, 1.5, 2.2));
        bottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.0, 0.5, 2.0));
        bottleList.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.0, 1.5, 2.5));
        bottleList.add(new Bottle("Fanta Zero", "Coca-Cola", 1.0, 0.5, 1.95));
    }

    public static BottleDispenser getInstance() {
        return bd;
    }

    public void addMoney(String info, TextView a, TextView b) {
        double d = Double.parseDouble(info);
        money += d;
        a.setText("Klink! Added more money!");
        String text = Double.toString(money);
        b.setText(text);
    }

    public void buyBottle(int choice, TextView a, TextView b) {
        Bottle sBottle = bottleList.get(choice-1);
        if (money >= sBottle.getPrice()) {
            money -= sBottle.getPrice();
            String text = Double.toString(money);
            b.setText(text);
            a.setText("KACHUNK! ");
            a.append(sBottle.getName());
            a.append(" came out of the dispenser!");
            bottles -= 1;
            bottleList.remove(choice-1);
        } else 
            System.out.println("Add money first!");
    }

    public void returnMoney(TextView a, TextView b) {
        b.setText("0.00");
        String x = String.format("%.2f", money);
        a.setText("Klink klink. Money came out! You got " + x + "€ back");
        money = 0;
    }

    public void listBottles(TextView a) {
        for (int i=0; i<bottles; i++) {
            Bottle bot = bottleList.get(i);
            String x = Integer.toString(i +1);
            a.append(x + ". Name: " + bot.getName() + "\n");
            String b = Double.toString(bot.getSize());
            a.append("Size: " + b + "\n");
            String c = Double.toString(bot.getPrice());
            a.append("Price: " + c + "\n");
        }
    }
}
