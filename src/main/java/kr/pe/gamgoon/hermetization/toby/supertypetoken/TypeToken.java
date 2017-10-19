package kr.pe.gamgoon.hermetization.toby.supertypetoken;

public class TypeToken {
	
	static <T> T create(Class<T> clazz) throws Exception {
		return clazz.newInstance();
	}
	
	public static void main(String[] args) throws Exception {
		Object o = create(Object.class);
		System.out.println(o.getClass());
		
		// o = create(Integer.class);
		// System.out.println(o.getClass()); // integer 에는 디폴트 생성자가 없다. clazz.newInstance() 로 생성할 수 없다. 
	}
}
