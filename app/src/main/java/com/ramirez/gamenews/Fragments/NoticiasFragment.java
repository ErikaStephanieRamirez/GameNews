package com.ramirez.gamenews.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramirez.gamenews.R;
import com.ramirez.gamenews.adapter.AdapterNews;
import com.ramirez.gamenews.adapter.AdapterPlayers;
import com.ramirez.gamenews.repository.db.NewsViewModel;
import com.ramirez.gamenews.repository.db.PlayerViewModel;
import com.ramirez.gamenews.repository.modelos.New;
import com.ramirez.gamenews.repository.modelos.Players;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoticiasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticiasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticiasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //valores
    private RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private String category;

    private OnFragmentInteractionListener mListener;

    public NoticiasFragment() {
        // Required empty public constructor
    }

    /*/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticiasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticiasFragment newInstance(String category) {
        NoticiasFragment fragment = new NoticiasFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_principal, container, false);
        recyclerView = v.findViewById(R.id.recycler);

        if(category.equals("lol")){
            setLolNews();
        }else if (category.equals("overwatch")){
            setOverwatchNews();
        }else if (category.equals("csgo")){
            setCsgoNews();
        }
        return v;
    }

    private void setLolNews(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        final AdapterNews adapterNews = new AdapterNews(getContext());

        recyclerView.setAdapter(adapterNews);
        recyclerView.setLayoutManager(gridLayoutManager);

        newsViewModel.getLolnews().observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> news) {
                adapterNews.setNews(news);
            }
        });
    }

    private void setOverwatchNews(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        final AdapterNews adapterNews = new AdapterNews(getContext());

        recyclerView.setAdapter(adapterNews);
        recyclerView.setLayoutManager(gridLayoutManager);

        newsViewModel.getOverwatchnews().observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> news) {
                adapterNews.setNews(news);
            }
        });
    }

    private void setCsgoNews(){
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        final AdapterNews adapterNews = new AdapterNews(getContext());

        recyclerView.setAdapter(adapterNews);
        recyclerView.setLayoutManager(gridLayoutManager);

        newsViewModel.getCsgonews().observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> news) {
                adapterNews.setNews(news);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
