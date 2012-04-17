package com.jike.mobile.browser.quickStart;

import java.util.List;

import com.jike.mobile.browser.model.QuickStartIcon;
import com.jike.mobile.browser.model.UploadFile;

public interface QuickStartService {
	public Integer iconAdd(QuickStartIcon quickStartIcon, UploadFile icon);

	public QuickStartIcon findIconById(Integer iconId);

	public void iconModify(QuickStartIcon quickStartIcon, UploadFile iconFile);
	
	public void delete(int iconId);
	
	public List<QuickStartIcon> getListDesc(Integer page, Integer length);

	public QuickStartIcon findIconByWebUrl(String webUrl);
}
