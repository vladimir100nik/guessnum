package lv.tsi.java;

import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GamesResult> results = new ArrayList<>();

    public static void main(String[] args) {

        String answer;

        do {
            System.out.println("Введите ваше имя:");
            String yourName = scan.next();
            System.out.println("Привет " + yourName + " хочешь начать игру? Y/N");



            long t1 = System.currentTimeMillis();
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
                    GamesResult r = new GamesResult();
                    r.name = yourName;
                    r.triesCount = i;
                    results.add(r);
                    long t2 = System.currentTimeMillis();
                    r.userTime = (t2 - t1) / 1000;


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


        showResults();
    }



    private static void showResults() {

        for (GamesResult r : results) {
            System.out.println(r.name + " вы угадали с " + r.triesCount + " попытки " + r.userTime + "сек.");
        }

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
            try {
                answer = scan.nextInt();
            } catch (InputMismatchException e) {                           //e - название переменной для записи ошибок
                System.out.println("Это не число!");
                scan.next();
                continue;
            }
            if (answer < 1 || answer > 100) {
                System.out.println("Вы ввели неверное число");

            } else {
                return answer;
            }

        } while (true);
    }

}
