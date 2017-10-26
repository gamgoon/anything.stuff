package kr.pe.gamgoon.hermetization.toby.reactive;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class PubSub {

	public static void main(String[] args) throws InterruptedException {
		// Publisher <- Observable
		// Subscriber <- Observer
		
		Iterable<Integer> itr = Arrays.asList(1,2,3,4,5);
//		ExecutorService es = Executors.newSingleThreadExecutor();
		ExecutorService es = Executors.newCachedThreadPool();
		
		Publisher<Integer> p = new Publisher<Integer>() {

			@Override
			public void subscribe(Subscriber<? super Integer> subscriber) {
				subscriber.onSubscribe(new Subscription() {
					Iterator<Integer> it = itr.iterator();
					@Override
					public void cancel() {
						
					}

					@Override
					public void request(long n) {  
						es.execute(() -> {
							int i = 0;
//							try {
								while (i++ < n) {
									if (it.hasNext())
										subscriber.onNext(it.next());
									else { 
										subscriber.onComplete();
										break;
									}
								}
//							} catch (RuntimeException e) {
//								subscriber.onError(e);
//							}							
						
						});
					}
				});
			}
		};
		
		Subscriber<Integer> s = new Subscriber<Integer>() {
			Subscription subscription;
			
			@Override
			public void onComplete() {
				System.out.println(Thread.currentThread().getName() +  " onComplete");
			}

			@Override
			public void onError(Throwable arg0) {
				System.out.println("onError " + arg0.getMessage());
			}

			@Override
			public void onNext(Integer item) {
				System.out.println(Thread.currentThread().getName() + " onNext " + item);
				this.subscription.request(1);
			}

			@Override
			public void onSubscribe(Subscription subscription) {
				System.out.println(Thread.currentThread().getName() +  " onSubscribe");
				this.subscription = subscription;
				this.subscription.request(1);
			}
			
		};
		
		p.subscribe(s);
		es.awaitTermination(10, TimeUnit.HOURS);
	}
}
