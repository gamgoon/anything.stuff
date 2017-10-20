package kr.pe.gamgoon.hermetization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeToken {
	static class TypesafeMap {
		Map<Class<?>, Object> map = new HashMap<>();
		
		<T> void put(Class<T> clazz, T value) {
			map.put(clazz, value);
		}
		
		<T> T get(Class<T> clazz) {
			return clazz.cast(map.get(clazz));
		}
	}
	
	// Type Token : 특정 타입의 클래스 정보를 넘겨서 타입 안정성을 꽤하는 것을 , 어떤 타입 정보를 값으로 넘기겠다. 
	public static void main(String[] args) {
		TypesafeMap m = new TypesafeMap();
		m.put(Integer.class, 1);
		m.put(String.class, "String");
		m.put(List.class, Arrays.asList(1,2,3)); // List
		
		// 타입 토큰의 한계 : 클래스 리터럴로 해당 클래스의 오브젝트를 가져올때 파라메터가 적용된 것을 구분해서 가져올 수 없다, 
		// 제네릭에 대한 정보가 없다. 이런 식의 타입 토큰 방식으로는 제네릭 정보까지 있는 타입 토큰을 쓸 수 없다.
		// 그래서 수퍼 타입 토큰!!
		m.put(List<Integer>.class, Arrays.asList(1,2,3)); // List<Integer>
		m.put(List<String>.class, Arrays.asList("a", "b", "c")); // List<String>
		
		System.out.println(m.get(Integer.class));
		System.out.println(m.get(String.class));
		System.out.println(m.get(List.class));
	}
}
