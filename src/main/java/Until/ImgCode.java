package Until;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import 斐斐打码.Hello;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImgCode {
    public static void snapshot2(WebDriver drivername, String filename)
    {
        // this method will take screen shot ,require two parameters ,one is driver name, another is file name


        //  File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            WebDriver augmentedDriver = new Augmenter().augment(drivername);
            File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            File file = new File("D:\\temp\\"+filename);
            FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        }
        finally
        {
            System.out.println("screen shot finished");
        }
    }
    public static byte[] takeScreenshot(WebDriver driver) throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        //TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
    public static BufferedImage createElementImage(WebDriver driver, WebElement webElement)
            throws IOException {
        // 获得webElement的位置和大小。
        Point location = webElement.getLocation();
        Dimension size = webElement.getSize();
        // 创建全屏截图。
        BufferedImage originalImage =
                ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
        // 截取webElement所在位置的子图。
        BufferedImage croppedImage = originalImage.getSubimage(
                430,
                285,
                110,
                45);
        return croppedImage;
    }

    public static String getCode(WebDriver driver) throws Exception {
        WebElement ele = driver.findElement(By.xpath(".//*[@alt='验证码']"));
        snapshot2(driver,"111.jpg");
        BufferedImage inputbig = createElementImage(driver,ele);
        ImageIO.write(inputbig, "png", new File("d://temp//123.jpg"));
        Hello Code = new Hello();
        return Code.getCode();
    }
}
