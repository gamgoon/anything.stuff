package kr.pe.gamgoon.hermetization;

import java.util.Arrays;
import java.util.List;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    		SpringApplication.run(App.class, args);
    }
    
    @RestController
    public static class Controller {
    		@RequestMapping("/hello")
    		public Publisher<String> hello(String name) {
    			return new Publisher<String>() {

					@Override
					public void subscribe(Subscriber<? super String> s) {
						s.onSubscribe(new Subscription() {

							@Override
							public void request(long n) {
								s.onNext("Hello " + name);
								s.onComplete();
							}

							@Override
							public void cancel() {
								
							}
							
						});
					}
    				
    			};
    		}
    }
    
    
    
    @RestController
    public static class MyController {
    	
    		@RequestMapping("/")
    		public List<User> users() {
    			return Arrays.asList(new User("A"), new User("B"), new User("C"));
    		}
    }
    
    public static class User {
    		String name;
    			public User() {}
			public User(String name) {
				super();
				this.name = name;
			}

			@Override
			public String toString() {
				return "User [name=" + name + "]";
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
    		
    }
}
