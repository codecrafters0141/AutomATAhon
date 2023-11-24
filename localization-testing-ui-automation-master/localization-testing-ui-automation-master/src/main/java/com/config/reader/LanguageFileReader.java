package com.config.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.testng.Assert;

import com.utility.LoggerMethods;
import com.utility.UnicodeConverter;

public class LanguageFileReader {
	private Properties prop;
	private FileInputStream file;
	private String fileCSV;
	private BufferedReader br;
	private UnicodeConverter unicodeConverter;
	private String fileExtention;
	private boolean fileNameFound;
	private String resourcesPath = "src/main/resources/";
	private String language = "";

	public void setLangCodeProperty(String languageName) throws URISyntaxException, IOException {
		if (languageName.equalsIgnoreCase("default language")) {
			languageName = ConfigFileReader.getConfigPropery("defaultTestLanguage");
		}
		language = languageName;
		System.setProperty("language", languageName);

		String langCode = "";
		switch (language.toLowerCase()) {
		case "chinese":
			langCode = "zh-CN";
			break;
		case "hindi":
			langCode = "hi";
			break;
		case "arabic":
			langCode = "ar";
			break;
		case "english":
			langCode = "en";
			break;
		case "french":
			langCode = "fr";
			break;
		case "spanish":
			langCode = "es";
			break;
		default:
			LoggerMethods.logInfo("LanguageFileReader", "setLangCodeProperty",
					"Failure >>>>>>>>>>>>>>>>:" + "Invalid Language Name: " + language);
			Assert.fail("Failure >>>>>>>>>>>>>>>>:" + "Invalid Language Name: " + language);
			break;
		}
		System.setProperty("languageCode", langCode);
	}

	public void initLangFile() throws URISyntaxException {
		try {
			LoggerMethods.logInfo("LanguageFileReader", "initLangFile", "Language Name is : [" + language + "]");

			File folder = new File(resourcesPath);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if ((file.getName().split("\\.")[0]).equals(language.toLowerCase())) {
					fileNameFound = true;
					break;
				}
			}

			if (!fileNameFound) {
				LoggerMethods.logInfo("LanguageFileReader", "initLangFile",
						"Failure >>>>>>>>>>>>>>>>:" + "Invalid Language Name: " + language);
				Assert.fail("Failure >>>>>>>>>>>>>>>>:" + "Invalid Language Name: " + language);
			}

			fileExtention = FilenameUtils.getExtension(resourcesPath + listOfFiles[0].getName());
			if (fileExtention.equalsIgnoreCase("properties")) {
				prop = new Properties();
				file = new FileInputStream(resourcesPath + language.toLowerCase() + ".properties");
				//prop.load(file);
			} else {
				fileCSV = resourcesPath + language.toLowerCase() + ".csv";
				//br = new BufferedReader(new FileReader(fileCSV));
			}
		} catch (Exception e) {
			LoggerMethods.logInfo("LanguageFileReader", "initLangFile", "Failure >>>>>>>>>>>>>>>>:" + e.toString());
			Assert.fail("Failure >>>>>>>>>>>>>>>>:" + e.toString());
		}
		LoggerMethods.logInfo("LanguageFileReader", "initLangFile", "Language File is Initialized");
	}

	public String getKeyValueFromLangFile(String keyName) throws URISyntaxException, IOException {
		String keyValue = "";
		if (fileExtention.equalsIgnoreCase("properties")) {
			keyValue = getKeyValueinPropertiesFile(keyName);
		} else {
			keyValue = getKeyValueinCSVFile(keyName);
		}
		return keyValue;
	}

	public String getUnicodeKeyValueinLangFile(String keyName) throws URISyntaxException, IOException {
		unicodeConverter = new UnicodeConverter();
		String strValue =unicodeConverter.UnicodeToStringConverter(getKeyValueFromLangFile(keyName));
		if (strValue.contains("\\")) {
			strValue =unicodeConverter.UnicodeToStringConverter(strValue);
		}
		return strValue;
	}

	public String getKeyValueinPropertiesFile(String keyName) throws URISyntaxException, IOException {
		String keyValue = "";
		try {
			prop.load(file);
			keyValue = prop.getProperty(keyName);
		} catch (Exception e) {
			LoggerMethods.logInfo("LanguageFileReader", "getKeyValueinPropertiesFile",
					"Failure >>>>>>>>>>>>>>>>:" + e.toString());
			Assert.fail("Failure >>>>>>>>>>>>>>>>:" + e.toString());
		}
		return keyValue;

	}

	public String getKeyValueinCSVFile(String keyName) throws URISyntaxException, IOException {
		String line = "";
		String keyValue = "";
		boolean keyFound = false;
		try {
			br = new BufferedReader(new FileReader(fileCSV));
			while ((line = br.readLine()) != null) {
				if (line.contains(keyName)) {
					keyFound=true;
					keyValue = line.split(",",2)[1];
					return keyValue;
				}
			}
			if(!keyFound) {
				LoggerMethods.logInfo("LanguageFileReader", "getKeyValueinCSVFile",
						"Failure >>>>>>>>>>>>>>>>:" + "Key[" + keyName+ "] not found");
				Assert.fail("Failure >>>>>>>>>>>>>>>>:" + "Key[" + keyName+ "] not found");
			}
		} catch (Exception e) {
			LoggerMethods.logInfo("LanguageFileReader", "getKeyValueinPropertiesFile",
					"Failure >>>>>>>>>>>>>>>>:" + e.toString());
			Assert.fail("Failure >>>>>>>>>>>>>>>>:" + e.toString());
		}
		return keyValue;
	}

}

//	
//	
//	
//	public void initLangProperty(String language) throws IOException, URISyntaxException {
//		prop = new Properties();
//		switch (language.toUpperCase()) {
//		case "ENGLISH":
//			file = new FileInputStream("src/main/resources/english.properties");
//			break;
//		case "FRENCH":
//			file = new FileInputStream("src/main/resources/french.properties");
//			break;
//		case "CHINESE":
//			file = new FileInputStream("src/main/resources/chinese.properties");
//			break;
//		case "RUSSIAN":
//			file = new FileInputStream("src/main/resources/russian.properties");
//			break;
//		case "ARABIC":
//			file = new FileInputStream("src/main/resources/arabic.properties");
//			break;
//		case "SPANISH":
//			file = new FileInputStream("src/main/resources/spanish.properties");
//			break;
//		case "HINDI":
//			file = new FileInputStream("src/main/resources/hindi.properties");
//			break;
//		default:
//			LoggerMethods.logInfo("LanguageFileReader", "initLangProperty", "Invalid Language Name");
//			Assert.fail("Failure >>>>>>>>>>>>>>>>:" + "Invalid Language Name");
//		}
//		prop.load(file);
//	}
//	
//	public BufferedReader initLangPropertyInCSV(String language) throws URISyntaxException, FileNotFoundException {
//		LoggerMethods.logInfo("LanguageFileReader", "initLangProperty", "Language Name is : [" + language + "]");
//		switch (language.toUpperCase()) {
//		case "ENGLISH":
//			fileCSV = "src/main/resources/english.csv";
//			break;
//		case "FRENCH":
//			fileCSV = "src/main/resources/french.csv";
//			break;
//		case "CHINESE":
//			fileCSV = "src/main/resources/chinese.csv";
//			break;
//		case "RUSSIAN":
//			fileCSV = "src/main/resources/russian.csv";
//			break;
//		case "ARABIC":
//			fileCSV = "src/main/resources/arabic.csv";
//			break;
//		case "SPANISH":
//			fileCSV = "src/main/resources/spanish.csv";
//			break;
//		case "HINDI":
//			fileCSV = "src/main/resources/hindi.csv";
//			break;
//		default:
//			LoggerMethods.logInfo("LanguageFileReader", "initLangProperty", "Invalid Language Name");
//			Assert.fail("Failure >>>>>>>>>>>>>>>>:" + "Invalid Language Name");
//		}
//		br = new BufferedReader(new FileReader(fileCSV));  
//		return br;
//	}
