package com.cgk.game.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author ianlukens May 20, 2015
 *
 */
public abstract class Constants {

	protected static <C extends Constants> void saveConfig(
			Class<C> consantsClass, Config config)
			throws IllegalArgumentException, IllegalAccessException,
			IOException {
		for (Field field : consantsClass.getDeclaredFields()) {
			if (!Modifier.isFinal(field.getModifiers())) {
				config.addKeyValue(field.getName(),
						"" + field.get(consantsClass));
			}
		}
		config.saveFile();

	}

	protected static <C extends Constants> void loadFromConfig(
			Class<C> constantsClass, Config config)
			throws IllegalArgumentException, IllegalAccessException, Exception {
		Field[] fields = constantsClass.getFields();
		for (Field field : fields) {
			String value = config.getValue(field.getName());
			if (value != null && !Modifier.isFinal(field.getModifiers())) {
				if (field.getType().toString().equals("float")) {
					Float floater = Float.parseFloat(value);
					field.set(constantsClass, floater);
					System.out.println("Set field " + field.getName() + " to "
							+ value);
				} else if (field.getType().toString().equals("int")) {
					field.set(constantsClass, Integer.parseInt(value));
					System.out.println("Set field " + field.getName() + " to "
							+ value);
				} else {
					System.out.println("Could not load value for "
							+ field.getName());
				}
			} else {
				System.out.println("No value found for " + field.getName()
						+ " or is final");
			}

		}
	}
}
