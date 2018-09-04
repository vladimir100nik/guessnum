package lv.tsi.java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Хочешь начать игру? Y/N");
        String answer = scan.next();

        do {

            int myNum = rand.nextInt(100) + 1;
            System.out.println(myNum);


            boolean userlost = true;
            for (int i = 1; i <= 5; i++) {
                System.out.println("Попытка N" + i);
                int userNum = askNum();

                if (userNum < myNum) {
                    System.out.println("Ваше число меньше!");
                } else if (userNum > myNum) {
                    System.out.println("Ваше число больше!");
                } else {
                    System.out.println("Вы угадали!!!");
                    userlost = false;
                    break;
                }

            }
            if (userlost) {
                System.out.println("Вы проиграли!");
            }

            System.out.println("Хочешь повторить? Y/N");
            answer = askYN();
            //  answer = scan.next();

        } while (answer.equals("y"));
    }

    static String askYN() {
        String answer;
        do {
            answer = scan.next();

            if (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("Y или N");
                continue;
            } else {
                return answer;
            }
        } while (true);


    }

    static int askNum() {
        int answer;
        do {
            answer = scan.nextInt();

            if (answer < 1 || answer > 100) {
                System.out.println("Вы ввели неверное число");

            } else {
                return answer;
            }

        } while (true);
        }

}
