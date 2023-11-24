package com.utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

public class LoggerMethods {

	public static Logger logger = LogManager.getLogger(LoggerMethods.class.getName());
	public static String log4jFilePath = "./src/test/resources/log4j.xml";
	public static String reportPath = "Logs";;

	public static int steps = 1;

	public static void initializeLog() throws URISyntaxException {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		System.setProperty("logFileName", "LogFile_" + format.format(date));
		System.setProperty("logFilePath", LoggerMethods.reportPath);
		ConfigurationFactory.setConfigurationFactory(new XmlConfigurationFactory());
		Configurator.reconfigure(new URI(log4jFilePath));
		System.out.println("Log Initialized");
	}

	public static void logInfo(String className, String methodName, String infoMesg) throws URISyntaxException {
		logger.info("Step-" + steps + " [[" + className + ":" + methodName + "]]" + " - " + infoMesg);
		steps++;
	}
}