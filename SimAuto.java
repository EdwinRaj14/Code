import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class SimAuto {
    public static String userName;
    public static String password;
    public static int status;
    public static String simStatus;
    public static Scanner scanner = new Scanner(System.in);
    public static WebDriver driver;
    public static int priority;
    public static String simPriority;
    public static int startDate;
    public static int startMonth;
    public static int startYear;
    public static int endDate;
    public static int endMonth;
    public static int endYear;
    public static String start = "02/01/2023";
    public static String end = "03/13/2023";
    public static String drive= "chrome";



    static Excelread read = new Excelread();

    public static void main(String args[]) throws IOException, AWTException {
        read.deleteExcelFile();
        getValuesforLogin();
        // simStatus();
        simPriority();
        startDateSelection();
        endDateSelection();
        urlLaunch();
        //  statusSelection();
        SimPrioritySelection();
        dateClick();
        downloadExcel();
        read.excelRead();
        read.mailReport();
        scanner.close();
    }

    public static void getValuesforLogin() {
        // System.out.println("Enter Your userName without @amazon.com");
        // userName = scanner.next();
        userName = "edwinaz";
        // System.out.println("Enter Your Yuibikey pin");
        // password = scanner.next();
        password = "411013103006";
    }

    public static void startDateSelection() {
        /*System.out.println("Enter the start date you want 01 to 31");
        startDate = scanner.nextInt();
        if (startDate > 31) {
            System.out.println("Date shouldn't be greater than 31");
            startDateSelection();
        }
        System.out.println("Enter the start month you want\n1.January\n 2.Feb \n 3.March\n 4.April \n 5.May \n 6.June \n 7.July \n 8.August \n 9.September \n 10.October \n 11.November \n 12.December");
        startMonth = scanner.nextInt();
        if (startMonth > 12) {
            System.out.println("Month shouldn't be greater than 12");
            startDateSelection();
        }
        System.out.println("Enter the start year you want");
        startYear = scanner.nextInt();
        System.out.println(startDate + "/" + startMonth + "/" + startYear);*/
        startDate = 2;
        startMonth = 2;
        startYear = 2023;
    }

    public static void endDateSelection() {
        /*
        System.out.println("Enter the end date you want 01 to 31");
        endDate = scanner.nextInt();
        if (endDate > 31) {
            System.out.println("Date shouldn't be greater than 31");
            endDateSelection();
        }
        System.out.println("Enter the end month you want\n1.January\n 2.Feb \n 3.March\n 4.April \n 5.May \n 6.June \n 7.July \n 8.August \n 9.September \n 10.October \n 11.November \n 12.December");
        endMonth = scanner.nextInt();
        if (endMonth > 12) {
            System.out.println("Month shouldn't be greater than 12");
            endDateSelection();
        }
        System.out.println("Enter the end year you want");
        endYear = scanner.nextInt();
        System.out.println("0" + endDate + "/" + endMonth + "/" + endYear);
        */
    }

    public static void urlLaunch() throws MalformedURLException {
        //Webpage initilization
        if (drive=="chrome") {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
          //  DesiredCapabilities cp = new DesiredCapabilities();
            //cp.setCapability(ChromeOptions.CAPABILITY,options);
            //options.merge(cp);
            //options.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(options);
        }
        else {
            driver= new FirefoxDriver();
            System.setProperty("webdriver.gecko.driver", "geckodriver");
        }
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
       driver.get("https://tinyurl.com/bdf5cfd5");
        //Entering user name and password for
       try{ Thread.sleep(1000);}
       catch (Exception e)
       {
           e.printStackTrace();
       }
        driver.findElement(By.id("user_name_field")).sendKeys(userName);
        driver.findElement(By.id("user_name_btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"password_field\"]")).sendKeys(password);
        driver.findElement(By.id("password_btn")).click();
    }

    //clicking status for sim
    public static void statusSelection() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("s2id_filter-dropdown")).click();
        WebElement status = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[41]"));
        // Javascript executor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", status);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[41]")).click();
        //sim status selection
        if (simStatus == "Open") {
            driver.findElement(By.xpath("//input[@value='Open']")).click();
        } else if (simStatus == "Resolved") {
            driver.findElement(By.xpath("//input[@value='Resolved']")).click();
        }
        driver.findElement(By.xpath("//button[@data-name='add-search-filter-button']")).click();
    }

    public static void simStatus() {
        System.out.println("Do you want open or resolved sim type 1 or 2.\n 1.open sim\n 2.Resolved Sim");
        status = scanner.nextInt();
        if (status == 1) {
            simStatus = "Open";
        } else if (status == 2) {
            simStatus = "Resolved";
        } else {
            System.out.println("Your input is not matching with our requirement. please select 1 or 2.");
            simStatus();
        }
        System.out.println("you selected " + simStatus + " sim");
    }

    public static void simPriority() {

        System.out.println("Please enter the priority\n 1.Low\n 2.Medium\n 3.High\n 4.Low&High\n 5.Low&Medium\n 6.Medium&High\n 7.Low&Medium&High ");
        priority = scanner.nextInt();
        if (priority == 1) {
            simPriority = "Low";
        } else if (priority == 2) {
            simPriority = "Medium";
        } else if (priority == 3) {
            simPriority = "High";
        } else if (priority == 4) {
            simPriority = "Low&High";
        } else if (priority == 5) {
            simPriority = "Low&Medium";
        } else if (priority == 6) {
            simPriority = "Medium&High";
        } else if (priority == 7) {
            simPriority = "Low&Medium&High";
        } else {
            System.out.println("Enter value between 1 to 7");
            simPriority();
        }
        System.out.println("you selected " + simPriority + " sim");
    }

    public static void SimPrioritySelection() {
        try{ Thread.sleep(60000);}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("s2id_filter-dropdown")).click();
        WebElement priorities = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[34]/div"));
        // Javascript executor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", priorities);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[34]/div")).click();
        if (simPriority == "Low") {
            driver.findElement(By.xpath("//input[@value='Low']")).click();

        }
        if (simPriority == "Medium") {
            driver.findElement(By.xpath("//input[@value='Medium']")).click();

        }
        if (simPriority == "High") {
            driver.findElement(By.xpath("//input[@value='High']")).click();
        }
        if (simPriority == "Low&High") {
            driver.findElement(By.xpath("//input[@value='Low']")).click();
            driver.findElement(By.xpath("//input[@value='High']")).click();
        }
        if (simPriority == "Low&Medium") {
            driver.findElement(By.xpath("//input[@value='Low']")).click();
            driver.findElement(By.xpath("//input[@value='Medium']")).click();
        }
        if (simPriority == "Medium&High") {
            driver.findElement(By.xpath("//input[@value='High']")).click();
            driver.findElement(By.xpath("//input[@value='Medium']")).click();
        }
        if (simPriority == "Low&Medium&High") {
            driver.findElement(By.xpath("//input[@value='High']")).click();
            driver.findElement(By.xpath("//input[@value='Medium']")).click();
            driver.findElement(By.xpath("//input[@value='Low']")).click();
        }
        driver.findElement(By.xpath("//button[@data-name='add-search-filter-button']")).click();
        //  driver.findElement(By.id("initiate-search")).click();
    }

    public static void label() {
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div/span")).click();

    }


    public static void dateClick() {
        driver.findElement(By.id("s2id_filter-dropdown")).click();
        WebElement dates = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[11]/div"));
        // Javascript executorx
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dates);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[11]/div")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[4]/div/div[1]/span/span/i")).click();
        // driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(startMonth + "/" + startDate + "/" + startYear);
        driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(start);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.TAB);
        //  driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(endMonth + "/" + endDate + "/" + endYear);
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(end);
        driver.findElement(By.xpath("//button[@data-name='add-search-filter-button']")).click();
        driver.findElement(By.id("initiate-search")).click();
    }

    public static void downloadExcel() throws AWTException {
        driver.findElement(By.xpath("//button[@data-toggle='dropdown']")).click();
        driver.findElement(By.xpath("//a[@data-name='export-search-results']")).click();
        driver.findElement(By.id("xls")).click();
        driver.findElement(By.xpath("//*[@id='submit-custom-export-job']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[@id=\"job-details\"]/div/section/div/div/table/tbody/tr/td[1]/a")).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER); //press enter key
        robot.keyRelease(KeyEvent.VK_ENTER); //release enter key
    }
}

