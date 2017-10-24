package kr.pe.gamgoon.hermetization.toby;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Generics {
	static boolean isEmpty(List<?> list) {
		return list.size() == 0;
	}
	
	static long frequency(List<?> list, Object elem) {
		return list.stream().filter(s -> s.equals(elem)).count();
	}
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,3,2);
		System.out.println(frequency(list, 1));
		
		System.out.println(max(list));
		
		System.out.println(Collections.max(list, (Comparator<Object>)(a,b) -> a.toString().compareTo(b.toString())));
	}

	// <? super T> 상위 한정(경계) , <? extends T> 하위 한정(경계)
	// 와일드카드 쓰는 가이드, 상위 한정, 하위한정. 사용되기 위해 쓴다면 상위한정. 그 밖에서 사용되기 위해서라면 하위한정???? 찾아볼것!!!!
	// static <T extends Comparable<? super T>> T max(List<? extends T> list) {  
	// 넘기는 파라메터 타입이 메소드 안에서 소모(사용)되는 경우는 하위 경계를 하고, 메소드 외부에서 사용되면 상위 경계를 쓴다. 
	// 예제 Collections.copy 
	// public static <T> void copy(List<? super T> dest, List<? extends T> src) {
	// 소모(사용) 한다는 것이 실제 해당 메소드 내에서 해당 타입의 메소드를 호출하느냐가 아니라( 둘다 호출될 수 있다) copy 의 dest 처럼 결고 아웃풋으로 사용되어 다른 곳에서 사용되어짐을 뜻한다.
	// 즉 아웃풋으로써는 상위 한정, 인풋으로써는 하위 한정. 
	static <T extends Comparable<? super T>> T max(List<? extends T> list) {
		return list.stream().reduce((a,b) -> a.compareTo(b) > 0 ? a : b).get();
	}
}
