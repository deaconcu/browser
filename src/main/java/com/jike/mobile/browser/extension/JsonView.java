package com.jike.mobile.browser.extension;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.jike.mobile.browser.model.Item;

public class JsonView{
	
	int code = 200;
	
	List<ItemView> itemViewList;
	
	public JsonView(List<Item> itemList) {
		itemViewList = new ArrayList<ItemView>();
		
		for(Item item : itemList) {
			itemViewList.add(new ItemView(item));
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@JSON(name="DLC")
	public List<ItemView> getItemViewList() {
		return itemViewList;
	}

	public void setItemViewList(List<ItemView> itemViewList) {
		this.itemViewList = itemViewList;
	}
	
	
}
