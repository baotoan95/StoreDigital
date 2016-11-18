package com.baotoan.spring.utils;

import java.util.Date;
import java.util.Random;

public class GenerateCode {
	public static String generate(int size) {
		String result = "";
		while(result.length() < size+1) {
			int rand = new Random().nextInt(123);
			if(rand >= 48 && rand <= 57 || rand >= 65 && rand <= 90 || rand >= 97 && rand <= 122) {
				result += (char)rand;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(generate(20));
	}
	
	public static String generateFileName() {
		return new Date().toString().replaceAll(" ", "").replaceAll(":", "").concat(generate(5));
	}
}
