package com.jike.mobile.browser.quickStart;

import com.jike.mobile.browser.model.QuickStartIcon;
import com.jike.mobile.browser.model.UploadFile;

public interface QuickStartService {
	public Integer iconAdd(QuickStartIcon quickStartIcon, UploadFile icon);

	public QuickStartIcon findIconById(Integer iconId);

	public void iconModify(QuickStartIcon quickStartIcon, UploadFile iconFile);
}
