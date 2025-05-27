package com.github.lotashinski.ui.config;

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
		   .ignoreIfMissing()
		   .systemProperties()
		   .load();
	}
	
}
