package com.padeltrophy.util.json;

public interface JsonTransformer {
	
	public String toJson(Object data);

	public Object fromJson(String json, Class clazz);
}
