package kr.pe.gamgoon.hermetization.toby.supertypetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {
	static class Sup<T> {
		T value;
	}
	
	static class Sub extends Sup<List<String>> {
		
	}
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Sup<String> s = new Sup<>();
		s.value = "String";

		
		// s 를 어디다 던졌다. 제네릭 파라미터가 뭘로 정해졌는지를 알기 위해 리플렉션을 이용해서.
		// String 타입이 나올 것 같지만, 타입 파라미터가 적용되지 않은 기본의 Object 가 나온다. 즉, 리플렉션을 이용해서도 제네릭 파라미터를 알 수 없다.
		// ERASURE에 의해서 런타임시에 이 정보는 모두 사라진다.제네릭을 이용해서 parameterized type 으로 만든 이 정보는 
		// 런타임 시에는 Sup s = new Sup(); 과 Sup<String> s = new Sup<>(); 과 같다. 타입변수의 타입을 정확히 알아낼 수 없다. 
		System.out.println(s.getClass().getDeclaredField("value").getType());
		
		// 이게 가능한 경우가 있다. 즉, 어떤건 없어지고, 어떤건 안없어진다.
		Sub b = new Sub();
		Type t = b.getClass().getGenericSuperclass();
		// Sup<String> 과 같이 파라미터 타입이 있는 타입?
		ParameterizedType ptype = (ParameterizedType) t;
		System.out.println(ptype.getActualTypeArguments()[0]);	// String 이 나온다.
		
		// 차이점은?
		// new Sup<String> 과 같은 경우는 바이트코드 호환성 문제때문에 런타임시에 String 타입 정보는 삭제가 된다. 하지만 새로운 타입을 정의하면서 수퍼클래스를 제네릭 클랙스로 즉, 타입파라미터를 가진 클래스로 이용을 하고 여기다 타입을 지정을 해서 (확정이된다. 전에 예기한 REIFICATION?)
		// 리플렉션을 통해서 런타임시에 접근을 할 수 있도록 바이트코드에 그 정보가 남아있다. 
		// Sup<String> 뿐만 아니라 Sup<List<String>> 과 같은 타입도 런타임시에 파라미터 타입을 알 수 있다.
		// 
	}
}
