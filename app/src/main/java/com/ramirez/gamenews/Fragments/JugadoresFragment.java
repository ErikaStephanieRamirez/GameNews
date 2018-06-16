package com.ramirez.gamenews.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramirez.gamenews.R;
import com.ramirez.gamenews.adapter.AdapterPlayers;
import com.ramirez.gamenews.repository.db.PlayerViewModel;
import com.ramirez.gamenews.repository.modelos.Players;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JugadoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JugadoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JugadoresFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    //valores
    private RecyclerView recyclerView;
    private AdapterPlayers adapter;
    private LinearLayoutManager linearLayoutManager;
    private PlayerViewModel playersViewModel;
    private String category;

    private OnFragmentInteractionListener mListener;

    public JugadoresFragment() {
        // Required empty public constructor
    }
/*
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JugadoresFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static JugadoresFragment newInstance(String category) {
        JugadoresFragment fragment = new JugadoresFragment();
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

        View v = inflater.inflate(R.layout.activity_juegos, container, false);
        recyclerView = v.findViewById(R.id.recycleview_players);

        if(category.equals("lol")){
            setLolplayers();
        }else if (category.equals("overwatch")){
            setOverwatchplayers();
        }else if (category.equals("csgo")){
            setCsgoplayers();
        }
        return v;
    }

    private void setLolplayers(){
        playersViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playersViewModel.getLolPlayers().observe(this, new Observer<List<Players>>() {
            @Override
            public void onChanged(@Nullable List<Players> players) {
                adapter = new AdapterPlayers((ArrayList<Players>)players,getContext());
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);

            }
        });
    }

    private void setOverwatchplayers(){
        playersViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playersViewModel.getOverwatchPlayes().observe(this, new Observer<List<Players>>() {
            @Override
            public void onChanged(@Nullable List<Players> players) {
                adapter = new AdapterPlayers((ArrayList<Players>)players,getContext());
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void setCsgoplayers(){
        playersViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        playersViewModel.getCsgoPlayer().observe(this, new Observer<List<Players>>() {
            @Override
            public void onChanged(@Nullable List<Players> players) {
                adapter = new AdapterPlayers((ArrayList<Players>)players,getContext());
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
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
