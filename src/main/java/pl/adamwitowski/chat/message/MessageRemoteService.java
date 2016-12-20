package pl.adamwitowski.chat.message;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.adamwitowski.chat.AbstractRemoteService;

@Service
public class MessageRemoteService extends AbstractRemoteService {
	
	private static final String MESSAGE_REMOTE_URL = "/api/message";
	
	
	public List<Message> getAllMessagesFromRemoteServer(){
		HttpHeaders headers = getDefaultHeaders();
		URI url = prepareUrl(MESSAGE_REMOTE_URL);
		RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, url);
		ResponseEntity<List<Message>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Message>>() {	
		});
		
		return response.getBody();
	}
	
	public Message createMessageOnRemoteServer(Message message){
		HttpHeaders headers = getDefaultHeaders();
		URI url = prepareUrl(MESSAGE_REMOTE_URL);
		RequestEntity<Message> request = new RequestEntity<>(message, headers, HttpMethod.POST, url);
		ResponseEntity<Message> result = restTemplate.exchange(request, Message.class);
		return result.getBody();
	}
}
