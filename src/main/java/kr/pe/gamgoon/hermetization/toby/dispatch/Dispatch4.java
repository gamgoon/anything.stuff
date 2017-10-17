package kr.pe.gamgoon.hermetization.toby.dispatch;

import java.util.Arrays;
import java.util.List;

public class Dispatch4 {
	// SNS 에 글을 올리는 인터페이스,
	// SNS 타입구분을 위해 if 문을 제거하기위해 Post 에 미디어별 postOn 메서드를 추가했다
	interface Post { 
		void postOn(Twitter sns); 
		void postOn(Facebook sns);
	}
	// 포스트 구현 클래스에서는 각 미디어블 메서드를 구현하기만 하면 될 것 같다.
	static class Text implements Post {

		@Override
		public void postOn(Twitter sns) {
			System.out.println("text-twitter");
		}

		@Override
		public void postOn(Facebook sns) {
			System.out.println("text-facebook");
		}
	}
	// 사진 포스팅을 하는 구현 클래스
	static class Picture implements Post {

		@Override
		public void postOn(Twitter sns) {
			System.out.println("picture-twitter");
		}

		@Override
		public void postOn(Facebook sns) {
			System.out.println("picture-facebook");
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

//		posts.forEach(p -> sns.forEach(s -> p.postOn(s))); // 하지만 SNS 타입을 받는 메소드를 찾을 수 없다. 컴파일 시점에 파라미터의 타입을 체크해서 정해둬야한다.
															// 실제 s의 타입은 SNS 이다. 이건 다이나믹 디스패치가 아닌 스태틱 디스패치다. 
	}
	
	
}
