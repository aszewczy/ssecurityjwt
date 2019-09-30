package pl.szewczyk.ssecurityjwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {   //Tworze proste Api na potrzeby testów

	
	 
	@GetMapping("/test1")   //dostępna dla wszystkich
	public String test1() {
		return "test1";
	}
	
	
	@GetMapping("/test2")   //dostępna tylko dla zalogowanych użytkowników
	public String test2() {
		return "test2";
	}
	
	@GetMapping("/test3")  //  Tylko dla użytkowników o określonej roli
	public String test3() {
		return "test3";
	}
	
}
