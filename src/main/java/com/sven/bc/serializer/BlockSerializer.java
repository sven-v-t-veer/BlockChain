package com.sven.bc.serializer;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sven.bc.anotations.SerializedField;
import com.sven.bc.block.Block;

public class BlockSerializer implements Serializer{
	
	@Override
	public byte[] serialize(Serializable object) {
		String result = "";
		Class<?> clazz = object.getClass();
		List<Field> fieldlist = getAllFields(new ArrayList<Field>(), clazz);
		for (Field field : fieldlist) {
			field.setAccessible(true);
			System.out.println("Field name: " + field.getName() + " " + field.isAnnotationPresent(SerializedField.class) + " " + field.getType());
			if (field.isAnnotationPresent(SerializedField.class)) {
				try {
					Object o = field.get(object);
					if (o != null) {
						System.out.println("Value: " + o.toString());
						result += o.toString();
					}
				} catch (Exception e) {
					return null;
				}
			} 
		}
		System.out.println(result);
		try {
			return result.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	protected List<Field> getAllFields(List<Field> fields, Class<?> clazz) {
		System.out.println(clazz);
		Field[] fieldlist = clazz.getDeclaredFields();
		System.out.println("Fields: "  + fieldlist.length);
		AccessibleObject.setAccessible(fieldlist, true);
		fields.addAll(Arrays.asList(fieldlist));
		if (clazz.getSuperclass() != Object.class) {
			getAllFields(fields, clazz.getSuperclass());
		}
		return fields;
	}


}
