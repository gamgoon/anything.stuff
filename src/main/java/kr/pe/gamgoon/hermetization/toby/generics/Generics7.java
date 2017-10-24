package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Generics7 {  
	static <T> void method1(List<T> list) {
		
	}
	
	static void method2(List<?> list) {
		// ? 를 사용할 수 없다.
		list.add(null);
		list.size();
		list.clear();
		Iterator<?> it = list.iterator();
		
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,3,2);
//		method2(list);
		
		System.out.println(isEmpty(list));
		System.out.println(frequency(list, 3));
		
		System.out.println(Collections.<Number>max(list, 
				(Comparator<Object>)(a,b) -> a.toString().compareTo(b.toString())));
	}

	// 아래와 같은 경우는 와일드카드를 사용하는 것이 옳바르다.
	
//	static <T> long frequency(List<T> list, T elem) {
	static long frequency(List<?> list, Object elem) {	// 와일드카드는 Object 의 기능을 사용하는 것은 허용이된다. 
		return list.stream().filter(s -> s.equals(elem)).count();
	}

	static boolean isEmpty(List<?> list) { // T를 사용하지 않기때문에 <T> List<T> 와 차이가 없다.
		return list.size() == 0;
	}
	
	static <T extends Comparable<? super T>> T max(List<? extends T> list) {
		return list.stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).get();
	}
}
