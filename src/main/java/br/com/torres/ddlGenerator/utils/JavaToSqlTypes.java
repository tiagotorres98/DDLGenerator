package br.com.torres.ddlGenerator.utils;

import java.util.HashMap;
import java.util.Map;

public class JavaToSqlTypes {
	
	public static String convert(String type){
		String result = getTypes().get(type);
		return result == null?type:result;
	}
	
	public static Boolean canConvertToSqlType(String type) {
		return getTypes().get(type) == null ? false : true;
	}
	
	public static Map<String,String> getTypes(){
		Map<String,String> types = new HashMap<>();
		types.put("long","bigint");
		types.put("boolean","bit");
		types.put("localdate","date");
		types.put("localdatetime","datetime");
		types.put("double","float");
		types.put("int","int");
		types.put("bigdecimal","numeric");
		types.put("float","real");
		types.put("short","smallint");
		types.put("byte[]","varbinary");
		types.put("string","varchar");
		types.put("integer","int");
		return types;
	}

}
