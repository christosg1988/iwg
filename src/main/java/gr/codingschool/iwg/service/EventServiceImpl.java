package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Event;
import gr.codingschool.iwg.model.User;
import gr.codingschool.iwg.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findHistoryByUser(User user) {
        return eventRepository.findAllByUser(user);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public int delete(int id) {
        return eventRepository.deleteById(id);
    }
}
