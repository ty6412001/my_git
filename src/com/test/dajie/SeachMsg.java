package com.test.dajie;

import java.io.Serializable;
import java.util.Map;

public class SeachMsg implements Serializable{
	
	private String key;
	private String value;
	public SeachMsg(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return key + "=" + value ;
	}
	
	
}
