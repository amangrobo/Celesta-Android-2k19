package in.org.celesta.iitp.events;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import in.org.celesta.iitp.R;

public class EventsFragment extends Fragment {

    public EventsFragment() {
    }

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_events);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                updateData();
            }
        });

        TabLayout tabLayout = view.findViewById(R.id.events_tab);
        ViewPager viewPager = view.findViewById(R.id.events_view_pager);

        EventsPagerAdapter adapter = new EventsPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        swipeRefreshLayout.setBackgroundResource(R.color.darkblue);

        return view;
    }

//    private void updateData() {
//
//        long latest = feedViewModel.getMaxEventId();
//        Log.e("maxid", String.valueOf(latest));
//
//        String token = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(USER_TOKEN, "0");
//        Log.e("token", token);
//
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//
//        Call<EventItem.FeedItemSuper1> call = service.getNewFeed(token, latest);
//        call.enqueue(new Callback<EventItem.FeedItemSuper1>() {
//            @Override
//            public void onResponse(Call<EventItem.FeedItemSuper1> call, Response<EventItem.FeedItemSuper1> response) {
//
//                if (response.isSuccessful()) {
//                    List<EventItem> allItems = response.body().getLatestFeeds();
//
//                    for (EventItem newItem : allItems) {
//                        if (feedViewModel.getFeedCount(newItem.getId()) == 0)
//                            feedViewModel.insert(newItem);
//                        Log.e("feed", newItem.getEventName());
//                    }
//
//                }
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<EventItem.FeedItemSuper1> call, Throwable t) {
//                Log.e("failure", t.getMessage());
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//
//
//    }
//

    public class EventsPagerAdapter extends FragmentPagerAdapter {

        public EventsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return EventDayFragment.newInstance(position + 1);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Day 1";
                case 1:
                    return "Day 2";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
