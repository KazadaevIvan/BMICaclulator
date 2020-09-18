import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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

    @Test
    public void checkObeseCategoryWithKgCms() {

        WebElement weightInput = driver.findElement(By.name("wg"));
        weightInput.sendKeys("100");

        WebElement heightInput = driver.findElement(By.name("ht"));
        heightInput.sendKeys("175");

        WebElement calculateButton = driver.findElement(By.name("cc"));
        calculateButton.click();

        WebElement categoryPole = driver.findElement(By.name("desc"));
        String actualResult = categoryPole.getAttribute("value");

        String expectedResult = "Your category is Obese";

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkSIUnitsWithLbFt() {

        Select weightUnits = new Select(driver.findElement(By.name("opt1")));
        weightUnits.selectByValue("pounds");

        WebElement weightInput = driver.findElement(By.name("wg"));
        weightInput.sendKeys("165");

        Select heightFt = new Select(driver.findElement(By.name("opt2")));
        heightFt.selectByValue("6");

        Select heightInches = new Select(driver.findElement(By.name("opt3")));
        heightInches.selectByValue("1");

        WebElement calculateButton = driver.findElement(By.name("cc"));
        calculateButton.click();

        WebElement SIUnitsPole = driver.findElement(By.name("si"));
        String actualResult = SIUnitsPole.getAttribute("value");

        String expectedResult = "21.91";

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkNormalCategoryAfterSwitchingWeightAndHeightUnits() {

        WebElement weightInput = driver.findElement(By.name("wg"));
        weightInput.sendKeys("75");

        WebElement heightInput = driver.findElement(By.name("ht"));
        heightInput.sendKeys("185");

        WebElement calculateButton = driver.findElement(By.name("cc"));
        calculateButton.click();

        WebElement categoryPole = driver.findElement(By.name("desc"));
        String kgCmResult = categoryPole.getAttribute("value");

        Select weightUnits = new Select(driver.findElement(By.name("opt1")));
        weightUnits.selectByValue("pounds");

        Select heightFt = new Select(driver.findElement(By.name("opt2")));
        heightFt.selectByValue("6");

        Select heightInches = new Select(driver.findElement(By.name("opt3")));
        heightInches.selectByValue("1");

        calculateButton.click();

        String lbFtInchResult = categoryPole.getAttribute("value");

        assertEquals(lbFtInchResult, kgCmResult);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}