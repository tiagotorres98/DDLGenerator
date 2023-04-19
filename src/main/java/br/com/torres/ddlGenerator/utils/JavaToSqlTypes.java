package br.com.torres.ddlGenerator.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JavaToSqlTypes {
	
	public static String convert(String type){
		String result = getTypes().get(type.toLowerCase());
		return result == null?type:result;
	}
	
	public static Boolean canConvertToSqlType(String type) {
		if(Objects.isNull(type)) {
			return Boolean.FALSE;
		}
		return getTypes().get(type.toLowerCase()) == null ? false : true;
	}
	
	public static Map<String,String> getTypes(){
		Map<String,String> types = new HashMap<String, String>();
		types.put("long","bigint");
		types.put("biginteger","bigint");
		types.put("boolean","bit");
		types.put("localdate","date");
		types.put("date","date");
		types.put("localtime","time");
		types.put("localdatetime","datetime");
		types.put("double","float");
		types.put("int","int");
		types.put("bigdecimal","numeric");
		types.put("float","real");
		types.put("short","smallint");
		types.put("byte[]","varbinary");
		types.put("string","varchar");
		types.put("integer","int");
		types.put("ordinal", "int");
		types.put("character", "char");
		types.put("char", "char");
		types.put("bigint", "bigint");
		types.put("smallint", "smallint");
		return types;
	}
	
	public static Boolean isNumberTypes(String type){
		
		if(Objects.isNull(type)) {
			return Boolean.FALSE;
		}
		
		Map<String,String> types = new HashMap<String, String>();
		types.put("long","bigint");
		types.put("biginteger","bigint");
		types.put("double","float");
		types.put("int","int");
		types.put("bigdecimal","numeric");
		types.put("float","real");
		types.put("short","smallint");
		types.put("integer","int");
		types.put("ordinal", "int");
		types.put("bigint", "bigint");
		types.put("smallint", "smallint");
		
		return types.get(type.toLowerCase()) == null ? false : true;
	}

}
