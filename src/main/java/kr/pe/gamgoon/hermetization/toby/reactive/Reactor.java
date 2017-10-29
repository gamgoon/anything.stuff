package kr.pe.gamgoon.hermetization.toby.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Reactor {
	public static void main(String[] args) {
		// publisher 인터페이스를 구현한 유틸리티성 클레스로 Flux 를 제공. 일종의 publisher.
		// iterPub, mapPub 같은 많은 메소드를 제공.
		
		Flux.<Integer>create(e -> {
			e.next(1);
			e.next(2);
			e.next(3);
			e.complete();
		})
		.log()
		.map(s -> s * 10)
		.log()
		.reduce(0, (a, b) -> a + b)
		.log()
		.subscribe(s -> System.out.println(s));
	}
}
