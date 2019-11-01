import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String sourcePath = "";
        String destinationPath = "";
        do {
            System.out.println("Enter source folder:");
            sourcePath = input.nextLine();
            System.out.println("Enter destination folder:");
            destinationPath = input.nextLine();
            try {
                File sourceFolder = new File(sourcePath);
                File destinationFolder = new File(destinationPath);
                copyFolder(sourceFolder, destinationFolder);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!sourcePath.equals("exit"));
    }

    public static void copyFolder(File source, File dest) throws Exception {
        for (File file : source.listFiles()) {
            if (file.isDirectory())
                copyFolder(file, dest);
            else
                Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("Done");
    }

    public static boolean findInDest(File toFind, File dest) {
        return true;
    }
}
