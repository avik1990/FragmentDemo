package com.fragdemo.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fragdemo.app.pojo.User;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;


public class BlankFragment2 extends Fragment implements View.OnClickListener {
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
    SendMessage SM;
    Button btn_senddata;
    EditText et_getinput;

    public BlankFragment2() {
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
        View view = inflater.inflate(R.layout.fragment_blank_2, container, false);
        tv_value = view.findViewById(R.id.tv_value);
        btn_senddata = view.findViewById(R.id.btn_senddata);
        et_getinput = view.findViewById(R.id.et_getinput);
        btn_senddata.setOnClickListener(this);
        mContext = getActivity();
        this.setRetainInstance(true);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_senddata) {
            if (!et_getinput.getText().toString().trim().isEmpty()) {
                MainActivity.vPager.setCurrentItem(2);
                SM.sendData(et_getinput.getText().toString().trim());
            }
        }
    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            SM = (SendMessage) context;
        } catch (Exception e) {
           // e.printStackTrace();
             throw new ClassCastException("Error in retrieving data. Please try again");
        }
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

    @Produce
    public User produceEvent() {
        return new User();
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
}
