package kr.pe.gamgoon.hermetization.toby.dispatch;

public class Dispatch {
	static class Service {
		void run(int number) {
			System.out.println("run(" + number +")");
		}
		
		void run(String msg) {
			System.out.println("run(" + msg + ")");
		}
	}
	
	public static void main(String[] args) {
		new Service().run(1); // static dispatch , 실행시점이 아니라도 어느 메소드 호출이 일어날지를 컴파일러가 (바이트코드에서도) 알고 있다.
		new Service().run("Dispatch");
		
	}
}
