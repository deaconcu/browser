package com.jike.mobile.browser.appbox;

import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.browser.model.AppboxItem;

public class Refresher extends TimerTask{
	
	Logger log = LoggerFactory.getLogger(Refresher.class);
	
	AppboxService appboxService;
	
	public AppboxService getAppboxService() {
		return appboxService;
	}

	public void setAppboxService(AppboxService appboxService) {
		this.appboxService = appboxService;
	}

	@Override
	public void run() {
		try {
			List<AppboxItem> appboxItems = appboxService.findItemAll();
			for(AppboxItem appboxItem : appboxItems) {
				try {
					appboxService.match(appboxItem);
				} catch (Exception e) {
					log.error(appboxItem.getId() + " match failed");
				}
			}
		} catch(Exception e) {
			log.error("find all appbox item failed");
		}
	}
}
