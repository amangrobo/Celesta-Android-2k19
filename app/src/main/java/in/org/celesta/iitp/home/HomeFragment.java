package in.org.celesta.iitp.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.org.celesta.iitp.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    OnItemSelectedListener callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        View myView = view.findViewById(R.id.main_pronite_ll);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the final radius for the clipping circle
//                int dx = Math.max(cx, v.getWidth() - cx);
//                int dy = Math.max(cy, v.getHeight() - cy);
//                float finalRadius = (float) Math.hypot(dx, dy);

                callback.onItemSelected(v, new HomeFragment());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            callback = (OnItemSelectedListener) context;
        }
    }

    interface OnItemSelectedListener {
        void onItemSelected(View view, Fragment newFragment);
    }

}
