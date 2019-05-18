package in.org.celesta.iitp.events;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class EventsViewModel extends AndroidViewModel {

    private EventsRepository eventsRepository;

    public EventsViewModel(@NonNull Application application) {
        super(application);
        eventsRepository = new EventsRepository(application);
    }

    List<EventItem> loadAllEvents(int day) {
        return eventsRepository.loadAllEvents(day);
    }

    public void insert(EventItem eventItem) {
        eventsRepository.insert(eventItem);
    }

    EventItem getEventById(String id) {
        return eventsRepository.loadEventById(id);
    }

}
