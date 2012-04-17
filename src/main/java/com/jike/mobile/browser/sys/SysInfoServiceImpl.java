package com.jike.mobile.browser.sys;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import com.jike.mobile.browser.dao.SysInfoDao;
import com.jike.mobile.browser.model.SysInfo;
import com.jike.mobile.browser.util.ServiceException;

public class SysInfoServiceImpl implements SysInfoService, ServletContextAware{

	private Logger log = LoggerFactory.getLogger(SysInfoServiceImpl.class);
	private SysInfoDao sysInfoDao;
	
	private ServletContext servletContext;
	private Properties properties;
	
	public SysInfoServiceImpl() {
		
	}

	@Override
	public String get(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void set(String key, String value) {
		set(key, value, false);
	}

	@Override
	public void set(String key, String value, boolean persistence) {
		properties.setProperty(key, value);
		if(persistence == true) {
			try {
				SysInfo sysInfo = sysInfoDao.findById(0);
				Class<SysInfo> sysInfoClass = SysInfo.class;
				
				key = key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
				
				Method setMethod = sysInfoClass.getMethod("set" + key, String.class);
				setMethod.invoke(sysInfo, value);

				sysInfoDao.update(sysInfo);			
			} catch(RuntimeException re) {
				log.error("Error setting property:" + key + ", database Exception");
				throw new ServiceException("Error setting property", re);
			} catch (Exception e) {
				log.error("Error setting property: " + key + ", reflection Exception: " + e.toString());
				throw new ServiceException("Error setting property", e);
			}
			
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initProperties();
		
	}
	
	private void initProperties() {
		try {
			InputStream in = servletContext.getResourceAsStream(servletContext.getInitParameter("serverConfigLocation")); 
			properties = new Properties();
			properties.load(in);

			properties.setProperty("real_root_path", servletContext.getRealPath("/"));
			
			String baseUrl = servletContext.getRealPath("/");
			
			properties.setProperty("base_url", baseUrl);
			properties.setProperty("serverConfigLocation", servletContext.getInitParameter("serverConfigLocation"));
			log.info("ServerConfig load Success");
		}
		catch (Exception e) {
			log.error("ServerConfig load Error");
			e.printStackTrace();
		}
	}

}








