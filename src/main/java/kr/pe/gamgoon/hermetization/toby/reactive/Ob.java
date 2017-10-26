package kr.pe.gamgoon.hermetization.toby.reactive;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ob {
	
	static class IntObservable extends Observable implements Runnable {

		@Override
		public void run() {
			for (int i=1; i<=10; i++) {
				setChanged();
				notifyObservers(i);		// push
				// int i = it.next();	// pull
			}
		}
		
	}
	
	public static void main(String[] args) {
		// Observable  // Source -> Event/Data -> Observer
		Observer ob = new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				System.out.println(Thread.currentThread().getName() + " " + arg);
			}
		};
		
		IntObservable io = new IntObservable();
		io.addObserver(ob);
//		io.run();
		
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(io);
		
		System.out.println(Thread.currentThread().getName() + " EXIT");
		es.shutdown();
	}
}
