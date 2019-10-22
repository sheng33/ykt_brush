import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaiduDemo {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //指定浏览器驱动路径
        System.setProperty("webdriver.chrome.driver","D:\\xt\\chromedriver.exe");
        //初始化浏览器名为driver
        WebDriver driver = new ChromeDriver();

        //窗口最大化
        driver.manage().window().maximize();
        //设置窗口大小
//        driver.manage().window().setSize(new Dimension(480,800));
        //打开网址
        driver.get("http://www.baidu.com");

        System.out.println("this is URL:"+driver.getCurrentUrl());
        System.out.println("this is URL:"+driver.getTitle());
//        try{
//            String url = "百度一下，你就知道";
//            assert url.equals(driver.getTitle());
//            System.out.println("Yes");
//        }catch (AssertionError  e){
//            driver.quit();
//            e.printStackTrace();
//        }
        //点击链接
//        driver.findElement(By.linkText("新闻")).click();
//        System.out.println("click 新闻");
//        Thread.sleep(2000);
//        //后退
//        driver.navigate().back();
//        System.out.println("back...this www.baidu.com");
//        Thread.sleep(2000);
//        //浏览器返回
//        driver.navigate().forward();
//        System.out.println("forward..."+driver.getCurrentUrl());
//        Thread.sleep(2000);
        //获取元素的标签
//       String str =  driver.findElement(By.id("su")).getTagName();
//        System.out.println(str);
        //xpath方法
//        WebElement str = driver.findElement(By.xpath("//*[@id='su']"));
//        System.out.println(str.getText());
        //linkText定位
        WebElement title = driver.findElement(By.linkText("新闻"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",title,"background: orange; border: 2px solid red;");
//        //退出
        Thread.sleep(2000);
        driver.quit();

    }
}
