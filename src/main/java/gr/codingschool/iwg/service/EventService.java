package gr.codingschool.iwg.service;

import gr.codingschool.iwg.model.Event;
import gr.codingschool.iwg.model.User;

import java.util.List;

public interface EventService {
    List<Event> findHistoryByUser(User user);
    Event save(Event event);
    int delete(int id);
}
