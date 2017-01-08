package pl.adamwitowski.user;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.adamwitowski.chat.message.Message;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String getAuthenticatedUsername() {
		return userService.getAuthenticatedUsername();
	}
	
	@RequestMapping(method = RequestMethod.GET)
		public List<String> getAllUsers(){
			return userService.getAllUsernamesWithoutAuthenticatedUsername();
		}
	
	
	}
	


