package kr.pe.gamgoon.hermetization.toby;

import java.util.Arrays;
import java.util.List;

public class Dispatch5 {
	// SNS 에 글을 올리는 인터페이스,
	interface Post { 
		void postOn(SNS sns); 
	}

	static class Text implements Post {
		@Override
		public void postOn(SNS sns) {
			sns.post(this);
		}
	}
	// 사진 포스팅을 하는 구현 클래스
	static class Picture implements Post {
		@Override
		public void postOn(SNS sns) {
			sns.post(this);
		}
	}
	
	// 소셜 미디어 인터페이스
	interface SNS {
		void post(Text post);
		void post(Picture post);
	}
	// 페북
	static class Facebook implements SNS {

		@Override
		public void post(Text post) {
			System.out.println("text-Facebook");
		}

		@Override
		public void post(Picture post) {
			System.out.println("picture-Facebook");
		}

		
	}
	// 트위터
	static class Twitter implements SNS {

		@Override
		public void post(Text post) {
			System.out.println("text-Twitter");
		}

		@Override
		public void post(Picture post) {
			System.out.println("picture-Twitter");
			
		}

		
	}
	
	// 구글
	static class Google implements SNS {

		@Override
		public void post(Text post) {
			System.out.println("text-Google");
		}

		@Override
		public void post(Picture post) {
			System.out.println("picture-Google");
			
		}

		
	}
	
	// 모든 포스트를 모든 미디어에 올리려 한다
	public static void main(String[] args) {
		
		List<Post> posts = Arrays.asList(new Text(), new Picture()); // 다양한 포스트 형식들 
		List<SNS> sns = Arrays.asList(new Facebook(), new Twitter(), new Google()); // 다양한 소셜 미디어들

		posts.forEach(p -> sns.forEach(s -> p.postOn(s))); // 더블 디스패치라는 기법이다. instanceof 기법에 비해 if 문 빠졌고...
															// 그전에 비해 미디어가 하나 추가되어도 (Google) 포스트쪽에는 수정한 것이 없다.
	}
	
	
}
