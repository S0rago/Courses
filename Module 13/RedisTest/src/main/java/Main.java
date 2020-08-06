import java.util.Random;

public class Main {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    private static final int USERS = 20;
    private static final int SLEEP = 100;

    private static void log(int userId) {
        String log = String.format("— На главной странице показываем пользователя %d", userId);
        System.out.println(log);
    }
    private static void paidLog(int userId) {
        String log = String.format("> Пользователь %d оплатил платную услугу", userId);
        System.out.println(log);
    }

    public static void main(String[] args) {

        RStorage redis = new RStorage();
        redis.init();
        try {
            for (int i = 1; i <= USERS; i++) {
                redis.changePriority(i, i);
            }
            redis.listKeys();
            for (int i = 1, user_id;;Thread.sleep(SLEEP), i++) {
                if (i == 10) {
                    user_id = new Random().nextInt(20);
                    paidLog(user_id);
                    i = 1;
                } else {
                    user_id = Integer.parseInt(redis.popFirst());
                    log(user_id);
                }
                redis.cycleUsers();
                redis.changePriority(20, user_id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        redis.shutdown();
    }
}