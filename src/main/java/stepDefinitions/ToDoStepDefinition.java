package stepDefinitions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ToDoStepDefinition {

  WebDriver driver;
  public static String status = "Passed";
  public static String perform = System.getenv("Performance") == null ? "No" : "Yes";
  public static String exp_dedicatedproxy = System.getProperty("dedicated_proxy") == null ? null : System.getProperty("dedicated_proxy");
  public static boolean isdedicated = false;
  public static boolean isgeolocation = false;
  public static boolean isVersionGreaterThan(String version1, String version2) {
    String[] parts1 = version1.split("\\.");
    String[] parts2 = version2.split("\\.");

    int length = Math.max(parts1.length, parts2.length);

    for (int i = 0; i < length; i++) {
      int v1 = (i < parts1.length) ? Integer.parseInt(parts1[i]) : 0;
      int v2 = (i < parts2.length) ? Integer.parseInt(parts2[i]) : 0;

      if (v1 < v2) {
        return false;
      } else if (v1 >= v2) {
        return true;
      }
    }

    return false; // Both versions are equal
  }

  @Given("^I launch Session on lambdatest with (.*?)$")
  public void user_already_on_home_page(String caps) throws Exception {
    String browser = System.getProperty("browser") == null ? "Chrome" : System.getProperty("browser");
    String version = System.getProperty("version") == null ? "latest" : System.getProperty("version");
    boolean BrowserVerify = System.getProperty("BrowserVerify") == null ? false : System.getProperty("BrowserVerify").equals("true");
    String resolution = System.getProperty("resolution") == null ? "1920x1080" : System.getProperty("resolution");
    String selenium_version = System.getProperty("selenium_version") == null ? "3.13.0" : System.getProperty("selenium_version");
    boolean videotogle = System.getProperty("nonselenium") == null ? true : false;
    String os = System.getenv("HYPEREXECUTE_PLATFORM");
    version = browser.equals("Edge") && os.contains("win") ? "canary" : version;
    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random rnd = new Random();
    StringBuilder rndstr = new StringBuilder(10);
    for (int i = 0; i < 10; i++) {
      rndstr.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    System.out.println(" generatedString: " + rndstr.toString());
    File object = new File(".//Files//generatedString-" + rndstr.toString() + ".txt");
    object.createNewFile();
    String path = System.getenv("default_directory");
    System.out.println(" system property: " + path);
    String username = System.getenv("LT_USERNAME");
    String accesskey = System.getenv("LT_ACCESS_KEY");
    String envvar = System.getenv("mayur1");
    System.out.println("ENV VAR: " + envvar);
    String scenario = System.getProperty("scenario");
    String jobid = System.getenv("JOB_ID");
    System.out.println("BROWSER: " + browser);
    System.out.println("VERSION: " + version);
    System.out.println("RESOLUTION: " + resolution);
    System.out.println("HYPEREXECUTE_PLATFORM: " + System.getenv("HYPEREXECUTE_PLATFORM"));
    System.out.println("HYPEREXECUTE_WORKING_DIR: " + System.getenv("HYPEREXECUTE_WORKING_DIR"));
    System.out.println("LT_USER: " + username);
    System.out.println("LT_KEY: " + accesskey);
    System.out.println("JOB_ID: " + jobid);
    String[] files = { "Code Arshad Cp Round 2.txt" };
    String stage_number = System.getenv("HYE_TEST_NUMBER");
    DesiredCapabilities capability = new DesiredCapabilities();
    capability.setCapability(CapabilityType.BROWSER_NAME, browser);
    capability.setCapability(CapabilityType.VERSION, version);
    capability.setCapability(CapabilityType.PLATFORM, os);
    capability.setCapability("resolution", resolution);
    capability.setCapability("build", "Cucu-Sel-TestNG-" + jobid);
    capability.setCapability("name", scenario);
    capability.setCapability("network", true);
    capability.setCapability("video", videotogle);
    capability.setCapability("console", true);
    capability.setCapability("visual", true);
    String[] customTags = { selenium_version };
    capability.setCapability("tags", customTags);
    capability.setCapability("selenium_version", selenium_version);
    if (caps.contains("geolocation")) {
      capability.setCapability("geoLocation", caps.split(":")[1]);
      isgeolocation = true;
    }
    if (caps.contains("unboundregion")) {
      capability.setCapability("unboundRegion", caps.split(":")[1]);
    }

    if (exp_dedicatedproxy != null && caps.contains("dedicatedproxy")) {
      capability.setCapability("dedicatedProxy", true);
      isdedicated = true;
    }

    try {
      FileWriter myWriter = new FileWriter(".//Files//generatedString-" + rndstr.toString() + ".txt");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
    System.out.println(gridURL);
    driver = new RemoteWebDriver(new URL(gridURL), capability);
    if (System.getProperty("selenium_version") != null) {
      int port_number = 4444 + Integer.parseInt(stage_number);
      if (port_number > 4448) {
        port_number = 4444 + Integer.parseInt(stage_number) + 1;
      }
      System.out.println("stage Number:- " + stage_number);
      System.out.println("Port Number:- " + port_number);
      // driver.get("http://localhost:" + port_number + "/ui#");
      // try {
      //   TimeUnit.SECONDS.sleep(4);
      // } catch (InterruptedException e) {
      //   e.printStackTrace();
      // }
      // String d_version = null;
      // if (isVersionGreaterThan(selenium_version, "4.0.0")) {
      //   d_version = driver.findElement(By.xpath("//h1/..//p")).getText();
      // } else {
      //   d_version = driver.findElement(By.cssSelector(".se-version")).getText();
      // }
      // System.out.println("Driver Version:- " + d_version);
      // Assert.assertTrue(d_version.contains(selenium_version));

      String userAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
      System.out.println("userAgent: " + userAgent);
      System.out.println("clientAgent: " + browser + "/" + version);
      String jsonBrowserKey = browser.equalsIgnoreCase("edge") ? "Edge" : browser; 
      browser = browser.toLowerCase().equals("edge") ? "edg" : browser;
      System.out.println("clientAgent adjusted: " + browser + "/" + version);
      if (BrowserVerify) {
        if (Pattern.matches("\\d+", version)) {
        Assert.assertTrue(userAgent.toLowerCase().contains((browser + "/" + version).toLowerCase()));
        } else {
          try {
              String jsonContent = new String(Files.readAllBytes(Paths.get("browser_version.json")));
              JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonContent);
              JSONObject browserObject = (JSONObject) jsonObject.get(jsonBrowserKey);          
              if (browserObject == null || !browserObject.containsKey(version.toLowerCase())) {
                  throw new RuntimeException("Version key '" + version + "' not found for browser '" + browser + "' in JSON.");
              }
              String numericVersion = (String) browserObject.get(version.toLowerCase());
              System.out.println("Comparing userAgent with JSON version: " + browser + "/" + numericVersion);
              Assert.assertTrue(userAgent.toLowerCase().contains((browser + "/" + numericVersion).toLowerCase()));     
          } catch (Exception e) {
              e.printStackTrace();
              throw new RuntimeException("Failed to retrieve numeric version from JSON for browser comparison.");
          }
        }      
      }

    }
    if(System.getProperty("failedfast")!= null){
      Assert.assertTrue(false, "Intentionally Failed Fast");
    }
    TimeUnit.SECONDS.sleep(60);
  }

  @Then("^open url from context json$")
  public void open_url() throws Exception {
    Object obj = new JSONParser().parse(new FileReader("data_context.json"));
    JSONObject jo = (JSONObject) obj;

    String url = (String) jo.get("url");
    System.out.println(url);
    driver.get(url);
  }

  @When("^select First Item$")
  public void select_first_item() throws InterruptedException {
    TimeUnit.SECONDS.sleep(3);
    driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
    TimeUnit.SECONDS.sleep(3);
    driver.findElement(By.id("isAgeSelected")).click();
  }

  @Then("^select second item$")
  public void select_second_item() {
    driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
  }

  @Then("^add new item$")
  public void add_new_item() {
    if (isgeolocation ) {
      try {
        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
      } catch (NoSuchElementException e) {
        System.out.println("Cookie popup is not visible, skipping click.");
      }
    driver.findElement(By.id("ex1-check1")).click();
    driver.findElement(By.id("ex1-check2")).click();
    driver.findElement(By.id("ex1-check3")).click();
    String title = driver.getTitle();
    if (!title.equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On Cloud")) {
      Assert.assertTrue(false, "Incorrect Title");
    }
    status = "passed";
  }
}

  @Then("^verify added item$")
  public void verify_added_item() {
    String item = driver.findElement(By.id("m-documentation")).getText();
    Assert.assertTrue(item.contains("Documentation"));
    status = "passed";
  }

  @Then("^Update the result$")
  public void update_result() {
    // ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
  }

  @Then("^I open google$")
  public void I_open_google() {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get("https://www.google.com");
  }

  @When("^I enter \"([^\"]*)\" in search textbox$")
  public void I_enter_in_search_textbox(String additionTerms) throws InterruptedException {
    //Write term in google textbox
    TimeUnit.SECONDS.sleep(5);
    WebElement googleTextBox = driver.findElement(By.name("q"));
    TimeUnit.SECONDS.sleep(5);
    googleTextBox.sendKeys(additionTerms);
    TimeUnit.SECONDS.sleep(5);
    WebElement searchButton = driver.findElement(By.name("btnK"));
    TimeUnit.SECONDS.sleep(5);
    searchButton.click();
    TimeUnit.SECONDS.sleep(5);
  }

  @Then("^I should get result as \"([^\"]*)\"$")
  public void I_should_get_correct_result(String expectedResult) throws InterruptedException {
    WebElement calculatorTextBox = driver.findElement(By.id("cwos"));
    TimeUnit.SECONDS.sleep(5);
    String result = calculatorTextBox.getText();
    Assert.assertEquals(result, expectedResult);
  }

  @Then("^I should get internet speed and sample todo check$")
  public void I_should_get_Internet_speed() {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get("https://www.fast.com");
    WebDriverWait wait = new WebDriverWait(driver, 60);
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".oc-icon-refresh")));
    for (int i = 0; i < 10; i++) {
      driver.get("https://lambdatest.github.io/sample-todo-app/");
      driver.findElement(By.name("li1")).click();
      driver.findElement(By.name("li2")).click();
      driver.findElement(By.id("sampletodotext")).clear();
      driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
      driver.findElement(By.id("addbutton")).click();
      String item = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
      Assert.assertTrue(item.contains("Yey, Let's add it to list"));
      if (perform == "Yes") {
        ((JavascriptExecutor) driver).executeScript("lambda-start-timer=" + i);
        Integer item_count = 5;
        for (int count = 1; count <= item_count; count++) {
          /* Enter the text box for entering the new item */
          driver.findElement(By.id("sampletodotext")).sendKeys("performance label");
          driver.findElement(By.id("addbutton")).click();
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        Object t1 = ((JavascriptExecutor) driver).executeScript("lambda-end-timer=" + i);
        System.out.println("timer " + t1);
      }
    }
  }

  @When("^Open local browser and verify tunnel$")
  public void verify_tunnel() {
    driver.get("http://127.0.0.1:8000/");
    Assert.assertEquals(driver.findElement(By.xpath("//body")).getText(), "Hello World");
    driver.get("https://ipinfo.io/");
    System.out.println("SystemProxy:- " + driver.findElement(By.xpath("(//*[@id='ip-string']/div/span/span[2]/span)[1]")).getText());
  }
  @When("^Open url and verify dedicated proxy$")
  public void verify_dedicatedproxy() {
  if (exp_dedicatedproxy != null && isdedicated) {
      driver.get("https://ipinfo.io/");
      String dedicatedproxy = driver.findElement(By.xpath("(//*[@id='ip-string']/div/span/span[2]/span)[1]")).getText();
      System.out.println("DedicatedProxy:- " + dedicatedproxy);
    }
  }

  @Then("^Open local browser and verify geolocation for country (.*?) and country code (.*?)$")
  public void verify_geolocation(String country, String countrycode) throws ParseException {
    /*
    Disabling temporarily
    driver.get("http://ip-api.com/json");
    String jsoncountry = driver.findElement(By.xpath("//pre")).getText();
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(jsoncountry);
    Assert.assertEquals(json.get("countryCode"), countrycode);
    Assert.assertEquals(json.get("country"), country);

     */
    driver.get("https://ipinfo.io/");
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(
      ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[text()='country']/following::span)[2]"))));
    String geoCode = driver.findElement(By.xpath("(//span[text()='country']/following::span)[2]")).getText();
    System.out.println("Country code ====> " + geoCode);
    Assert.assertEquals(geoCode, countrycode);
  }

  @Then("^Open local browser and verify ip (.*?) for unboundregion$")
  public void verify_unboundregion(String ip) throws InterruptedException {
    driver.get("https://mcastest14-my.sharepoint.com");
    TimeUnit.SECONDS.sleep(2);
    driver.findElement(By.xpath("//input[@type='email']")).sendKeys("lambdatest@mcas-test14.com");
    TimeUnit.SECONDS.sleep(2);
    driver.findElement(By.xpath("//input[@type='submit']")).click();
    TimeUnit.SECONDS.sleep(2);
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("hr0tp1hdsm_S");
    TimeUnit.SECONDS.sleep(2);
    driver.findElement(By.xpath("//input[@type='submit']")).click();
    TimeUnit.SECONDS.sleep(2);
    driver.findElement(By.xpath("//input[@type='submit']")).click();
    TimeUnit.SECONDS.sleep(2);
    // driver.findElement(By.xpath("//input[@type='submit']")).click();
    TimeUnit.SECONDS.sleep(10);
    driver.get("https://mcas-presence.mcas.ms/mcasapi/cas-about-version-info");
    TimeUnit.SECONDS.sleep(2);
    Object niptuck_ip = (((JavascriptExecutor) driver).executeScript("lambda-unbound-ping=x.mcas.ms"));
    System.out.println("ip: " + niptuck_ip);
    Assert.assertTrue(niptuck_ip.toString().contains(ip));
  }

  @Then("^Close the browser$")
  public void close_the_browser() {
    // ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
    driver.quit();
  }
}
