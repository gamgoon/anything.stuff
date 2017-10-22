package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.Arrays;
import java.util.List;

public class Generics5 {  
	
	// 아래 둘의 차이점은? 
	
	static <T> void method1(List<T> t) {
		
	}
	static void method2(List<?> t) {
		
	}
	
	
	// 또는
	static void method3(List<? extends Comparable> t) {
		
	}
	
	
	public static void main(String[] args) {
		List<?> list;  // ? : wildcards   모른다. 관심이 없다.
//		List<T> list2; // T : 모르지만, 이 타입이 정해지면 그 타입이 뭔지 알고 이후에 사용을 하겠다. 
		
		
		List<? extends Object> list3;  // 위의 list 와 같은 의미. Object 가 가지고 있는 기능만 가져다가 사용하겠다고 정의하는 것을 의미. 
		List<?> list4; // 타입이 뭐가 오든지 상관없고, List 가 가지고 있는 메소드만(기능) 사용할래. 원소에 대한 기능(메소드)는 안쓸래.
		
		
		
		
		
		printList(Arrays.asList(1,2,3));
		printList2(Arrays.asList(1,2,3));
		
		
		List<Integer> list5 = Arrays.asList(1,2,3);
//		printList(list5);  	// x
		printList2(list5);	// o
	}
	
	static void printList(List<Object> list) {
		list.forEach(s -> System.out.println(s));
	}
	
	static void printList2(List<?> list) {
		list.forEach(s -> System.out.println(s));
	}
}
