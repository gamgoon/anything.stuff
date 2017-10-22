package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {
	// type parameter 를 사용하면 제네릭이다.
	
	static class Hello<T> { // T -> type parameter 
		T t;
		T method(T val) { return null; }
	}
	
	public static void main(String[] args) {
		new Hello<String>(); // 여기서의 String 은 타입인자 (type argument)
		
		// parameter 와 argument. 선언되어 있는 것을 parameter, 실제 전달하는(넘기는) 인풋을 argument 
		
		// 제네릭 사용 장점?
		// 1. 컴파일 시점에서 컴파일러가 좀 더 정확하게 타입 체킹을 해줄 수 있다는 장점
		// 2. 타입만 바꾼 클래스의 재사용성. 
		
		
		// Raw Type
		List list = new ArrayList<Integer>();	// 제네릭인 경우이면서 파라미터 인자값을 지정해주지 않고 선언부를 쓰는 것. List list 이 부분 
		
		List<Integer> ints = Arrays.asList(1,2,3);
		List rawInts = ints;
		
		List<Integer> ints2 = rawInts; // warnning 이 뜬다. 	- Type safety: The expression of type List needs unchecked conversion to conform to List<Integer>
		List<String> strs = rawInts;
		
		String str = strs.get(0);
	}
	
}
