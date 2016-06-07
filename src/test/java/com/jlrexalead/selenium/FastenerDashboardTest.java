package com.jlrexalead.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FastenerDashboardTest {
	
	private JLRExaleadTest jlrExaleadTest = new JLRExaleadTest();
	
	private String url = "http://sul74142.solihull.jlrint.com:10000/ion/page/joining_matrix";
	private String username = "exalead";
	private String password = "Dashboard99";
	
	private String pageTitle = "Joining Matrix";
	
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
		jlrExaleadTest.init();
		jlrExaleadTest.loadDashboardWithLogin(this.url, this.username, this.password);
	}

	@After
	public void tearDown() {
		jlrExaleadTest.close();
	}

	@Test
	public void testDashboardPageTitleOk() {
		//this.driver.get("http://sul74142.solihull.jlrint.com:15000/ion/page/vehicle_certification");
		System.out.println("Testing Fastener dashbaord page title, expected: Joining Matrix");
		
		String actualPageTitle = jlrExaleadTest.getPageTitle();
		
        assertEquals(this.pageTitle, actualPageTitle);
        
	}
	
	@Test
	public void testDashboardStatusNumbers(){

		System.out.println("Selecting L462 from Model select refinement");
		
		//Select the L462 Model from Model list
		jlrExaleadTest.selectOptionFromMultiSelect("mandatory_refinements", 2, "L462");

		// check if the numbers on the dashboard meet minimum requirement
		System.out.println("Testing the Joint Count number on dashboard, criteria: minimum 1400");
		jlrExaleadTest.waitForElement("total_joint_count");
		int joinCount = jlrExaleadTest.getNumberFromSingleMetric("total_joint_count");
		assertTrue(joinCount>=1400);
		
		System.out.println("Testing the SPR Joint Count number on dashboard, criteria: minimum 1300");
		jlrExaleadTest.waitForElement("spr_joint_count");
		int sprJoinCount = jlrExaleadTest.getNumberFromSingleMetric("spr_joint_count");
		assertTrue(sprJoinCount>=1300);
		
		System.out.println("Testing the RSW Joint Count number on dashboard, criteria: minimum 100");
		jlrExaleadTest.waitForElement("total_joint_count");
		int rswJoinCount = jlrExaleadTest.getNumberFromSingleMetric("total_joint_count");
		assertTrue(rswJoinCount>=100);
	}
	
	@Test
	public void testToolRefinement(){

		System.out.println("testing tool refinement has category");
		
		//Select the L462 Model from Model list
		jlrExaleadTest.selectOptionFromMultiSelect("mandatory_refinements", 2, "L462");
		int numCat = jlrExaleadTest.getRefinementCategoryNumber("tool_name");
		System.out.println(numCat+" categories found for refinement tool_name");
		assertTrue(numCat>0);
	}
	
}
