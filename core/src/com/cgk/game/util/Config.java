package com.cgk.game.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ianlukens May 17, 2015
 *
 */
public class Config {

	private String fileName;
	private File configFile;
	private Map<String, String> values;
	private boolean loaded;

	public Config(String fileName) {
		this.fileName = fileName;
		configFile = new File(fileName);
		values = new HashMap<String, String>();
		loaded = loadConfig();
	}

	public boolean loaded() {
		return loaded;
	}

	public boolean loadConfig() {
		if (exists()) {
			try {
				loadFromFile();
			} catch (Exception e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	private void loadFromFile() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(configFile)));
		String line = "";
		while ((line = reader.readLine()) != null) {
			getKeyValueFromLine(line);
		}
		reader.close();
	}

	private void getKeyValueFromLine(String line) {
		int keyValueSeperatorIndex = line.indexOf(":");
		String key = line.substring(0, keyValueSeperatorIndex);
		String value = line
				.substring(keyValueSeperatorIndex + 1, line.length());
		values.put(key, value);
	}

	public boolean exists() {
		return configFile.exists();
	}

	public String getValue(String key) throws Exception {
		return values.get(key);
	}

	public String addKeyValue(String key, String value) {
		return values.put(key, value);
	}

	public void saveFile() throws IOException {
		if (!exists()) {
			configFile.createNewFile();
		}
		String configData = "";
		for (String key : values.keySet()) {
			configData += (key + ":" + values.get(key) + "\n");
		}
		PrintWriter out = new PrintWriter(fileName);
		out.print(configData);
		out.close();
	}
}
