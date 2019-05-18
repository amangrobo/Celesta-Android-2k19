package in.org.celesta.iitp.events;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.org.celesta.iitp.R;

public class EventDayFragment extends Fragment {

    private static final String ARG_PARAM1 = "day";

    private int day;
    private EventsRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private EventsViewModel viewModel;

    public EventDayFragment() {
    }

    public static EventDayFragment newInstance(int day) {
        EventDayFragment fragment = new EventDayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            day = getArguments().getInt(ARG_PARAM1);
        }

        viewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_day, container, false);

        recyclerView = view.findViewById(R.id.rv_feed_single_day);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EventsRecyclerAdapter(getContext(), (EventsRecyclerAdapter.OnFeedSelectedListener) getActivity());
        recyclerView.setAdapter(adapter);

        observeAll();

        return view;
    }

    private void observeAll() {
        List<EventItem> newList = viewModel.loadAllEvents(day);
        adapter.setEventItemList(newList);

        if (newList.size() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
//            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
//            emptyView.setVisibility(View.INVISIBLE);
        }
    }
}
