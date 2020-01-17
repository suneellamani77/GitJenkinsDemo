package com.sg.ngs;

import org.junit.Assert;
import org.junit.Test;

public class EhCacheTest {
	
	@Test
	public void addition() {
		int a = 7;
		int b = 8;
		int c = a + b;
		Assert.assertEquals("both are not equal", 15, c);
	}
	
	@Test
	public void substraction() {
		int a = 8;
		int b = 7;
		int c = a - b;
		Assert.assertEquals("The result is not correct", 1, c);
	}
	

}
