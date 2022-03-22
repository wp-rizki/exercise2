package function;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class userFunction {
    public static WebDriver driver=null;
    public static WebDriverWait waitVar=null;
    public static String URL="https://gist.github.com/rizfirm";
    public static String username="rizfirm";
    public static String password="p0larate";
    public static String gistTitle="New Gist";
    public static String gistBody="+ Just test";

    public void createServer() {
        driver =  new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

//    public static void main(String[] args) throws InterruptedException {
//        driver =  new ChromeDriver();
//
//        driver.manage().window().maximize();
//        driver.get(URL);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//        Thread.sleep(2000);
//
//    }

    public void tearDown(){
        driver.quit();
    }

    public void login() throws InterruptedException {
        driver.findElement(By.cssSelector("body > div.position-relative.js-header-wrapper > div > div.Header-item.f4.mr-0 > a.HeaderMenu-link.no-underline.mr-3")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //input username
        driver.findElement(By.xpath("//input[@id='login_field']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='login_field']")).sendKeys(username);
        Thread.sleep(2000);
        //ipunt password
        driver.findElement(By.xpath("//div[@id='login']/div[3]/form/div/input[@id='password']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='login']/div[3]/form/div/input[@id='password']")).sendKeys(password);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@name='commit']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void clickAdd(){
        driver.findElement(By.cssSelector("svg.octicon.octicon-plus.d-none.d-md-inline-block > path")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void createGist() throws InterruptedException{
        driver.findElement(By.name("gist[contents][][name]")).click();
        driver.findElement(By.name("gist[contents][][name]")).sendKeys(gistTitle);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#code-editor")).click();
        driver.findElement(By.cssSelector("#code-editor")).sendKeys(gistBody);
        Thread.sleep(1000);

        //scroll
        WebElement element1 = driver.findElement(By.name("gist[contents][][name]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"new_gist\"]/div/div[2]/div/details[@class='details-reset details-overlay select-menu BtnGroup-parent position-relative']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"new_gist\"]/div/div[2]/div/details/details-menu/label[2]/div/span[@class='select-menu-item-heading' and (text()='Create public gist')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#new_gist > div > div.d-flex.flex-items-center.flex-justify-between > div > button")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void verifyGistExist() throws InterruptedException{
        String getString = driver.findElement(By.cssSelector("strong.css-truncate-target.mr-1 > a")).getText();
        System.out.println(getString);
        if(getString.contains(gistTitle)){
            System.out.println("Text is Matched");
        }else{
            System.out.println("Text is not Matched");
        }
        Thread.sleep(2000);
    }

    public void editGist() throws InterruptedException{
        Thread.sleep(2000);
//        driver.findElement(By.xpath("//*[@id=\"gist-pjax-container\"]/div/div/div[2]/div[2]/div[1]/div/div[2]/span/a[2]/strong[@class='css-truncate-target' and (text()='"+gistTitle+"')]")).click();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"gist-pjax-container\"]/div[1]/div/div[1]/ul/li[1]/a[@class='btn btn-sm' and @aria-label=\"Edit this Gist\"]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"code-editor\"]/div/pre/span[@role='presentation']")).clear();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"code-editor\"]/div/pre/span[@role='presentation']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"code-editor\"]/div/pre/span[@role='presentation']")).sendKeys(" Update Newww");
        Thread.sleep(1000);

        //scroll
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"code-editor\"]/div/pre/span[@role='presentation']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
        Thread.sleep(1500);

        //submit
        driver.findElement(By.xpath("//*/button[@class='btn-primary btn' and @type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
    }

    public void deleteGist() throws InterruptedException{
        //Thread.sleep(2000);
        //driver.findElement(By.xpath("//strong[@class='css-truncate-target' and (text()='asd')]")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@class='btn-danger btn-sm btn' and @aria-label='Delete this Gist']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(1000);

        driver.switchTo().alert().accept();

    }

    public void verifyDeleteGist() throws InterruptedException{
        String getString = driver.findElement(By.xpath("//*/text()[normalize-space(.)='Gist deleted successfully.']/parent::*")).getText();
        System.out.println(getString);
        if(getString.contains("Gist deleted successfully.")){
            System.out.println("Text is Matched (Deleted)");
        }else{
            System.out.println("Text is not Matched (Deleted)");
        }
        Thread.sleep(2000);
    }

    public void viewAllGist() throws InterruptedException {
        driver.findElement(By.cssSelector("a.js-selected-navigation-item.selected.UnderlineNav-item")).click();
        Thread.sleep(3000);

    }


}
