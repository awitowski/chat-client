package pl.adamwitowski.chat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Controller {
	
	@RequestMapping(value="/say", method =RequestMethod.GET)
	public String hello(){
		return " John";
	}
}
