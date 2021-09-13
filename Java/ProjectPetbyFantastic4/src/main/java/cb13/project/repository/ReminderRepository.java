package cb13.project.repository;

import cb13.project.entities.Reminder;
import cb13.project.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
    
    public List<Reminder> findReminderByUser(User user);
    
}
