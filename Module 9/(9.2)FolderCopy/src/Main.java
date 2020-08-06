import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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
                System.out.println("Done");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!sourcePath.equals("exit"));
    }

    public static void copyFolder(File source, File dest) throws Exception {
        if (source.isDirectory()) {
            if (!dest.exists()) dest.mkdir();
            String[] subSrc = source.list();
            for (String file : subSrc) {
                File subSrcFile = new File(source, file);
                File destFile = new File(dest, file);
                copyFolder(subSrcFile, destFile);
            }
        } else {
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied: " + dest);
        }
    }
}
