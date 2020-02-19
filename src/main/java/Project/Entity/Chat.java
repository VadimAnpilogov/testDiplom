package Project.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
//    private String recipient;//получатель
    private String sender;//отправитель
//    private String nameMess;
    public String date;
//    private boolean active;

    public Chat(){}

    public Chat(String message, String sender, String date) {
        this.message = message;
        this.sender = sender;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getRecipient() {
//        return recipient;
//    }
//
//    public void setRecipient(String recipient) {
//        this.recipient = recipient;
//    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
//
//    public String getNameMess() {
//        return nameMess;
//    }
//
//    public void setNameMess(String nameMess) {
//        this.nameMess = nameMess;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
