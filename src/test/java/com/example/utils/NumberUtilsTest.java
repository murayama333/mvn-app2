package com.example.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void test_format_by0() {
		int price = 0;
		String actual = NumberUtils.format(price);
		String expected = "0";
		assertEquals(expected, actual);
	}

	@Test
	public void test_format_by1000() {
		int price = 1000;
		String actual = NumberUtils.format(price);
		String expected = "1,000";
		assertEquals(expected, actual);
	}

	@Test
	public void test_format_by1000000() {
		int price = 1000000;
		String actual = NumberUtils.format(price);
		String expected = "1,000,000";
		assertEquals(expected, actual);
	}

	@Test
	public void test_format_byMinus1000000() {
		int price = -1000000;
		String actual = NumberUtils.format(price);
		String expected = "-1,000,000";
		assertEquals(expected, actual);
	}

}
