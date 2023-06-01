import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class Test_Cookie {

    private static final Logger logger = LogManager.getLogger("Test_Cookie");

    @BeforeAll
    public static void wb() {

        WebDriverManager.chromedriver().setup();
    }
    private WebDriver driver;



    @BeforeEach
    public void setUp() {
      ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru");
        driver.manage().window().maximize();
        logger.info(driver.manage().getCookies());


    }




        @Test
        public void login() {

            driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[action*=\"login\"] [name=\"email\"]")))
                    .sendKeys("tel212302@gmail.com");
            driver.findElement(By.xpath("//div[2]/form/div[3]/input"))
                    .sendKeys("12345");
            driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();


        }



        @AfterEach
        public void close () {
            if (driver != null)
                driver.quit();
        }
    }

