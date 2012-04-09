package com.jike.mobile.browser.util;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;

public class Validate {
	
	public static int page(Integer page) {
		if(page == null || page <= 0) {
			return 1;
		} else 
			return page;
	}
	
	public static boolean id(Integer id, Message msg) {
		if(id == null) {
			msg.setValue("input.id.is.empty");
			return false;
		} else if(id <= 0) {
			msg.setValue("input.id.is.invalid");
			return false;
		}
		return true;
	}

	public static boolean appboxCategoryAll(AppboxCategory appboxCategory, Message msg) {
		if(isNull(appboxCategory, msg)) {
			return false;
		} else {
			if(appboxCategory.getId() == null || appboxCategory.getId() <= 0) {
				msg.setValue("appbox.input.category.id.is.invalid");
				return false;
			}
			if(appboxCategory.getName() == null || appboxCategory.getName().trim().equals("")) {
				msg.setValue("appbox.input.category.name.is.empty");
				return false;
			}
			return true;
		}
	}
	
	public static boolean appboxCategoryWithoutId(AppboxCategory appboxCategory, Message msg) {
		if(isNull(appboxCategory, msg)) {
			return false;
		} else {
			if(appboxCategory.getName() == null || appboxCategory.getName().trim().equals("")) {
				msg.setValue("appbox.input.category.name.is.empty");
				return false;
			}
			return true;
		}
	}
	
	public static boolean appboxItemAll(AppboxItem appboxItem, Message msg) {
		if(isNull(appboxItem, msg)) {
			return false;
		} else {
			if(!IntegerPositive(appboxItem.getId(), "appbox.item", "id", msg)) return false;
			if(!StringLength(appboxItem.getName(), 0, 0, "appbox.item", "name", msg)) return false;
			if(!StringLength(appboxItem.getSource(), 0, 0, "appbox.item", "source", msg)) return false;
			if(!StringLength(appboxItem.getTitleRegex(), 0, 0, "appbox.item", "titelRegex", msg)) return false;
			if(!StringLength(appboxItem.getUrlRegex(), 0, 0, "appbox.item", "urlRegex", msg)) return false;
			if(!StringLength(appboxItem.getPicRegex(), 0, 0, "appbox.item", "picRegex", msg)) return false;
			if(isNull(appboxItem.getAppboxCategory(), msg)) return false;
			return true;
		}
	}
	
	public static boolean appboxItemWithoutId(AppboxItem appboxItem, Message msg) {
		if(isNull(appboxItem, msg)) {
			return false;
		} else {
			if(!StringLength(appboxItem.getName(), 0, 0, "appbox.item", "name", msg)) return false;
			if(!StringLength(appboxItem.getSource(), 0, 0, "appbox.item", "source", msg)) return false;
			if(!StringLength(appboxItem.getTitleRegex(), 0, 0, "appbox.item", "titelRegex", msg)) return false;
			if(!StringLength(appboxItem.getUrlRegex(), 0, 0, "appbox.item", "urlRegex", msg)) return false;
			if(!StringLength(appboxItem.getPicRegex(), 0, 0, "appbox.item", "picRegex", msg)) return false;
			if(isNull(appboxItem.getAppboxCategory(), msg)) return false;
			return true;
		}
	}
	
	public static boolean isNull(Object o, Message msg) {
		if(o == null) {
			msg.setValue("input.object.is.null");
			return true;
		}
		return false;
	}
	
	private static boolean StringLength(String s, int min, int max, String msg_obj, String msg_field, Message msg) {
		if(s == null || s.trim().equals("")) {
			msg.setValue(msg_obj + "." + msg_field + ".is.empty");
			return false;
		}
		else if(min > 0 && s.length() < min) {
			msg.setValue(msg_obj + "." + msg_field + ".is.out.of.range");
			return false;
		}
		else if(max > 0 && s.length() > max) {
			msg.setValue(msg_obj + "." + msg_field + ".is.out.of.range");
			return false;
		}
		return true;
	}
	
	private static boolean IntegerPositive(Integer i, String msg_obj, String msg_field, Message msg) {
		if(i == null || i <= 0) {
			msg.setValue(msg_obj + "." + msg_field + ".is.invalid");
			return false;
		}
		return true;
	}
}




































