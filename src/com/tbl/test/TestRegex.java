package com.tbl.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
		String str = "192.168.111.111";
		System.out.println(str
				.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));

		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaabaab");
		boolean b = m.matches();
		System.out.println("匹配结果：" + b);

//		p = Pattern.compile("[^1][3,5]+\\d{9}");
//		m = p.matcher("83812345678");
//		b = m.matches();
		
//		p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
		p = Pattern.compile("^.+@.+\\..+$");
		m = p.matcher("276153811@qq.com");
		b = m.matches();
		System.out.println("匹配结果：" + b);
	}
}
