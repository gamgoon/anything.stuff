package kr.pe.gamgoon.hermetization.toby.dispatch;

import java.util.Arrays;
import java.util.List;

public class Dispatch3 {
	// SNS 에 글을 올리는 인터페이스
	interface Post { void postOn(SNS sns); }
	// 타입 판별에 if 문을 썼다.
	// - 미디어를 하나 추가하면 모든 포스트 구현 클래스마다 미디어 타입별 if 문을 추가해야한다.
	// 문자 포스팅을 하는 구현 클래스
	static class Text implements Post {
		public void postOn(SNS sns) {
			if (sns instanceof Facebook) {
				System.out.println("text - facebook");
			} 
			if (sns instanceof Twitter) {
				System.out.println("text - twitter");
			} 
		}
	}
	// 사진 포스팅을 하는 구현 클래스
	static class Picture implements Post {
		public void postOn(SNS sns) {
			if (sns instanceof Facebook) {
				System.out.println("picture - facebook");
			} 
			if (sns instanceof Twitter) {
				System.out.println("picture - twitter");
			} 
		}
	}
	
	// 소셜 미디어 인터페이스
	interface SNS {}
	// 페북
	static class Facebook implements SNS {
		
	}
	// 트위터
	static class Twitter implements SNS {
		
	}
	
	// 모든 포스트를 모든 미디어에 올리려 한다
	public static void main(String[] args) {
		
		List<Post> posts = Arrays.asList(new Text(), new Picture()); // 다양한 포스트 형식들 
		List<SNS> sns = Arrays.asList(new Facebook(), new Twitter()); // 다양한 소셜 미디어들

		posts.forEach(p -> sns.forEach(s -> p.postOn(s))); // 런타임 시점에 Post의 구현 클래스가 정해진다.
	}
	
	
}
