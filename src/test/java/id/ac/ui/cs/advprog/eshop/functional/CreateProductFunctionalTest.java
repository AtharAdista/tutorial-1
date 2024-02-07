package id.ac.ui.cs.advprog.eshop.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)

public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }


    @Test
    void createProductPageTitle(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();

        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void testCreateButton(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/product/list");

        WebElement createButton = driver.findElement(By.xpath("/html/body/div/a"));
        createButton.click();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = baseUrl + "/product/create";
        assertEquals(expectedUrl, currentUrl);
    }

    @Test
    void testIfFormValid(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Sampo Cap Bango");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("5");

        WebElement nameInForm= driver.findElement(By.xpath("/html/body/div/form/div[1]/input"));
        String productNameText = nameInForm.getAttribute("value");
        assertEquals("Sampo Cap Bango", productNameText);

        WebElement quantityInForm= driver.findElement(By.xpath("/html/body/div/form/div[2]/input"));
        String productQuantityText = quantityInForm.getAttribute("value");
        assertEquals("05", productQuantityText);

    }

    @Test
    void testIfCreateProductWorks(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Sampo Cap Bango");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("10");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        WebElement createButton = driver.findElement(By.xpath("/html/body/div/a"));
        createButton.click();

        WebElement nameInput2 = driver.findElement(By.id("nameInput"));
        nameInput2.sendKeys("Sampo Cap ABC");

        WebElement quantityInput2 = driver.findElement(By.id("quantityInput"));
        quantityInput2.sendKeys("5");

        WebElement submitButton2 = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton2.click();

        WebElement tableData = driver.findElement(By.xpath("/html/body/div/table"));

        WebElement productNameElement = tableData.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[1]"));
        String productNameText = productNameElement.getText();
        assertEquals("Sampo Cap Bango", productNameText);

        WebElement productQuantityElement = tableData.findElement(By.xpath("tbody/tr[1]/td[2]"));
        String productQuantityText = productQuantityElement.getText();
        assertEquals("10", productQuantityText);

        WebElement productNameElement1 = tableData.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[1]"));
        String productNameText1 = productNameElement1.getText();
        assertEquals("Sampo Cap ABC", productNameText1);

        WebElement productQuantityElement1 = tableData.findElement(By.xpath("tbody/tr[2]/td[2]"));
        String productQuantityText1 = productQuantityElement1.getText();
        assertEquals("5", productQuantityText1);

    }



}