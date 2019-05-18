package in.org.celesta.iitp.events;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import in.org.celesta.iitp.database.AppDatabase;

public class EventsRepository {

    private EventsDao eventsDao;

    EventsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        eventsDao = db.eventsDao();
    }

    List<EventItem> loadAllEvents(int day) {
        getAsyncTask task = new getAsyncTask(eventsDao);
        try {
            return task.execute(day).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(EventItem eventItem) {
        new insertAsyncTask(eventsDao).execute(eventItem);
    }

    EventItem loadEventById(String id) {
        getEventById task = new getEventById(eventsDao);
        try {
            return task.execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getAsyncTask extends AsyncTask<Integer, Void, List<EventItem>> {
        private EventsDao mAsyncTaskDao;

        getAsyncTask(EventsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<EventItem> doInBackground(Integer... params) {
            return mAsyncTaskDao.loadAllEvents(params[0]);
        }
    }

    private static class insertAsyncTask extends AsyncTask<EventItem, Void, Void> {
        private EventsDao mAsyncTaskDao;

        insertAsyncTask(EventsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EventItem... params) {
            mAsyncTaskDao.insertEvent(params[0]);
            return null;
        }
    }

    private static class getEventById extends AsyncTask<String, Void, EventItem> {
        private EventsDao mAsyncTaskDao;

        getEventById(EventsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected EventItem doInBackground(String... params) {
            return mAsyncTaskDao.loadEventById(params[0]);
        }
    }

}
