package cb13.project.service.impl;

import cb13.project.entities.Reminder;
import cb13.project.entities.User;
import cb13.project.repository.ReminderRepository;
import cb13.project.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Override
    public List<Reminder> findAllReminders() {
        return  reminderRepository.findAll();
    }

    @Override
    public Reminder saveReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @Override
    public Reminder updateReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @Override
    public void deleteReminderById(Long reminderId) {
     reminderRepository.deleteById(reminderId);
    }

    @Override
    public Reminder findById(Long id) {
        return reminderRepository.getById(id);
    }

    @Override
    public List<Reminder> findAllRemindersByUser(User user) {
        return reminderRepository.findReminderByUser(user);
    }
}
