package pl.adamwitowski.chat.message;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public void delete(Integer id){
		messageDao.delete(id);
	}
	
	public List<Message> getAllMessagesFromUsername(String username){
		List<Message> messages = messageDao.getAllMessagesFromUsername(username);
//		for(Message message: messages){
//			message.setNewMessage(false);
//			messageDao.update(message);
//		}
		return messages;
	}
	
	public Message createMessageOnRemoteServer(Message message){
		return messageRemoteService.createMessageOnRemoteServer(message);
	}
	
	public List<String> saveAllMessagesFromRemoteServer(){
		List<Message> messages = messageRemoteService.getAllMessagesFromRemoteServer();
		for(Message message: messages){
			message.setNewMessage(true);
			create(message);
		}
		return messages.stream().map(Message::getSenderId).collect(Collectors.toList());
	}
	
	public List<Message> getAllNewMessagesFromUsername(String username){
		return messageDao.getAllNewMessagesFromUsername(username);
	}
	
	public List<String> getAllUsersWithNewMessages(){
		return messageDao.getAllUsersWithNewMessages();
	}
	 public void changeNewMessageColumnValue(Integer id){
		 messageDao.changeNewMessageColumnValue(id);
	 }

}
