package pl.adamwitowski.chat.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(method = RequestMethod.GET)
	public List<Message> getMessages(@RequestParam String username) {
		
		return messageService.getAllMessagesFromUsername(username);
	}
	
	@RequestMapping(path = "/new_from_remote", method = RequestMethod.GET)
	public List<String> saveMessagesFromRemoteServer(){
		return messageService.saveAllMessagesFromRemoteServer();
	}
	
	@RequestMapping(path = "/new_from_user", method = RequestMethod.GET)
	public List<Message> getNewMessages(@RequestParam String username){
		return messageService.getAllNewMessagesFromUsername(username);
	}
	
	@RequestMapping(path = "/users_with_new_message", method = RequestMethod.GET)
	public List<String> getUsersWithNewMessages(){
		return messageService.getAllUsersWithNewMessages();
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteMessage(@RequestParam Integer id){
		messageService.delete(id);
	}
	
	@RequestMapping(path="/false", method = RequestMethod.PUT)
	public void changeFlagNewMessageOnFalse(@RequestParam Integer id){
		messageService.changeNewMessageColumnValue(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Message createMessage(@RequestBody Message inputMessage) {
		Message message = messageService.createMessageOnRemoteServer(inputMessage);
//		messageService.create(message);
		return inputMessage;
		
	}

}
