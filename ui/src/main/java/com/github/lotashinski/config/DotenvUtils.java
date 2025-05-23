package com.github.lotashinski.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvUtils {
	
	private static Dotenv dt;
	
	public static Dotenv dotenv() {
		if (dt == null) {
			dt = configureDotenv();
		}
		
		return dt;
	}
	
	private static Dotenv configureDotenv() {
		return Dotenv
		   .configure()
		   .systemProperties()
		   .load();
	}
	
}
