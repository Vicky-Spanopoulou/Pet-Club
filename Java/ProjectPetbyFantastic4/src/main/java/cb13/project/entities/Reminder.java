package cb13.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Size;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "reminders")
@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 500)
    @Column(name="info")
    private  String info;

    @Column(name="done")
    private boolean done;

    @JsonBackReference(value="user-reminders")
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Reminder() {
    }

    public Reminder(String info, boolean done) {
        this.info = info;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reminder{id=").append(id);
        sb.append(", info=").append(info);
        sb.append(", done=").append(done);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

    

}