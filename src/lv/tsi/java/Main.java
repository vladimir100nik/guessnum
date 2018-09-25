package lv.tsi.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GamesResult> results = new ArrayList<>();

    public static void main(String[] args) {
        loadResults();
        String answer;

        do {
            System.out.println("Введите ваше имя:");
            String yourName = scan.next();
            //System.out.println("Привет " + yourName + " хочешь начать игру? Y/N");


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
                    results.sort(Comparator.<GamesResult>comparingInt(r0 -> r0.triesCount)
                            .thenComparing(r0 -> r0.userTime));
                    long t2 = System.currentTimeMillis();
                    r.userTime = (t2 - t1);


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
        saveResult();

    }

    private static void loadResults() {
        File file = new File("top_scores.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                GamesResult result = new GamesResult();
                result.name = in.next();
                result.triesCount = in.nextInt();
                result.userTime = in.nextLong();
                results.add(result);
            }
        } catch (IOException e) {
            System.out.println("Cannot load from file");
        }

    }

    private static void saveResult() {

        File file = new File("top_scores.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (GamesResult r : results) {
                out.printf("%s %d %d\n", r.name, r.triesCount, r.userTime);
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file");
        }

    }

//
//    private static void showResults() {
//        int count = Math.min(5, results.size());
//        for (int i = 0; i < count; i++) {
////            if (results.size() < 10) {
////                count = results.size();
////            }
//            GamesResult r = results.get(i);
//            System.out.printf("%s - %d - %d sec\n", r.name, r.triesCount, r.userTime);
//
//        }
//    }

    private static void showResults() {
        int maxLen = findMaxNameLen();

        results.stream()
                .limit(5)
                .forEach(r -> {
                    System.out.print(r.name);
                    for (int i = 0; i < (maxLen - r.name.length()); i++) {
                        System.out.print("_");
                    }
                    System.out.printf("%d - %.2fsec\n", r.triesCount, r.userTime / 1000.0);
                });
    }

    private static int findMaxNameLen() {


        return results.stream()
                .map(r -> r.name)
                .map(n -> n.length())
                .max(Comparator.naturalOrder())
                .get();
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
