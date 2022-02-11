package com.example.demo;

import com.example.demo.browsers.ChromeBrowser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoApplication extends ChromeBrowser {

    public static void main(String[] args) {
        new DemoApplication().start();
    }

    private void start(){
        try {
            webDriver.get("https://banking.firsttechfed.com/authentication");
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));
            WebElement name = webDriver.findElement(By.id("username"));
            name.sendKeys("data");
            WebElement password = webDriver.findElement(By.id("password"));
            password.sendKeys("bjkdsbdshb");
            WebElement buttom = webDriver.findElement(By.id("btn_submitCredentials"));
            buttom.submit();
            new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("dashboard_v2_navigation_item")));
            Document home = Jsoup.parse(webDriver.getPageSource());
            Elements uls = home.select("div#module_accounts > ul");
            for (Element el : uls) {
                if (el.id().equals("legend_list") || el.select("div").outerHtml().contains("PRIMARY SHARE")) {
                    continue;
                }
                String accountType = el.select("li > a > h4").first().text().trim().toLowerCase();
                String accountId = el.select("li:nth-child(2)").first().id().substring(8);
                String amount = el.select("li > a > div").first().text();
                String nameAccount = el.select("div > div:nth-child(1) > h4").first().text();
                String number = el.select("div > div:nth-child(2) > span > span[aria-label*=Account ending] ").first().text();
                System.out.printf("DADOS CONTA : id %s, SALDO : %s, NOME : %s, NUMERO : %s, Tipo Conta : %s", accountId, amount, nameAccount, number, accountType);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            logs(webDriver);
        }
    }
}
