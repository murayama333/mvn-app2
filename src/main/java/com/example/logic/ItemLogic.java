package com.example.logic;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.ItemDao;
import com.example.dto.Item;
import com.example.utils.NumberUtils;


public class ItemLogic {
	
	private ItemDao itemDao;
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public List<Item> getItems(String itemName){
		
		if(itemName == null){
			return new ArrayList<Item>();
		}
		List<Item> itemList = itemDao.findByItemName(itemName);
		for (Item item : itemList) {
			String formattedPrice = NumberUtils.format(item.getPrice());
			item.setFormattedPrice(formattedPrice);
		}
		return itemList;
	}
}
