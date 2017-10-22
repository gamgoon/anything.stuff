package kr.pe.gamgoon.hermetization.toby.supertypetoken;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import kr.pe.gamgoon.hermetization.App.User;

public class SpringTypeReference {
	public static void main(String[] args) {
		// spring 3.2 이상에서 사용 가능
		ParameterizedTypeReference type = new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>() {
		};
		// {} 바디를 주는 건, 수퍼타입 토큰이고, 익명클래스의 인스턴스를 만들어 익명클래스가 상속하고있는 수퍼클래스의 재내릭 타입 파라미터 정보를 전달하기 위한 용도.
		System.out.println(type.getType());
		
		RestTemplate rt = new RestTemplate();
//		List<Map> users = rt.getForObject("http://localhost:8080", List.class); // String.class  요런걸 타입토큰 이라고 한다. restTemplate 은 <User> 타입을 알수 없다. 그래서 가장 근접한 LinkedHashMap 으로 바꾼다.
		List<User> users = rt.exchange("http://localhost:8080", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {}).getBody();
		users.forEach(System.out::println);
	}
}
