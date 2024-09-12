import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestOtus {


    private String login = "tel212302@gmail.com";
    private String pas = "12345";


    @BeforeAll
    public static void wb() {
        WebDriverManager.chromedriver().setup();
    }

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://otus.ru");


    }

    @AfterEach
    public void close() {
        if (driver != null)
            driver.quit();
    }


    @Test
    public void Auth() {



        loginInOtus();

        enterLk();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_fname")));
        clearAndEnter(By.id("id_fname"), "Тимур");
        clearAndEnter(By.id("id_lname"), "Банзарон");
        clearAndEnter(By.id("id_fname_latin"), "Timur");
        clearAndEnter(By.id("id_lname_latin"), "Banzaron");
        clearAndEnter(By.id("id_blog_name"), "Тим");
        clearAndEnter(By.cssSelector("[name = date_of_birth]"), "03.05.1999");
        driver.findElement(By.xpath("//div[@data-ajax-slave='/lk/biography/cv/lookup/cities/by_country/']")).click();
        driver.findElement(By.xpath("//button[@title='Россия']")).click();
        driver.findElement(By.xpath("//div[@class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-dependent-slave-city js-lk-cv-custom-select']")).click();
        driver.findElement(By.xpath("//button[@title='Москва']")).click();
        driver.findElement(By.cssSelector("div:nth-child(3) > div.container__col.container__col_9.container__col_md-8.container__col_middle")).click();
        driver.findElement(By.cssSelector("[title=\"Продвинутый (Advanced)\"]")).click();
        driver.findElement(By.cssSelector("div.container__col.container__col_9.container__col_md-8.container__col_middle > label:nth-child(1) > span")).click();
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.xpath("//option[@value='m']")).click();
        clearAndEnter(By.id("id_company"), "NIKAKAYA");
        clearAndEnter(By.id("id_work"), "tozheNIKAKAYA");
        driver.findElement(By.cssSelector("[title=\"Сохранить и продолжить\"]")).submit();

        driver.quit();


        loginInOtus();

        enterLk();

        Assertions.assertEquals("Тимур", driver.findElement(By.id("id.fname")).getAttribute("value"));
    }

    public void loginInOtus() {
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[action*=\"login\"] [name=\"email\"]")))
                .sendKeys(login);
        driver.findElement(By.xpath("//div[2]/form/div[3]/input"))
                .sendKeys(pas);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
    }

    private void clearAndEnter(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys();
    }

    private void enterLk() {
        driver.get("https://otus.ru/lk/biography/personal/");
    }

}

