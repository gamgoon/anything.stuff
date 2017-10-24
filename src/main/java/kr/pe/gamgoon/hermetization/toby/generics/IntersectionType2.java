package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.function.Consumer;

//IntersectionType 
public class IntersectionType2 {  
	
	interface DelegateTo<T> {
		T delegate();
	}
	
	interface Hello extends DelegateTo<String> {
		default void hello() {
			System.out.println("Hello " + delegate());
		}
	}
	
	interface UpperCase extends DelegateTo<String> {
		default void upperCase() {
			System.out.println(delegate().toUpperCase());
		}
	}
	
	public static void main(String[] args) {
		run((DelegateTo<String> & Hello & UpperCase)()->"Daniel Jung", o -> {
			o.hello();
			o.upperCase();
		});
	}

	private static <T extends DelegateTo<S>, S> void run(T t, Consumer<T> consumer) {
		consumer.accept(t);
	}
}
