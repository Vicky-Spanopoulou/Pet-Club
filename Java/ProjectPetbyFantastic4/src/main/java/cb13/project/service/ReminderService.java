package cb13.project.service;

import cb13.project.entities.Reminder;
import cb13.project.entities.User;

import java.util.List;

public interface ReminderService {

    List<Reminder> findAllReminders();
    
    List<Reminder> findAllRemindersByUser(User user);
    
    Reminder findById(Long id);

    Reminder saveReminder(Reminder reminder);


    Reminder updateReminder(Reminder reminder);

    void deleteReminderById(Long reminderId);


}
