package pl.adamwitowski.chat.message;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(Message message){
		em.persist(message);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getAllMessagesFromUsername(String username){
		return em.createNativeQuery("SELECT * FROM MESSAGE WHERE RECEIVER_ID = ? OR SENDER_ID = ?;", Message.class)
				.setParameter(1, username)
				.setParameter(2, username)
				.getResultList();
	}
}
