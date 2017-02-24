package com.qait.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest2 {
	@Test
	public void testMethodOne(){
		System.out.println("Executing 'testMethodOne'");
		Assert.assertTrue(true);
	}
	
	@Test
	public void testMethodTwo(){
		System.out.println("Executing 'testMethodTwo'");
		Assert.assertTrue(true);
	}

	@Test(dependsOnMethods={"testMethodTwo"})
	public void testMethodThree(){
		System.out.println("Executing 'testMethodThree'");
		Assert.assertTrue(false);
	}

	@Test
	public void testMethodFour(){
		System.out.println("Executing 'testMethodFour'");
		Assert.assertTrue(true);
	}
}