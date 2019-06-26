package com.example.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.ResourceBundle;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.Test;

import com.example.dto.Item;

public class ItemDaoTest extends DBTestCase{

	public ItemDaoTest(String name){
		super(name);
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.example.utils.db");
		String driver = bundle.getString("DRIVER");
		String url = bundle.getString("URL");
		String user = bundle.getString("USER");
		String password = bundle.getString("PASSWORD");

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, user);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, password);
	}

	@Override
	protected void setUpDatabaseConfig(DatabaseConfig config) {
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XmlDataSet(ItemDaoTest.class.getResourceAsStream("testData.xml"));
	}

	@Test
	public void test_findAll() {
		ItemDao itemDao = new ItemDao();
		List<Item> items = itemDao.findAll();
		assertThat(items.size(), is(9));
	}

	@Test
	public void test_findByName() {
		ItemDao itemDao = new ItemDao();
		List<Item> items = itemDao.findByItemName("Boots");
		assertThat(items.size(), is(3));
	}
}
