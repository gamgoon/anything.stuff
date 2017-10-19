package kr.pe.gamgoon.hermetization.toby.supertypetoken;

public class TypeToken2 {
	
	static class Generic<T> {
		T value;
		
		void set(T t) {}
		T get() { return null; }
	}
	
	public static void main(String[] args) throws Exception {
		Generic<String> s = new Generic<>();
		s.value = "String";
		
		Generic<Integer> i = new Generic<>();
		i.value = 1;
		i.set(10);
	}
}
