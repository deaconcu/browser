package com.jike.mobile.browser.model;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class UploadTest {
	@Test
	public void testCalender() throws IOException {
		File file = new File("d://testlog.log");
		UploadFile uploadFile = new UploadFile(file, "", "1.txt");
		uploadFile.upload("aa", "2");
	}
}
