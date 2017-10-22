package kr.pe.gamgoon.hermetization.toby.generics;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Generics3 {  
	
	// 일반적으로 타입 파라미터는 제한이 없다. 뭐든 올 수 있다.
	// Bounded Type Parameter <T extends List> 와 같은 것.
	// 타입 파라미터에 제한을 두겠다.

	
	static <T extends List> void print(T t) {}
	
	// multiple bound 라 하고 타입의 제약 조건을 여러 타입을 다 만족 해야한다. 클래스는 하나만 ==> 인터섹션 타입 
	static <T extends List & Serializable & Comparable & Closeable> void print2(T t) {}
	// 인터섹션 타입의 사용 - 람다식, 
	
	
	
	// 바운디드 타입 응용 
	// 런타임시에 ERASURE에 의해 삭제되지 않는 제네릭 타입 정보 중 하나 더는 여기서 Comparable 이 정보는 런타임 시에 남는다.
	static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
		return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0).count();
	}
	
	public static void main(String[] args) {
		
		Integer[] arr = new Integer[] {1,2,3,4,5,6,7};
		System.out.println(countGreaterThan(arr, 4));
		
		String[] arr2 = new String[] {"a","b","c","d","e","f","g"};
		System.out.println(countGreaterThan(arr2, "b"));
	}
}
