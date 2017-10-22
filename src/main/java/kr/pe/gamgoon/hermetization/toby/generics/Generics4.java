package kr.pe.gamgoon.hermetization.toby.generics;

import java.io.Closeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Generics4 {  
	
	public static void main(String[] args) {
		Integer i = 10;
		Number n = i;
		
		List<Integer> intList = new ArrayList<>();
		// List<Number> numberList = intList;	// Integer 는 Number의 서브타입이라서 될 것 같지만, List<Integer> 는 List<Number>의 서브타입이 아니다!!!!
		
		ArrayList<Integer> arrList = new ArrayList<>();
		List<Integer> intList2 = arrList;	// 요건 된다. 
		
		
		List<String> s1 = new MyList<String, Integer>();
		List<String> s2 = new MyList<String, String>();
		
		// 타입 추론
		Generics4.method(1, Arrays.asList(1,2,3)); 
		Generics4.<Integer>method(1, Arrays.asList(1,2,3));  // 타입 힌트를 줄 수 도 있다.
		
		List<String> c1 = Collections.emptyList();
		List<String> c2 = Collections.<String>emptyList();
	}
	
	static class MyList<E, P> implements List<E> {

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean add(E e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public E get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E set(int index, E element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void add(int index, E element) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public E remove(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int indexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ListIterator<E> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	static <T> void method(T t, List<T> list) {
		
	}
}
