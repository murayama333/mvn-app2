package com.example.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.dao.ItemDao;
import com.example.dto.Item;

public class ItemLogicTest {

	@Test
	public void test_args_null() {
		ItemLogic itemLogic = new ItemLogic();
		List<Item> items = itemLogic.getItems(null);
		assertEquals(0, items.size());
	}

	@Test
	public void test_items1() {

		String itemName = "foo";
		
		ItemDao mockDao = mock(ItemDao.class);
		when(mockDao.findByItemName(anyString())).thenReturn(expectItems());
		
		ItemLogic itemLogic = new ItemLogic();
		itemLogic.setItemDao(mockDao);
		
		List<Item> actual = itemLogic.getItems(itemName);
		
		assertThat(actual.size(), is(expectItems().size()));
		verify(mockDao).findByItemName(itemName);
	}

	private List<Item> expectItems() {
		List<Item> items = new ArrayList<>();
		for(int i = 1; i < 10; i++){
			Item item = new Item();
			item.setId(i);
			item.setName("item" + i);
			item.setPrice(i * 100);
			item.setCategory((i % 3) + 1); // 1,2,3
			items.add(item);
		}
		return items;
	}
}
