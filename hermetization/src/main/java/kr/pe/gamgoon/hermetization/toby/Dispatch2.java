package kr.pe.gamgoon.hermetization.toby;

import java.util.Arrays;
import java.util.List;

public class Dispatch2 {
	static abstract class Service {
		abstract void run();
	}
	
	static class MyService1 extends Service {

		@Override
		void run() {
			System.out.println("run1");
		}
		
	}
	
	static class MyService2 extends Service {

		@Override
		void run() {
			System.out.println("run2");
		}
		
	}
	public static void main(String[] args) {
		MyService1 svc1 = new MyService1();
		svc1.run();
		MyService2 svc2 = new MyService2();
		svc2.run();
		
		Service svc = new MyService1();
		
		
		
		
		svc.run(); 	// dynamic dispatch : 런타임시에 svc에 할당되어있는 오브젝트가 뭔가를 보고 결정 
					// receiver parameter : this에 해당되는 오브젝트가 receiver parameter로 들어가있다
		
		List<Service> svcs = Arrays.asList(new MyService1(), new MyService2());
		svcs.forEach(Service::run); // method reference 사용
	}
}
