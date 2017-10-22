package kr.pe.gamgoon.hermetization.toby.generics;

public class Generics2 {
	
	static class Hello<T> {
		
	}
	
	// Generic Method
	static <T> void print(T t) {
		System.out.println(t.toString());
	}
	
	public static void main(String[] args) {
		print("Hellow");
		print(1);
	}


	static class Generic<T> {
		// 이때는 static 메소드에서 클래스의 타입파라미터 타입을 사용할 수 없다. 이유는 클래스의 타입 파라미터는 인스턴스가 만들어질때 받아오게 되어있기때문. 이때는 메소드레벨의 타입 파라메터를 적용해 주어야한다. 
		// static <T> void print(T t) {}  이렇게.
		static void print(T t) { 
			System.out.println(t.toString());
		}
		
		// 생성자에도
		public <S> Generic(S s) {
			
		}
	}
}
