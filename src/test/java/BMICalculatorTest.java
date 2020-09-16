import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;

public class BMICalculatorTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://healthunify.com/bmicalculator/");
        driver.manage().window().maximize();
    }
    @Test
    public void checkNormalCategoryWithKgCms() {

        WebElement weightInput = driver.findElement(By.name("wg"));
        weightInput.sendKeys("75");

        WebElement heightInput = driver.findElement(By.name("ht"));
        heightInput.sendKeys("185");

        WebElement calculateButton = driver.findElement(By.name("cc"));
        calculateButton.click();

        WebElement categoryPole = driver.findElement(By.name("desc"));
        String actualResult = categoryPole.getAttribute("value");

        String expectedResult = "Your category is Normal";

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkUnderweightCategoryWithKgCms() {

        WebElement weightInput = driver.findElement(By.name("wg"));
        weightInput.sendKeys("45");

        WebElement heightInput = driver.findElement(By.name("ht"));
        heightInput.sendKeys("170");

        WebElement calculateButton = driver.findElement(By.name("cc"));
        calculateButton.click();

        WebElement categoryPole = driver.findElement(By.name("desc"));
        String actualResult = categoryPole.getAttribute("value");

        String expectedResult = "Your category is Underweight";

        assertEquals(actualResult, expectedResult);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}