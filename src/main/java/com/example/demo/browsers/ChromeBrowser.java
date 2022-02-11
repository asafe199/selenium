package com.example.demo.browsers;

import com.example.demo.Logs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class ChromeBrowser extends Logs {

    protected ChromeOptions options;
    protected WebDriver webDriver;

    static {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
    }

    public ChromeBrowser(){
        initData();
    }

    private void initData() {
        this.options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--headless");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        this.options.setCapability("loggingPrefs", logPrefs);
        this.webDriver = new ChromeDriver(options);
    }

}
