package pl.adamwitowski.chat.message;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	MessageRemoteService messageRemoteService;
	
	@Autowired
	private MessageDao messageDao;
	
	public void create(Message message){
		messageDao.create(message);
	}
	
	public List<Message> getAllMessagesFromUsername(String username){
		return messageDao.getAllMessagesFromUsername(username);
	}
	
	public Message createMessageOnRemoteServer(Message message){
		return messageRemoteService.createMessageOnRemoteServer(message);
	}
	
	public void saveAllMessageFromRemoteServer(){
		List<Message> messages = messageRemoteService.getAllMessagesFromRemoteServer();
		for(Message message: messages){
			create(message);
		}
	}
	

}
