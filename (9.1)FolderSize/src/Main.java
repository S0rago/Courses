import java.io.File;
import java.util.Scanner;

public class Main {
    static StringBuilder builder = new StringBuilder();
    static String[] sizes = {"bytes","KB","MB","GB","TB","PB"};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String folder = "";
        while(!folder.equals("exit")) {
            System.out.println("Enter path to check/'exit': ");
            folder = input.nextLine();
            try {
                File dir = new File(folder);
                if (dir.isDirectory()) {
                    getFiles(dir);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void getFiles(File dir) {
        for (File path : dir.listFiles())
            if (path.isDirectory()) getFiles(path);
            else printSize(path);
    }

    public static void printSize(File file) {
        double dividedSize = file.length();
        int level = 0;
        builder.append(file.getPath()).append(" - ");
        while (dividedSize >= 1024) {
            dividedSize = dividedSize / 1024;
            level++;
        }
        String str = String.format("%.2f %s", dividedSize, sizes[level]);
        builder.append(str);
        System.out.println(builder);
        builder.setLength(0);
    }
}