import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        PrintWriter writer = new PrintWriter("res/numbers.txt");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int regionCode = 1; regionCode < 200; regionCode++) {
            final int finalRegionCode = regionCode;
            executorService.execute(() -> {
                try {
                    // PrintWriter writer = new PrintWriter("res/numbers" + finalRegionCode + ".txt");
                    for (int number = 1; number < 1000; number++) {
                        StringBuilder carNum = new StringBuilder();
                        for (char firstLetter : letters) {
                            for (char secondLetter : letters) {
                                for (char thirdLetter : letters) {
                                    carNum.append(firstLetter)
                                            .append(padNumber(number, 3))
                                            .append(secondLetter)
                                            .append(thirdLetter)
                                            .append(padNumber(finalRegionCode, 2))
                                            .append('\n');
                                }
                            }
                        }
                        synchronized (writer) {
                            writer.write(carNum.toString());
                        }
                    }
//                    writer.flush();
//                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
//            for (int number = 1; number < 1000; number++) {
//                StringBuilder carNum = new StringBuilder();
//                for (char firstLetter : letters) {
//                    for (char secondLetter : letters) {
//                        for (char thirdLetter : letters) {
//                            carNum.append(firstLetter)
//                                    .append(padNumber(number, 3))
//                                    .append(secondLetter)
//                                    .append(thirdLetter)
//                                    .append(padNumber(regionCode, 2))
//                                    .append('\n');
//                        }
//                    }
//                }
//                writer.write(carNum.toString());
//            }
//        }
        writer.flush();
        writer.close();
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength)
    {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for(int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }

//    private static String padNumber(int number, int numberLength) {
//        StringBuilder numberStr = new StringBuilder(Integer.toString(number)).reverse();
//        int padSize = numberLength - numberStr.length();
//        numberStr.append("0".repeat(Math.max(0, padSize)));
//        return numberStr.reverse().toString();
//    }

//    private static String padNumber(int number, int numberLength)
//    {
//        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
//        int padSize = numberLength - numberStr.length();
//        for(int i = 0; i < padSize; i++) {
//            numberStr.insert(0, '0');
//        }
//        return numberStr.toString();
//    }
}

//200 регионов без изменений: 1546787 мс (7000-10000 на регион), 41352, 82383
//200 регионов 1 поток, StringBuilder в padNumber и цикле, запись по целым регионам: 74161 мс (300-400 на регион)
//пул потоков по числу ядер: 57111