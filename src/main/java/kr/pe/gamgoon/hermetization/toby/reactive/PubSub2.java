package kr.pe.gamgoon.hermetization.toby.reactive;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;


/**
 * Reactive Stream - Operators
 * 
 * Publisher -> [Data1] -> Op1 -> [Data2] -> Op2 -> [Data3] -> Subscriber
 * Operator 를 이용해 Data 를 가공
 * 1. map (d1 -> f -> d2)
 * 
 * @author gamgoon
 *
 */
@Slf4j
public class PubSub2 {

	public static void main(String[] args) {
		// pub -> [Data1] -> mapPub -> [Data2] -> map2Pub -> [Data3] -> logSub
		log.debug("1");
		Publisher<Integer> pub = iterPub(Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList()));
		Publisher<String> mapPub = mapPub(pub, s -> "[" + s + "]");  // Operator1
//		Publisher<Integer> map2Pub = mapPub(mapPub, s -> -s);  // Operator2
//		map2Pub.subscribe(logSub());
		mapPub.subscribe(logSub());
		
		// pub -> [Data1] -> sumPub -> [Sum] -> logSub
//		log.debug("2");
//		Publisher<Integer> sumPub = sumPub(pub);
//		sumPub.subscribe(logSub());
		
		log.debug("3");
		Publisher<StringBuilder> reducePub = reducePub(pub, new StringBuilder(), (a, b) -> a.append(b + ","));
		reducePub.subscribe(logSub());
	}


	// 1,2,3,4,5
	// 0 -> (0,1) -> 0 + 1 = 1
	// 1 -> (1,2) -> 1 + 2 = 3
	// 3 -> (3,3) -> 3 + 3 = 6
	// ...
	private static <T, R> Publisher<R> reducePub(Publisher<T> pub, R init,
			BiFunction<R, T, R> biFunction) {
		return new Publisher<R>() {
			
			@Override
			public void subscribe(Subscriber<? super R> sub) {
				pub.subscribe(new DelegateSub<T, R>(sub) {
					R result = init;

					@Override
					public void onNext(T i) {
						result = biFunction.apply(result, i);
					}

					@Override
					public void onComplete() {
						sub.onNext(result);
						sub.onComplete();
					}
					
				});
			}
			
		};
	}

//
//	private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
//		return new Publisher<Integer>() {
//
//			@Override
//			public void subscribe(Subscriber<? super Integer> sub) {
//				pub.subscribe(new DelegateSub<Integer>(sub) {
//					int sum = 0;
//					@Override
//					public void onNext(Integer i) {
//						sum += i;
//					}
//					@Override
//					public void onComplete() {
//						sub.onNext(sum);
//						sub.onComplete();
//					}
//					
//				});
//			}
//			
//		};
//	}


	private static <T, R> Publisher<R> mapPub(Publisher<T> pub, Function<T, R> function) {
		return new Publisher<R>() {

			@Override
			public void subscribe(Subscriber<? super R> sub) {
//				pub.subscribe(sub);
				pub.subscribe(new DelegateSub<T, R>(sub) {

					@Override
					public void onNext(T i) {
						sub.onNext(function.apply(i));
					}
					
				});
			}
			
		};
	}


	private static <T> Subscriber<T> logSub() {
		return new Subscriber<T>() {

			@Override
			public void onComplete() {
				log.debug("onComplete:");
			}

			@Override
			public void onError(Throwable arg0) {
				log.debug("onError: {}", arg0);
			}

			@Override
			public void onNext(T arg0) {
				log.debug("onNext: {}", arg0);
			}

			@Override
			public void onSubscribe(Subscription arg0) {
				log.debug("onSubscribe:");
				arg0.request(Long.MAX_VALUE);
			}
			
		};
	}

	private static Publisher<Integer> iterPub(List<Integer> iter) {
		return new Publisher<Integer>() {

			@Override
			public void subscribe(Subscriber<? super Integer> sub) {
				sub.onSubscribe(new Subscription() {

					@Override
					public void cancel() {
						
					}

					@Override
					public void request(long arg0) {
						try {
							iter.forEach(s -> sub.onNext(s));
							sub.onComplete();	
						} catch (Throwable t) {
							sub.onError(t);
						}
					}
					
				});
			}
			
		};
	}
}
