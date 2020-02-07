import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {

    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage newImage = Resize(image, newWidth*2, newHeight*2);
                newImage = Resize(newImage, newWidth, newHeight);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
    }

    private BufferedImage Resize(BufferedImage oldImage, int tempWidth, int tempHeight) {
        BufferedImage image = new BufferedImage(
                tempWidth, tempHeight, BufferedImage.TYPE_INT_RGB
        );
        int widthStep = oldImage.getWidth() / tempWidth;
        int heightStep = oldImage.getHeight() / tempHeight;

        for (int x = 1; x < tempWidth; x++)
        {
            for (int y = 1; y < tempHeight; y++) {
                int rgb = oldImage.getRGB(x * widthStep, y * heightStep);
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }
}
