package pl.adamwitowski.user;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.adamwitowski.chat.AbstractRemoteService;
import pl.adamwitowski.chat.message.Message;

@Service
public class UserRemoteService extends AbstractRemoteService {
	
	private static final String USER_REMOTE_URL = "/api/user";
	
	
	public List<String> getAllUsernames(){
		HttpHeaders headers = getDefaultHeaders();
		URI url = prepareUrl(USER_REMOTE_URL);
		RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, url);
		ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<String>>() {
			
		});
		return response.getBody();
	}
	
	
	
	

	
	

}
