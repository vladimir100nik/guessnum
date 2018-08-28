package lv.tsi.java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int myNum = rand.nextInt(100) + 1;
        System.out.println(myNum);

        boolean userlost = true;
        for (int i = 1; i <= 5; i++) {
            System.out.println("Попытка N" + i);
            int usernum = scan.nextInt();

            if (usernum < myNum) {
                System.out.println("Ваше число меньше!");
            } else if (usernum > myNum) {
                System.out.println("Ваше число больше!");
            } else {
                System.out.println("Вы угадали!!!");
                userlost = false;
                break;
            }

        }
        if(userlost == true) {
            System.out.println("Вы проиграли");

        }
    }

}
