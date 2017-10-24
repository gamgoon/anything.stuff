package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics8 {  
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,3,2);
		reverse(list);
		System.out.println(list);
	}

	// 와일드 카드로 만든거는 이게 캡쳐라는 프로세스가 들어간다는 것. 추론. 캡쳐가 필요로하는 상황에서는 추론 해보고 안되면 오류발생
	// The method set(int, capture#3-of ?) in the type List<capture#3-of ?> is not applicable for the arguments (int, capture#4-of ?)
	// 캡쳐가 문제가 될떄는 캡쳐를 하는 핼퍼 메소드를 만들어라. 
	// 또는 Raw type을 이용
	static void reverse(List<?> list) { 
		reverseHelper(list);
	}
	
	
	private static <T> void reverseHelper(List<T> list) { // 여기서 캡쳐를 해서 문제가 발생하지 않는다.
		List<T> temp = new ArrayList<>(list);
		List list2 = list; // raw type
		for (int i=0; i<list.size(); i++) {
//			list.set(i, temp.get(list.size() - i - 1));
			list2.set(i, list2.get(list2.size() - i - 1));
		}
	}
}
