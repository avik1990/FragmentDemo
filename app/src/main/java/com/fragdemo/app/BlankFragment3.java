package com.fragdemo.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fragdemo.app.pojo.User;
import com.squareup.otto.Subscribe;


public class BlankFragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context mContext;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tv_value;
    private OnFragmentInteractionListener mListener;

    public BlankFragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_3, container, false);
        tv_value = view.findViewById(R.id.tv_value);
        mContext = getActivity();
        this.setRetainInstance(true);
        return view;
    }


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBusInit.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBusInit.getBus().unregister(this);
    }

    @Subscribe
    public void getResult(User switchboard) {
        //Toast.makeText(mContext, switchboard.getCountry(), Toast.LENGTH_SHORT).show();
        try {
            if (switchboard != null) {
                Log.d("CurrentFragmentValue", switchboard.getCountry());
                tv_value.setText(switchboard.getCountry());
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    protected void displayReceivedData(String message) {
        tv_value.setText("Data received: " + message);
    }
}
