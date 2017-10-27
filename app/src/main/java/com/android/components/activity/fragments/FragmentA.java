package com.android.components.activity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.components.R;
import com.android.components.utils.UIUtil;


public class FragmentA extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        UIUtil.showToast(getActivity(), "Fragment:: onAttach(...)");

        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtil.showToast(getActivity(), "Fragment:: onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UIUtil.showToast(getActivity(), "Fragment:: onCreateView(...)");
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtil.showToast(getActivity(), "Fragment:: onActivityCreated(...)");
    }

    @Override
    public void onResume() {
        super.onResume();
        UIUtil.showToast(getActivity(), "Fragment:: onResume()");
        if (mListener != null) {
            mListener.onFragmentInteraction("FragmentA");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        UIUtil.showToast(getActivity(), "Fragment:: onPause()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        UIUtil.showToast(getActivity(), "Fragment:: onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UIUtil.showToast(getActivity(), "Fragment:: onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        UIUtil.showToast(getActivity(), "Fragment:: onDetach()");
        mListener = null;
    }
}
