package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics6 {  
	static class A {}
	static class B extends A {}
	
	static void print(List<? extends Object> list) {}
	
	public static void main(String[] args) {
		List<B> listB = new ArrayList<B>();
		List<Integer> listInt = Arrays.asList(1,2,3);
		
//		List<A> la = listB; // x
		List<? extends A> la = listB;
		List<? super B> l2 = listB;
//		List<? super A> l3 = listB; // x
		
		la.add(null);
		la.add(new B());	// error 캡쳐가 안된다. 
	}
}
