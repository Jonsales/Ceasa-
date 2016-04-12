package com.john.ceasa.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.john.ceasa.Lists.ItemListView.*;
import com.john.ceasa.Utils.*;
import com.john.ceasa.Lists.AdapterListView.*;
import com.john.ceasa.R;
import com.john.ceasa.VolleyConection.*;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IndividualFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndividualFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View rowView;

    static ArrayList<ItemIndividual> arrayListItens = new ArrayList<>();
    static ArrayList<String> arrayvalues = new ArrayList<>();
    protected static ListView list;

    ImageButton btnInicio, btnFim;
    public boolean dataInicioPressed = false;

    private OnFragmentInteractionListener mListener;

    private CalendarView calendarView;
    private Data dataAntiga = new Data(16, 1, 2016);
    private Data dataInicio;
    private Data dataFim;
    private boolean first = true;

    public IndividualFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndividualFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndividualFragment newInstance(String param1, String param2) {
        IndividualFragment fragment = new IndividualFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rowView = inflater.inflate(R.layout.fragment_individual, container, false);

        declarate();
        dataInicioOnClick();
        dataFimOnClick();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if (ChangeDate(dayOfMonth, month, year)) {
                    if (dataInicioPressed)
                        dataInicio = new Data(dayOfMonth, month, year);
                    else
                        dataFim = new Data(dayOfMonth, month, year);

                    ((FrameLayout) rowView.findViewById(R.id.frameCalendar)).setVisibility(View.GONE);

                    if (dataInicio != null && dataFim != null) {
                        callConnection();
                    }
                }

            }
        });

        return rowView;
    }

    private boolean ChangeDate(int dayOfMonth, int month, int year) {

        if (dataAntiga.getDia() != dayOfMonth || dataAntiga.getMes() != month || dataAntiga.getAno() != year || first){
            first = false;
            dataAntiga = new Data(dayOfMonth, month, year);
            return true;
        }
        else
            return false;
    }

    private void callConnection() {
        HashMap<String, String> params = mountData();
        Connection task = new Connection("url", Request.Method.GET, "getVendas", AllActivits.vendasActivity, params);
        task.callByJsonStringRequest();
    }

    private HashMap<String, String> mountData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("data_inicio", dataInicio.getDia() + "/" + dataInicio.getMes() + "/" + dataInicio.getAno());
        params.put("data_fim", dataFim.getDia() + "/" + dataFim.getMes() + "/" + dataFim.getAno());

        return params;
    }

    public static void inflateList(){

        for (int i = 0; i< 100; i++) {
            arrayListItens.add(new ItemIndividual("nome", "email", "profissao","2", "1", "2", "3", "4"));
            arrayvalues.add("id");
        }

        AdapterIndividual listAdapter = new AdapterIndividual(AllActivits.clienteActivity, arrayvalues, arrayListItens);
        list.setAdapter(listAdapter);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /********************************* inicio metodos de cick ****************************/
    public void dataInicioOnClick() {
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInicioPressed = true;
                openFrameCalendar();
            }
        });

    }

    public void dataFimOnClick() {
        btnFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInicioPressed = false;
                openFrameCalendar();
            }
        });

    }
    /********************************* fim metodos de cick ****************************/

    public void openFrameCalendar(){
        ((FrameLayout)rowView.findViewById(R.id.frameCalendar)).setVisibility(View.VISIBLE);
    }

    public static void responseConnection(String response) {
        Toast.makeText(AllActivits.vendasActivity, response, Toast.LENGTH_LONG).show();
        inflateList();
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

    private void declarate() {
        calendarView = (CalendarView)rowView.findViewById(R.id.calendarView);
        list = (ListView)rowView.findViewById(R.id.list_individual);
        btnInicio =  (ImageButton)rowView.findViewById(R.id.data_inicio);
        btnFim = (ImageButton)rowView.findViewById(R.id.data_fim);
    }
}
