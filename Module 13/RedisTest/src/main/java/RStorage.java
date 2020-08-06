import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RStorage {

    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> datingUsers;

    private final static String KEY = "DATING_USERS";
    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        datingUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void changePriority(int score,int user_id)
    {
        datingUsers.add(score, String.valueOf(user_id));
    }

    void cycleUsers() {
        for (ScoredEntry<String> entry : datingUsers.entryRange(0, -1)) {
            datingUsers.addScore(entry.getValue(), -1);
        }
    }

    String popFirst() {
        return datingUsers.pollFirst();
    }
}