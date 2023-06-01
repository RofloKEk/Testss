import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test_Kiosk  {
    public WebDriver driver;

    @BeforeAll
    public static void wb() {

        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        driver.get("https://p.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");


    }

    @Test
            public void kiosk() {

        WebElement element = driver.findElement(By.xpath("//a[@href='assets/images/p1.jpg']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"pp_hoverContainer\"]")));
       assertTrue(driver.findElement(By.cssSelector("[class=\"pp_hoverContainer\"]")).isDisplayed());


    }


    @AfterEach
    public void close() {
        if (driver != null)
            driver.quit();
    }

}



