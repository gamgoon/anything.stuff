package kr.pe.gamgoon.hermetization.toby.supertypetoken;

import java.awt.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TypeToken3 {
	static class TypesafeMap {
		Map<Class<?>, Object> map = new HashMap<>();
		
		<T> void put(Class<T> clazz, T value) {
			map.put(clazz, value);
		}
		
		<T> T get(Class<T> clazz) {
			return clazz.cast(map.get(clazz));
		}
	}
	
	// Type Token
	public static void main(String[] args) {
		TypesafeMap m = new TypesafeMap();
		m.put(Integer.class, 1);
		m.put(String.class, "String");
//		m.put(List.class, Arrays.asList(1,2,3));
		
		System.out.println(m.get(Integer.class));
		System.out.println(m.get(String.class));
	}
}
