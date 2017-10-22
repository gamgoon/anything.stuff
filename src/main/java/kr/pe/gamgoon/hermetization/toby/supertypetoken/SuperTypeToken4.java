package kr.pe.gamgoon.hermetization.toby.supertypetoken;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ResolvableType;

// 최종 super type token
public class SuperTypeToken4 {
	static class TypesafeMap {
		Map<Type, Object> map = new HashMap<>();
		
		<T> void put(TypeReference<T> tr, T value) {
			map.put(tr.type, value);
		}
		
		<T> T get(TypeReference<T> tr) {
//			return ((Class<T>)tr.type).cast(map.get(tr)); // TypeReference<String> : ok , TypeReference<List<String> : not ok
			if (tr.type instanceof Class<?>)
				return ((Class<T>)tr.type).cast(map.get(tr.type));
			else 
				return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr.type));
		}
	}

	static class TypeReference<T> { // Sup
		Type type;

		public TypeReference() {
			Type stype = getClass().getGenericSuperclass();
			if (stype instanceof ParameterizedType) {
				this.type = ((ParameterizedType)stype).getActualTypeArguments()[0];
			}
			else throw new RuntimeException();
		}
	}
	
	public static void main(String[] args) {
//		TypeReference t = new TypeReference<List<Map<String,List<String>>>>() {};
//		System.out.println(t.type);
		TypesafeMap m = new TypesafeMap();
		m.put(new TypeReference<String>() {}, "String");
		m.put(new TypeReference<Integer>() {}, 1);
		m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1,2,3));
		m.put(new TypeReference<List<String>>() {}, Arrays.asList("a", "b", "c"));
		m.put(new TypeReference<List<List<String>>>() {}, Arrays.asList(Arrays.asList("a", "b", "c"), Arrays.asList("a", "b", "c"), Arrays.asList("a", "b", "c")));
		
		System.out.println(m.get(new TypeReference<String>() {}));
		System.out.println(m.get(new TypeReference<Integer>() {}));
		System.out.println(m.get(new TypeReference<List<String>>() {}));
		System.out.println(m.get(new TypeReference<List<Integer>>() {}));
		System.out.println(m.get(new TypeReference<List<List<String>>>() {}));
		
		
		ResolvableType rt = ResolvableType.forInstance(new TypeReference<List<String>>() {});
		System.out.println(rt.getSuperType().getGeneric(0).getType());
		System.out.println(rt.getSuperType().getGeneric(0).getNested(2).getType());
		
	}
}
