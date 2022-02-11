package com.example.demo.browsers;

import com.example.demo.Logs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class FirefoxBrowser extends Logs {

    protected FirefoxOptions options;
    protected WebDriver webDriver;

    static {
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver");
    }

    public FirefoxBrowser(){
        initData();
    }

    private void initData(){
        this.options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--headless");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        this.options.setCapability("loggingPrefs", logPrefs);
        this.webDriver = new FirefoxDriver(options);
    }
}
