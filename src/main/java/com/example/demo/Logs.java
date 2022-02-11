package com.example.demo;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class Logs {

    protected void logs(WebDriver webDriver) {
        List<LogEntry> entries = webDriver.manage().logs().get(LogType.BROWSER).getAll();
        for (LogEntry entry : entries) {
            System.out.println(new JSONObject(entry));
        }
    }
}
