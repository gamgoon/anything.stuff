package kr.pe.gamgoon.hermetization.toby.generics;

import java.util.function.Function;

//Lambda
public class Generics9 {  
	public static void main(String[] args) {
		
		Function<String, String> f = new Function<String, String>() {
			@Override
			public String apply(String t) {
				return t;
			}
		};
		
		hello(f);
		hello(new Function<String, String>() {
			@Override
			public String apply(String t) {
				return t;
			}
		});
		
		hello(
			(String s) -> {
				return s;
			}
		);
		
		hello(s -> s);
		
	}
	private static void hello(Function<String, String> object) {
		
	}
}
