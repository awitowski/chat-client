package pl.adamwitowski.chat.message;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {

	@PersistenceContext
	EntityManager em;

	@Value("${auth.username}")
	private String authenticatedUser;

	public void create(Message message) {
		em.persist(message);
	}
	
	public void update(Message message){
		em.merge(message);
	}

	public void delete(Integer id) {
		Message messageToDelete = em.find(Message.class, id);
		em.remove(messageToDelete);
	}

	@SuppressWarnings("unchecked")
	public List<Message> getAllMessagesFromUsername(String username) {
		return em.createNativeQuery("SELECT * FROM MESSAGE WHERE RECEIVER_ID = ? OR SENDER_ID = ?;", Message.class)
				.setParameter(1, username).setParameter(2, username).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getAllNewMessagesFromUsername(String username) {
		return em.createNativeQuery(
				"SELECT * FROM MESSAGE WHERE RECEIVER_ID = ? AND SENDER_ID = ? AND NEW_MESSAGE = 'T';", Message.class)
				.setParameter(1, authenticatedUser).setParameter(2, username).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllUsersWithNewMessages() {
		return em.createNativeQuery("SELECT SENDER_ID FROM MESSAGE WHERE RECEIVER_ID = ? AND NEW_MESSAGE = 'T'",
				Message.class).setParameter(1, authenticatedUser).getResultList();

	}
	


	public void changeNewMessageColumnValue(Integer id) {
		Query update = em.createQuery("UPDATE Message m SET m.newMessage='false' WHERE m.id = ?").setParameter(1, id);
		update.executeUpdate();
	}
}
