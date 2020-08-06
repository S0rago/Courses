import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception {
        String rootPath = "hdfs://1c3ff7342e1f:8020";
        String filePath = "/test/folder/text.txt";
        String folderPath = "/test";
        String addFolderPath = "/test/test/test/";
        FileAccess fa = new FileAccess(rootPath);
        System.out.println(fa.read(rootPath + filePath));

        fa.delete(rootPath + filePath);
        fa.create(rootPath + filePath,false);
        fa.create(rootPath + addFolderPath,true);
        fa.append(rootPath + filePath, "this is a test file");
        System.out.println(fa.read(rootPath + filePath));

        List<String> list = fa.list(rootPath + folderPath);
        if (list != null) {
            list.forEach(System.out::println);
        }
    }

    private static String getRandomWord() {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for (int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
