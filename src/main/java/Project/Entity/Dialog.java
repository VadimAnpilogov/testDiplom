package Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameMess;
    private String sender;
    private String recipient;


    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "MessDialog",
            joinColumns = {@JoinColumn(name = "DialogId")},
            inverseJoinColumns = {@JoinColumn(name = "MessageId")}
    )
    private Set<Messages> MessageDialogs = new HashSet<>();


    public Dialog() {
    }
    public Dialog(String nameMess, String sender, String recipient) {
        this.nameMess = nameMess;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Set<Messages> getMessageDialogs() {
        return MessageDialogs;
    }

    public void setMessageDialogs(Set<Messages> messageDialogs) {
        MessageDialogs = messageDialogs;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMess() {
        return nameMess;
    }

    public void setNameMess(String nameMess) {
        this.nameMess = nameMess;
    }
}
