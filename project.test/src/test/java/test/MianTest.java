package test;

import org.junit.Test;

import util.StringUtil;

public class MianTest {
	
	@Test
	public void test1(){
		System.out.println(!StringUtil.isNullOrBlank(null));
	}
}
