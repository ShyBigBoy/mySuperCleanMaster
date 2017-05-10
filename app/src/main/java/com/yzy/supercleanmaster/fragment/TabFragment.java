package com.yzy.supercleanmaster.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    private TextView textView;
    private String title;

    public static TabFragment newInstance(int index){
        Bundle bundle = new Bundle();
        bundle.putInt("index", 'A' + index);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, null);
        textView = (TextView) view.findViewById(R.id.text);
        title = String.valueOf((char) getArguments().getInt("index"));
        textView.setText(String.valueOf((char) getArguments().getInt("index")));

        Log.i("CleanMaster", "TabFragment.onCreateView-" + title);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("CleanMaster", "TabFragment.onDestroyView-" + title);
    }
}
