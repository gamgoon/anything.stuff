package kr.pe.gamgoon.hermetization.toby.reactive.iter;

import java.util.Iterator;

public class Client {
	public static void main(String[] args) {
		GameCollection gc = new GameCollection();
		
		Iterator<Game> gameIterator = gc.iterator();
		
		while (gameIterator.hasNext()) {
			Game g = gameIterator.next();
			System.out.println(g.getName());
		}
		
		for (Game g : gc) {
			System.out.println(g.getName());
		}

		GameCollectionV2 gc2 = new GameCollectionV2();
		for (Game g : gc2.games()) {
			System.out.println(g.getName());
		}

		for (GameConsole g : gc2.consoles()) {
			System.out.println(g.getName());
		}
		
		iterateTwice(gc);
		iterateTwice(gc2.games());
		
		
		GameCollection gc3 = new GameCollection();
		
		gc3.add(new Game("AAA"));
		gc3.add(new Game("BBB"));
		gc3.add(new Game("CCC"));
		gc3.add(new Game("DDD"));
		
		System.out.println("");
		iterateTwice(gc3);
		
		System.out.println(" ");
		CustomBadIterator gamesBadIterator = gc3.games();
		iterateTwice(gamesBadIterator);
		
	}
	
	public static void iterateTwice(Iterable<Game> games) {
		for (Game g : games) {
			System.out.println(g.getName());
		}
		
		for (Game g : games) {
			System.out.println(g.getName());
		}
		System.out.println();
	}
}
