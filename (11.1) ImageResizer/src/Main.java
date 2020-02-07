import java.io.File;

public class Main
{
    private static int cores = Runtime.getRuntime().availableProcessors();
    private static int newWidth = 300;
    public static void main(String[] args)
    {
        System.out.println(Runtime.getRuntime().availableProcessors());

        //вставить пути
        String srcFolder = "src";
        String dstFolder = "dest";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();

        int splitSize = files.length / cores;
        File[] splitFiles = new File[splitSize];
        for (int i = 0; i < cores; i++) {
            System.arraycopy(files, splitSize*i, splitFiles, 0, splitFiles.length);
            ImageResizer imageResizer = new ImageResizer(splitFiles, newWidth, dstFolder, start);
            new Thread(imageResizer).start();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
