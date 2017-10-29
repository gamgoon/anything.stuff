package kr.pe.gamgoon.hermetization.toby.reactive.iter;

import java.util.Iterator;
import java.util.Vector;

public class CircularGamesIterator implements Iterator<Game> {

	private Vector<Game> list;
	private int currentPosition;
	
	public CircularGamesIterator(Vector<Game> games) {
		list = games;
		currentPosition = 0;
	}

	@Override
	public boolean hasNext() {
		return currentPosition < list.size();
	}

	@Override
	public Game next() {
		Game el = list.elementAt(currentPosition);
		currentPosition = (currentPosition + 1) % list.size();
		return el;
	}

	@Override
	public void remove() {
		
	}
}
