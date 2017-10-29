package kr.pe.gamgoon.hermetization.toby.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DelegateSub<T, R> implements Subscriber<T> {
	Subscriber sub;

	public DelegateSub(Subscriber<? super R> sub) {
		this.sub = sub;
	}

	@Override
	public void onComplete() {
//		log.debug("mapPub onComplere");
		sub.onComplete();
	}

	@Override
	public void onError(Throwable t) {
//		log.debug("mapPub onError");
		sub.onError(t);
	}

	@Override
	public void onNext(T i) {
//		log.debug("mapPub onNext {}" , i);
		sub.onNext(i);
	}

	@Override
	public void onSubscribe(Subscription s) {
//		log.debug("mapPub onSubscribe");
		sub.onSubscribe(s);
	}

}
