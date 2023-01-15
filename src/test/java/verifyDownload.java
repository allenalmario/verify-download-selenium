import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class verifyDownload {
    public static void main(String[] args) throws IOException {
        //setup chrome driver
        WebDriverManager.chromedriver().setup();

        //change directory of downloads for chrome driver
        //instance of chrome options has methods
        //for setting chrome driver specific capabilities
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        //adding element to the map
        //key, value
        //directory, path for folder
        prefs.put("download.default_directory", System.getProperty("user.dir")+ File.separator+"downloads");

        options.setExperimentalOption("prefs", prefs);

        //create a chrome driver instance
        WebDriver driver = new ChromeDriver(options);

        // max window
        driver.manage().window().maximize();

        //go to url
        driver.get("http://autopract.com/selenium/download.html");

        //find download link element
        WebElement downloadLink = driver.findElement(By.id("download"));

        //click on download link
        downloadLink.click();

        Assert.assertTrue(FileUtil.isFileDownload("sample", "csv", 10000));

        //close browser
        driver.close();
    }
}
