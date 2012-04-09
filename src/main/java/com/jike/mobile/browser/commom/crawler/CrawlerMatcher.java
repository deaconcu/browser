package com.jike.mobile.browser.commom.crawler;

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
	
	Logger log = LoggerFactory.getLogger(CrawlerMatcher.class);
	
	String url;
	String[] regexs;
	String[] result;

	public void setUrl(String url) throws crawlerException{
		if(url == null || !url.matches("http://.*")) throw new crawlerException("url.is.not.correct");
		this.url = url;
	}

	public void setRegexs(String[] regexs) {
		this.regexs = regexs;
		result = new String[regexs.length];
	}
	
	public String[] getResult() {
		return result;
	}

	public void execute() throws crawlerException {
		String html = getHtml();
		try {
			for(int i = 0; i < regexs.length; i++) {
				Pattern pattern = Pattern.compile(regexs[i], Pattern.DOTALL);
				
				Matcher m = pattern.matcher(html); 
				String r = null;
				if(m.find()) {
					r = m.group(1);
				}
				result[i] = r;
				log.info("regex: " + regexs[i] + " | result: " + r);
			}
		} catch (RuntimeException re) {
			
		}
	}
		
	private String getHtml() throws crawlerException{
		HttpClient httpclient = new DefaultHttpClient();	
		HttpGet httpget = new HttpGet(url);
		try {
			httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
			httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
			httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
			
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
			if(entity == null) throw new crawlerException("can.not.get.HTML");

	    	byte[] bytes = EntityUtils.toByteArray(entity);
	    	String charSet = "";
	    	charSet = EntityUtils.getContentCharSet(entity);
		    return new String(bytes, charSet);
		    
		} catch (Exception e) {
			throw new crawlerException("connection.failed");
		}
	}
	
	public static void main(String... args) {
		CrawlerMatcher cm = new CrawlerMatcher();
		try {
			cm.setUrl("http://news.163.com/");
			String[] regexs = {"<h2><a href=\"http://news.163.com/photoview/00AO0001/22297.html\">(.*?)</a></h2>"};
			
			
			cm.setRegexs(regexs);
			cm.execute();
			System.out.println(Arrays.toString(cm.getResult()));
			
			
		} catch (crawlerException e) {
			System.out.println(e.getMessage());
		}
	}
}






















