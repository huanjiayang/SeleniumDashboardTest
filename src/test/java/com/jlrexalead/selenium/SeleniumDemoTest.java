package com.jlrexalead.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDemoTest {

	private WebDriver driver;
	
	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
	}

	@Before
	public void setUp() {
		this.driver = new FirefoxDriver();
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}

	@Test
	public void testGoogleTitleOk() {
		this.driver.get("http://www.google.com");
        assertEquals("Google", this.driver.getTitle());
	}
	
	@Test
	public void testGoogleTitleNotOk() {
		this.driver.get("http://www.google.com");
        assertEquals("Googlebla", this.driver.getTitle());
	}
}
