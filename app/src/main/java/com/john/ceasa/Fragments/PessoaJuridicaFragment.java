package com.john.ceasa.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;

import com.john.ceasa.Lists.ItemListView.*;
import com.john.ceasa.Utils.*;
import com.john.ceasa.Lists.AdapterListView.*;
import com.john.ceasa.R;
import com.john.ceasa.VolleyConection.*;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PessoaJuridicaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PessoaJuridicaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FloatingActionButton fab;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView list;
    ArrayList<ItemPJuridica> arrayListItens = new ArrayList<>();
    ArrayList<String> arrayvalues = new ArrayList<>();
    static Activity PFisicaActivity;

    private OnFragmentInteractionListener mListener;
    Valida valida = new Valida();


    public PessoaJuridicaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PessoaFisicaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PessoaJuridicaFragment newInstance(String param1, String param2) {
        PessoaJuridicaFragment fragment = new PessoaJuridicaFragment();
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
        View view = inflater.inflate(R.layout.fragment_pessoa_juridica, container, false);

        PFisicaActivity = getActivity();

        list = (ListView)view.findViewById(R.id.list_Pjuridica);

        fab = (FloatingActionButton)view.findViewById(R.id.fabJuridica);

        /*fab.attachToListView(list, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrollUp() {

            }
        }, new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (list.getChildAt(0).getTop() == 0)
                    fab.show(true);
                else
                    fab.hide(true);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });*/

        inflateList();

        return view;
    }

    public void inflateList(){

        arrayListItens.add(new ItemPJuridica(1));
        arrayvalues.add("" + 1);
        AdapterPJuridica listAdapter = new AdapterPJuridica(getActivity(), arrayvalues, arrayListItens);

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
    /**************************** começo metodos click **************************/
    public void fbButtonClick(Activity activity) {
        validateForms();
    }
    /**************************** fim metodos click **************************/
    private void validateForms() {
        if (valida.formEmptyJuridico())
            Alerts.menssage("Aviso!", "Campos vazios", AllActivits.adicionarClienteActivity);
        else if (!valida.validEditMailJuridico())
            Alerts.menssage("Aviso!", "Email inválido", AllActivits.adicionarClienteActivity);
        else if (!valida.validEditTelJuridico())
            Alerts.menssage("Aviso!", "Telefone inválido", AllActivits.adicionarClienteActivity);
        else if (valida.isCNPJ(String.valueOf(AdapterPJuridica.cnpj.getText())))
            Alerts.menssage("Aviso!", "CPF inválido", AllActivits.adicionarClienteActivity);
        else
            sendClient();
    }

    private void sendClient() {
        Connection task = new Connection(Constants.URLclients, Request.Method.POST, "sendClient",AllActivits.adicionarClienteActivity, mountDataToPost());
        task.callByJsonStringRequest();
    }

    private HashMap<String, String> mountDataToPost() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", String.valueOf(AdapterPJuridica.nome.getText()));
        params.put("identify", ((String.valueOf(AdapterPJuridica.cnpj.getText()).replace(".","")).replace("-","")).replace("/", ""));
        params.put("type", "legal");//pessoa juridica
        params.put("phone", String.valueOf(getTelefones()));
        params.put("email", String.valueOf(getEmails()));
        params.put("cep", String.valueOf(AdapterPJuridica.cep.getText()));
        params.put("address", String.valueOf(AdapterPJuridica.end.getText()));
        params.put("neighborhood", String.valueOf(AdapterPJuridica.bairro.getText()));
        params.put("number", String.valueOf(AdapterPJuridica.numero.getText()));
        params.put("complement", String.valueOf(AdapterPJuridica.compl.getText()));
        params.put("uf", "SP"/*AdapterPJuridica.uf.getSelectedItem().toString()*/);
        params.put("city","SP"/*AdapterPJuridica.city.getSelectedItem().toString()*/);

        return params;
    }

    private HashMap<String,String> getTelefones() {
        HashMap<String, String> telefones = new HashMap<>();
        telefones.put("telefone0", String.valueOf(AdapterPJuridica.tel.getText()));
        for (EditText e : AdapterPJuridica.listaTelefones){
            telefones.put("telefone"+e.getId(), String.valueOf(e.getText()));
        }
        return telefones;
    }
    private HashMap<String,String> getEmails() {
            HashMap<String, String> emails = new HashMap<>();
            emails.put("email0", String.valueOf(AdapterPJuridica.mail.getText()));
        for (EditText e : AdapterPJuridica.listaEmails){
                emails.put("email"+e.getId(), String.valueOf(e.getText()));
            }
            return emails;
        }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
