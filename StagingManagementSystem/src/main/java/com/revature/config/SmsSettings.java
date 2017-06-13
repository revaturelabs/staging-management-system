package com.revature.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class SmsSettings extends HashMap<String,String>{

	private static final long serialVersionUID = 8452345186136105388L;
	
	private static SmsSettings settings;

	private SmsSettings() {
		super();
	}

	public SmsSettings(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public SmsSettings(int initialCapacity) {
		super(initialCapacity);
	}

	public SmsSettings(Map<? extends String, ? extends String> m) {
		super(m);
	}

	public static SmsSettings getInstance(){
		if(settings == null){
			load();
		}
		return settings;
	}
	
	public static void reload(){
		load();
	}
	
	private static void load(){
		settings = new SmsSettings();
		Properties prop = new Properties();
		try {
			prop.load(SmsSettings.class.getResourceAsStream("/sms.properties"));
			for(String key : prop.stringPropertyNames()){
				settings.put(key, prop.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
