package com.jike.mobile.browser.common.crawler;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerMatcher {
	
	private Logger log = LoggerFactory.getLogger(CrawlerMatcher.class);
	
	private String url;
	private String charSet;
	private String[] regexs;
	private String[] result;

	public void setUrl(String url) throws crawlerException{
		if(url == null || !url.matches("http://.*")) throw new crawlerException("url.is.not.correct");
		this.url = url;
	}

	public void setRegexs(String[] regexs) {
		this.regexs = regexs;
		result = new String[regexs.length];
	}
	
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	
	public String[] getResult() {
		return result;
	}

	public void execute() throws crawlerException {
		String html = getHtml();
		for(int i = 0; i < regexs.length; i++) {
			try {
				Pattern pattern = Pattern.compile(regexs[i], Pattern.DOTALL);
				
				Matcher m = pattern.matcher(html); 
				String r = null;
				if(m.find()) {
					r = m.group(1);
				}
				result[i] = r;
				log.info("regex: " + regexs[i] + " | result: " + r);
			} catch (RuntimeException re) {
				
			}
		}
	}
		
	private String getHtml() throws crawlerException{
		HttpClient httpclient = new DefaultHttpClient();	
		HttpGet httpget = new HttpGet(url);
		try {
			httpget.setHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
			
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
			if(entity == null) throw new crawlerException("can.not.get.HTML");

	    	byte[] bytes = EntityUtils.toByteArray(entity);
	    	String charSetAuto = EntityUtils.getContentCharSet(entity);
	    	if(charSet == null || charSet.equals("")) {
	    		charSet = charSetAuto;
	    	}
	    	System.out.println("aaa");
		    return new String(bytes, charSet);
		    
		} catch (Exception e) {
			log.info(e.toString());
			throw new crawlerException("connection.failed");
		}
	}
	
	public static void main(String... args) {
		CrawlerMatcher cm = new CrawlerMatcher();
		try {
			cm.setUrl("http://sports.sina.com.cn/");
			String[] regexs = {"<dd>(.*?)</dd>"};
			
			
			cm.setRegexs(regexs);
			cm.setCharSet("gbk");
			cm.execute();
			System.out.println(Arrays.toString(cm.getResult()));
			
			
		} catch (crawlerException e) {
			System.out.println(e.getMessage());
		}
	}
}






















