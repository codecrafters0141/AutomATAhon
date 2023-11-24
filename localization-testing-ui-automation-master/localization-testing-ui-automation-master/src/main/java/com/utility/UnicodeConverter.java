package com.utility;

import java.net.URISyntaxException;

import org.apache.commons.text.StringEscapeUtils;
import org.testng.Assert;

public class UnicodeConverter {

	public String UnicodeToStringConverter(String strUnicode) throws URISyntaxException {
		String convertedString = "";
		try {
			convertedString = StringEscapeUtils.unescapeJava(strUnicode);
			LoggerMethods.logInfo("UnicodeConverter", "UnicodeToStringConverter",
					"Unicode [" + strUnicode + "] Converted to String [" + convertedString + "]");
		} catch (Exception e) {
			LoggerMethods.logInfo("UnicodeConverter", "UnicodeToStringConverter", e.toString() + " " + e.getMessage());
			Assert.fail("Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
		}
		return convertedString;
	}

	public String StringToUnicodeConverter(String lang, String str) throws URISyntaxException {
		String charUnicode;
		StringBuilder sb = new StringBuilder();
		try {
			for (char c : str.toCharArray()) {
				String hexString = Integer.toHexString((int) c);
				switch (lang.toLowerCase()) {
				case "chinese":
					charUnicode = "\\u";
					break;
				case "hindi":
				case "arabic":
				case "russian":
					charUnicode = "\\u0";
					if (hexString.length() == 2) {
						charUnicode = "\\u00";
					}
					break;
				default:
					charUnicode = "\\u00";
					break;
				}
				sb.append(charUnicode).append(Integer.toHexString((int) c));
			}
			LoggerMethods.logInfo("UnicodeConverter", "StringToUnicodeConverter",
					"String [" + str + "] Converted to Unicode [" + sb.toString() + "]");
		} catch (Exception e) {
			LoggerMethods.logInfo("UnicodeConverter", "StringToUnicodeConverter", e.toString() + " " + e.getMessage());
			Assert.fail("Failure >>>>>>>>>:" + e.toString() + " " + e.getMessage());
		}
		return sb.toString();
	}

}
