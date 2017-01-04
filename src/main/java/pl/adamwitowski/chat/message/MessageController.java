package pl.adamwitowski.chat.message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageRemoteService messageRemoteService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Message> getMessages(@RequestParam String username) {
		messageService.saveAllMessageFromRemoteServer();
		return messageService.getAllMessagesFromUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteMessage(@RequestParam Integer id){
		messageService.delete(id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Message createMessage(@RequestBody Message inputMessage) {
		Message message = messageService.createMessageOnRemoteServer(inputMessage);
		messageService.create(message);
		return inputMessage;
		
	}

}
