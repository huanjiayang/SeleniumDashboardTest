package com.jlrexalead.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JLRExaleadTest {
	private WebDriver driver;
	
	public void init(){
		this.driver = new FirefoxDriver();
	}
	
	public void close(){
		this.driver.quit();
	}
	
	public boolean loadDashboardWithLogin(String url, String username, String password){
		this.driver.get(url);
		if (this.driver.findElements(By.name("login")).size() != 0){
			System.out.println("Log in page encountered");
			System.out.println("Attempting to log in as exalead");
			this.driver.findElement(By.cssSelector("input[name=login]")).sendKeys(username);
			this.driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
			this.driver.findElement(By.cssSelector("input[name=connect]")).click();
			System.out.println("Logging in as exalead completed");
		}
		return true;
	}
	
	public String getPageTitle(){
		return this.driver.getTitle();
	}
	
	public void waitForElement(String id){
		WebDriverWait wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	public int getNumberFromSingleMetric(String id){
		WebElement jointCountDiv = this.driver.findElement(By.id("total_joint_count"));
		WebElement joinCountNumberContainer = jointCountDiv.findElement(By.cssSelector("div.main"));
		return Integer.parseInt(joinCountNumberContainer.getText());
	}
	
	public void selectOptionFromMultiSelect(String selectContainerId, int selectIndex, String matchingText){
		WebElement mandatoryRefinementDiv = this.driver.findElement(By.id(selectContainerId));
		WebElement mandatoryRefinementTable = mandatoryRefinementDiv.findElement(By.cssSelector("table"));
		Select modelSelect = new Select(mandatoryRefinementTable.findElements(By.cssSelector("tr")).get(selectIndex).findElement(By.cssSelector("select")));
		for(WebElement option : modelSelect.getOptions()){
			if(option.getText().contains(matchingText)){
				modelSelect.selectByVisibleText(option.getText());
				break;
			}
		}
	}
	
	public int getRefinementCategoryNumber(String refinementId){
		List<WebElement> categoryList = this.driver.findElement(By.id(refinementId+"_categories")).findElements(By.cssSelector("li"));
		return categoryList.size();
	}
}
