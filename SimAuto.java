import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;
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
    public static int date;
    public static int month;
    public static int year;
    public static int startDate;


    public static void main(String args[]) {
        getValuesforLogin();
        simStatus();
        simPriority();
        dateSelection();
        urlLaunch();
        statusSelection();
        SimPrioritySelection();
        dateClick();
        scanner.close();
    }

    public static void urlLaunch() {
        //Webpage initilization
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
        driver.get("https://tinyurl.com/2kegyhge");
        //Entering user name and password for sim
        driver.findElement(By.id("user_name_field")).sendKeys(userName);
        driver.findElement(By.id("user_name_btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"password_field\"]")).sendKeys(password);
        driver.findElement(By.id("password_btn")).click();
    }

    //clicking status for sim
    public static void statusSelection() {
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

    public static void getValuesforLogin() {
        System.out.println("Enter Your userName without @amazon.com");
        userName = scanner.next();
        System.out.println("Enter Your Yuibikey pin");
        password = scanner.next();
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
        System.out.println("you selected "+simStatus+" sim");
    }

    public static void simPriority() {

        System.out.println("Please enter the priority\n 1.Low\n 2.Medium\n 3.High");
        priority = scanner.nextInt();
        if (priority == 1) {
            simPriority = "Low";
        } else if (priority == 2) {
            simPriority = "Medium";
        } else if (priority == 3) {
            simPriority = "High";
        } else {
            System.out.println("Enter value between 1 to 3");
            simPriority();
        }
        System.out.println("you selected "+simPriority+" sim");
    }
    public static void SimPrioritySelection(){
        driver.findElement(By.id("s2id_filter-dropdown")).click();
        WebElement priorities = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[34]/div"));
        // Javascript executor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", priorities);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[34]/div")).click();
        if (simPriority=="Low")
        {
            driver.findElement(By.xpath("//input[@value='Low']")).click();

        }
        if (simPriority=="Medium")
        {
            driver.findElement(By.xpath("//input[@value='Medium']")).click();

        }
        if (simPriority=="High")
        {
            driver.findElement(By.xpath("//input[@value='High']")).click();
        }
        driver.findElement(By.xpath("//button[@data-name='add-search-filter-button']")).click();
       driver.findElement(By.id("initiate-search")).click();
    }
    public static void dateSelection()
    {
        System.out.println("Enter the date you want 01 to 31");
        date=scanner.nextInt();
        System.out.println("Enter the month you want\n1.January\n 2.Feb \n 3.March\4 4.April \n 5.May \n 6.June \n 7.July \n 8.August \n 9.September \n 10.October \n 11.November \n 12.December");
        month=scanner.nextInt();
        System.out.println("Enter the year you want");
        year=scanner.nextInt();
        System.out.println(date+"/"+month+"/"+year);
        startDate =date+month+year;

    }
    public static void dateClick(){
        driver.findElement(By.id("s2id_filter-dropdown")).click();
        WebElement dates = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[11]/div"));
        // Javascript executorx
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dates);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[11]/div")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[4]/div/div[1]/span/span/i")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(month+"/"+date+"/"+year);
        //driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.RETURN);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.TAB);
        driver.findElement(By.cssSelector("//input[@type='text']")).sendKeys(month+"/"+date+"/"+year);
       /* String sMonth=driver.findElement(By.className("ui-datepicker-month")).getText();
        System.out.println(sMonth);
        String startYear= sMonth.split(" ")[0].trim();
        String startMonth= sMonth.split(" ")[1].trim();

        while(!(startMonth.equals("June")&&startYear.equals("2022"))){
            driver.findElement(By.xpath("//a[@title='Prev']")).click();
             sMonth=driver.findElement(By.className("ui-datepicker-month")).getText();
            System.out.println(sMonth);
            startYear= sMonth.split(" ")[0].trim();
            startMonth= sMonth.split(" ")[1].trim();

        }
        driver.findElement(By.xpath("/a[@text()='23']")).click();*/
    }
}
