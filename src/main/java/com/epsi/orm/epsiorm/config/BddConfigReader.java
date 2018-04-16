package com.epsi.orm.epsiorm.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Michael
 */
public class BddConfigReader {

				public static BddConfig read(String fileName) throws IOException {
								Properties prop = new Properties();
								prop.load(new FileReader(fileName));
								BddConfig b = new BddConfig();
								b.setBase(prop.getProperty("base"));
								b.setHost(prop.getProperty("host"));
								b.setLogin(prop.getProperty("login"));
								b.setPwd(prop.getProperty("password"));
								return b;
				}

}
