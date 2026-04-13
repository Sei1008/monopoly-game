package model;

import java.util.Random;

public class Dice{
    private int dice1,dice2;
    private static final Random rand=new Random();

    public void roll(){
        dice1=rand.nextInt(6)+1;
        dice2=rand.nextInt(6)+1;
    }
    public boolean isDoubles(){
        return dice1==dice2;
    }
    public int getDice1(){
        return dice1;
    }
    public int getDice2(){
        return dice2;
    }
    public int getTotal(){
        return dice1+dice2;
    }
}