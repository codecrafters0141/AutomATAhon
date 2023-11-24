package com.config.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	static Properties configProp;
	static FileInputStream file;

	public static void initializeConfigFile() throws IOException {
		configProp = new Properties();
		file = new FileInputStream("./config.properties");
		configProp.load(file);
	}

	public static String getConfigPropery(String keyName) throws IOException {
		return configProp.getProperty(keyName);
	}

}
