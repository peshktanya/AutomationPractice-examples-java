package contact.us;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ContactUsTests {
    WebDriver driver;

    @BeforeClass
    public void SetUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void SetUpMethod(){

        driver.navigate().to("http://automationpractice.com/");
        driver.findElement(By.linkText("Contact us")).click();
    }

    @AfterClass
    public void TearDown(){
        driver.quit();
    }

    @Test(timeOut = 3000)
    public void ICanSubmitSuccessfully()  {

        //dropDown
        driver.findElement(By.id("id_contact")).click();
        new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Webmaster");
        driver.findElement(By.id("id_contact")).click();
        driver.findElement(By.id("email")).sendKeys("qwerty@test.net");
        driver.findElement(By.id("id_order")).sendKeys("qwerty");
        driver.findElement(By.id("message")).sendKeys("Message in txtx");
        WebElement submitButton = driver.findElement(By.id("submitMessage"));
        submitButton.click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='center_column']/p")).getText().contains("Your message has been successfully sent to our team.") );
    }

    @Test
    public void CheckErrorIfInvalidEmail() {
        new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Customer service");
        driver.findElement(By.id("email")).sendKeys("qwerty");
        driver.findElement(By.id("id_order")).sendKeys("some Order reference");
        driver.findElement(By.id("message")).sendKeys("message");
        driver.findElement(By.id("submitMessage")).click();

        Assert.assertEquals(
                driver.findElement(By.cssSelector(".alert-danger li")).getText(),"Invalid email address.");
    }

    @Test
    public void CheckErrorIfEmptyMessage() {

        new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Webmaster");
        driver.findElement(By.id("email")).sendKeys("test@test.test");
        driver.findElement(By.id("id_order")).sendKeys("some order");
        driver.findElement(By.id("submitMessage")).click();

        Assert.assertEquals(
                driver.findElement(By.cssSelector(".alert-danger li")).getText(),"The message cannot be blank.");
    }

    @Test(enabled = false)
    public void CheckErrorIfEmptySubject() {

        new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("-- Choose --");
        driver.findElement(By.id("email")).sendKeys("test@test.test");
        driver.findElement(By.id("id_order")).sendKeys("some order");
        driver.findElement(By.id("message")).sendKeys("message");
        driver.findElement(By.id("submitMessage")).click();

        Assert.assertEquals(
                driver.findElement(By.cssSelector(".alert-danger li")).getText(),
                "Please select a subject from the list provided.", "Wrong error text");
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
