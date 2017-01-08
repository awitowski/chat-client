package pl.adamwitowski.chat.message;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private int id;

	private String receiverId;

	private String senderId;

	@Column(unique = true)
	private String messageId;
	
	private boolean newMessage;
	
	private String body;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public boolean isNewMessage() {
		return newMessage;
	}

	public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", receiverId=" + receiverId + ", senderId=" + senderId + ", body=" + body
				+ ", created=" + created + "]";
	}

}
