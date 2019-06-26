package com.example.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;

import org.junit.Test;

public class ConnectionUtilsTest {

	@Test
	public void test_getConnection() {
		// TODO mock
		Connection actual = ConnectionUtil.getConnection();
		assertThat(actual, is(notNullValue()));
	}

}
