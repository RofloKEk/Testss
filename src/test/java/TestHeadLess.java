import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestHeadLess {

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
        driver.get("https://duckduckgo.com/");
        options.addArguments("headless");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test
    public void testHeadLess() {

        driver.findElement(By.name("q")).sendKeys("ОТУС");
        driver.findElement(By.id("search_button_homepage")).click();
        WebElement firstResult = driver.findElement(By.cssSelector("#r1-0 > div.ikg2IXiCD14iVX7AdZo1 > h2 > a > span"));
        String expectedText = "Онлайн‑курсы для профессионалов, дистанционное обучение";
        if (firstResult.getText().equals(expectedText)) {
            System.out.println("Первый результат соответствует ожидаемому тексту");
        } else {
            System.out.println("Первый результат не соответствует ожидаемому тексту");
        }
    }
        @AfterEach
        public void close() {
            if (driver != null)
                driver.quit();
        }

    }




