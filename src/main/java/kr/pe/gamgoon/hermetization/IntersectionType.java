package kr.pe.gamgoon.hermetization;

import java.util.function.Consumer;
import java.util.function.Function;

public class IntersectionType {
	
	interface Pair<T> {
		T getFirst();
		T getSecond();
		void setFirst(T first);
		void setSecond(T second);
	}
	
	static class Name implements Pair<String> {
		
		String firstName;
		String lastName;
		
		public Name(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String getFirst() {
			return this.firstName;
		}

		@Override
		public String getSecond() {
			return this.lastName;
		}

		@Override
		public void setFirst(String first) {
			this.firstName = first;
		}

		@Override
		public void setSecond(String second) {
			this.lastName = second;
		}
		
	}
	
	
	interface ForwardingPair<T> extends DelegateTo<Pair<T>>, Pair<T> {

		// Pair 의 구현해야할 메소드를 default 메소드로 모두 구현해버리면 된다. 이렇게 하면 구현 해야할 메소드는 DelegateTo 의 메소드 하나만 남는다.  
		
		@Override
		default T getFirst() {
			return delegate().getFirst();
		}

		@Override
		default T getSecond() {
			return delegate().getSecond();
		}

		@Override
		default void setFirst(T first) {
			delegate().setFirst(first);
		}

		@Override
		default void setSecond(T second) {
			delegate().setSecond(second);
		}

	}
	
	interface DelegateTo<T> {
		T delegate();
	}
	
	private static <T extends DelegateTo<S>, S> void run(T t, Consumer<T> consumer) {
		consumer.accept(t);
	}
	
	interface Convertable<T> extends DelegateTo<Pair<T>> {
		default void convert(Function<T, T> mapper) {
			Pair<T> pair = delegate();
			pair.setFirst(mapper.apply(pair.getFirst()));
			pair.setSecond(mapper.apply(pair.getSecond()));
		}
	}
	
	interface Printable<T> extends DelegateTo<Pair<T>> {
		default void print() {
			System.out.println(delegate().getFirst() + " " + delegate().getSecond());
		}
	}
	
	
	
	public static void main(String[] args) {
		// 기존에 있는 리스트를 구현한 클래스를 가져와서 거기에 원래 없는 기능을 동적으로 삽입하는
	
		Pair<String> name = new Name("Toby", "Lee");
		
		run((ForwardingPair<String> & Convertable<String> & Printable<String>) () -> name, o -> {
			o.print();
			o.convert(s -> s.toUpperCase());
			o.print();
			o.convert(s -> s.substring(0, 2));
			o.print();
		});
	}

	static  <T> void print(Pair<T> pair) {
		System.out.println(pair.getFirst() + " " + pair.getSecond());
	}
}
