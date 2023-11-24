package com.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Translator {

	static String filePath = "./python_library/lang_translator.py";

	public static String translate(String fromLang, String toLang, String text) {
		String pathPython = filePath;
		String[] cmd = new String[5];
		cmd[0] = "python";
		cmd[1] = pathPython;
		cmd[2] = fromLang;
		cmd[3] = toLang;
		cmd[4] = text;
		Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
			p = r.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String s;
		String strOutput = "";
		try {
			while ((s = in.readLine()) != null) {
				strOutput = s;
				System.out.println(s);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strOutput;
	}
}