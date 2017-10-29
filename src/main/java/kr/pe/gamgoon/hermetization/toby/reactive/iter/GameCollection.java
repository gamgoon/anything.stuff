package kr.pe.gamgoon.hermetization.toby.reactive.iter;

import java.util.Iterator;
import java.util.Vector;

public class GameCollection implements Iterable<Game> {
	private Vector<Game> games;
	
	public GameCollection() {
		games = new Vector<>();
	}
	
	public void add(Game game) {
		games.add(game);
	}
	
	@Override
	public Iterator<Game> iterator() {
//		return games.iterator();
		return new CircularGamesIterator(games);
	}

	public CustomBadIterator games() {
		return new CustomBadIterator(games);
	}
}
