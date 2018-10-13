package log.in;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AutomationPracticeTest {
    WebDriver driver;

    @BeforeSuite
    public void SetUpSuite() {
    System.out.println("Tests started...");
    }


    @AfterSuite
    public void TearDownSuite() {
        System.out.println("Tests finished...");
    }

    @BeforeClass
    public void SetUpTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com/");
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email_create")).sendKeys("q22@test.qqq");
        driver.findElement(By.id("SubmitCreate")).click();

    }

    @AfterClass
    public void TearDown(){
        driver.quit();
    }



    @Test
    public void ICanCheckRadioButton(){

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("id_gender2")).click();

    }

    @Test(groups = "Regression")
    public void ICanSelectFromDropDown(){

        Select dropDownDays = new Select(driver.findElement(By.id("days")));
        dropDownDays.selectByValue("4");
        Select dropDownMonths = new Select(driver.findElement(By.id("months")));
        dropDownMonths.selectByValue("10");
        Select dropDownYears = new Select(driver.findElement(By.id("years")));
        dropDownYears.selectByValue("2000");
    }

    @Test(groups = "Regression")
    public void ICanMarkCheckBoxes(){
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
    }


    @Test
    public void ICanSelectCountry(){
        Select dropDownDays = new Select(driver.findElement(By.id("id_country")));
        dropDownDays.selectByValue("21");
    }

    @Test(dependsOnMethods = "ICanSelectCountry")
    public void ICanFillInCityAndState(){
        Select dropDownDays = new Select(driver.findElement(By.id("id_state")));
        dropDownDays.selectByValue("5");

        driver.findElement(By.id("city")).sendKeys("California");
        driver.findElement(By.id("postcode")).sendKeys("012345");
    }

}
