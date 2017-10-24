package kr.pe.gamgoon.hermetization.toby.generics;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

//IntersectionType 
public class IntersectionType {  
	
	interface Hello {
		default void hello() {
			System.out.println("Hello");
		}
	}
	
	interface Hi extends Function{
		default void hi() {
			System.out.println("Hi");
		}
	}
	
	public static void main(String[] args) {
		
//		hello((Function & Serializable & Cloneable)s -> s); // & 를 써서 인터페이스를 둘 이상을 붙일 이유가 있느냐? Marker 인터페이스! 메소드가 없다! (Serializable), 최종적으로 메소드가 1개이기만 하면 된다.
		
		

		// 최종 메소드가 1개 라는 건 default 메소드는 제외.
		
		hello((Function & Hello & Hi) s -> s);
		
		run((Function & Hello & Hi & Printer)s->s, o -> {
			o.hello();
			o.hi();
			o.print("Lambda");
		});
	}
	
	
//	private static void hello(Function o) {
//	private static <T extends Function & Serializable & Cloneable> void hello(T o) {
//		
//	}

	interface Printer {
		default void print(String str) {
			System.out.println(str);
		}
	}
	
	// Benji Weber http://benjiweber.co.uk/blog/posts/java/
	// 아이디어 : 인터섹션타입을 이용하면 클래스를 정의하지 않고도 인터페이스를 조합해서 여러기능을 가진 코드를 만들수있다 
	private static <T extends Function & Hello & Hi> void hello(T t) {
		t.hello();
		t.hi();
	}
	
	// Consumer : 리턴은 없고 파라미터만 하나 있는 것
	private static <T extends Function> void run(T t, Consumer<T> consumer) {
		consumer.accept(t);
	}
}
