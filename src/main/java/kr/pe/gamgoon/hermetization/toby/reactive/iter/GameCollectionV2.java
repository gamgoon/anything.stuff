package kr.pe.gamgoon.hermetization.toby.reactive.iter;

import java.util.Iterator;
import java.util.Vector;

public class GameCollectionV2 { 
	private Vector<Game> games;
	private Vector<GameConsole> consoles;
	
	private class Games implements Iterable<Game> {

		@Override
		public Iterator<Game> iterator() {
			return games.iterator();
		}
	}
	
	private class Consoles implements Iterable<GameConsole> {

		@Override
		public Iterator<GameConsole> iterator() {
			return consoles.iterator();
		}
	}
	
	public GameCollectionV2() {
		games = new Vector<Game>();
		consoles = new Vector<GameConsole>();
	}
	
	public void add(Game game) {
		games.add(game);
	}
	
	public void add(GameConsole console) {
		consoles.add(console);
	}
	
	public Games games() {
		return new Games();
	}
	
	public Consoles consoles() {
		return new Consoles();
	}
}
