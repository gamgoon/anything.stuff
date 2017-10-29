package kr.pe.gamgoon.hermetization.toby.reactive.iter;

import java.util.Iterator;
import java.util.Vector;

public class CustomBadIterator implements Iterator<Game>, Iterable<Game> {

	public CustomBadIterator(Vector<Game> games) {
	}

	@Override
	public Iterator<Game> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Game next() {
		// TODO Auto-generated method stub
		return null;
	}

}
