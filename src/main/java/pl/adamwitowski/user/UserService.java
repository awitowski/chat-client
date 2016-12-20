package pl.adamwitowski.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRemoteService userRemoteService;
	
	@Value("${auth.username}")
	private String authenticatedUsername;
	
	public String getAuthenticatedUsername(){
		return authenticatedUsername;
	}
	
	public List<String> getAllUsernamesWithoutAuthenticatedUsername(){
		List<String> users = userRemoteService.getAllUsernames();
		users.remove(authenticatedUsername);
		return users;
	
	}
	
}
