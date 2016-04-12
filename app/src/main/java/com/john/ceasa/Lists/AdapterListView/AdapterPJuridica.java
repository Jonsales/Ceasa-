package com.john.ceasa.Lists.AdapterListView;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.android.volley.Request;
import com.john.ceasa.Lists.ItemListView.*;
import com.john.ceasa.Utils.*;
import com.john.ceasa.Lists.AdapterListView.*;
import com.john.ceasa.R;
import com.john.ceasa.VolleyConection.*;

import java.util.ArrayList;

/**
 * Created by paulo on 08/01/16.
 */
public class AdapterPJuridica extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> values = new ArrayList<>();
    private ArrayList<ItemPJuridica> list = new ArrayList<>();
    View rowView;
    public static ArrayList<EditText> listaTelefones = new ArrayList<>();
    public static ArrayList<EditText> listaEmails = new ArrayList<>();
    LinearLayout linearTelefones;
    LinearLayout linearEmails;

    TextView addTel;
    TextView addEmail;
    public static EditText nome, cnpj, tel,mail,cep, end, bairro, numero, compl;
    public static Spinner uf, city;

    public AdapterPJuridica(Context context, ArrayList<String> values, ArrayList<ItemPJuridica> list) {
        super(context, R.layout.item_list_pessoa_juridica, values);
        this.context = context;
        this.values = values;
        this.list = list;
    }
    private void declarate() {
        linearTelefones = (LinearLayout)rowView.findViewById(R.id.telefones);
        linearEmails = (LinearLayout)rowView.findViewById(R.id.emails);
        addTel = (TextView)rowView.findViewById(R.id.addTel);
        addEmail = (TextView)rowView.findViewById(R.id.addEmail);
        nome = (EditText)rowView.findViewById(R.id.nome);
        cnpj = (EditText)rowView.findViewById(R.id.cnpj);
        tel = (EditText)rowView.findViewById(R.id.tel);
        mail = (EditText)rowView.findViewById(R.id.mail);
        cep = (EditText)rowView.findViewById(R.id.cep);
        end = (EditText)rowView.findViewById(R.id.end);
        bairro = (EditText)rowView.findViewById(R.id.bairro);
        numero = (EditText)rowView.findViewById(R.id.numero);
        compl = (EditText)rowView.findViewById(R.id.comp);
        uf = (Spinner)rowView.findViewById(R.id.uf);
        city = (Spinner)rowView.findViewById(R.id.city);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.item_list_pessoa_juridica, parent, false);

        declarate();
        masks();

        addTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTel(listaTelefones.size() + 1);
            }
        });
        addEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMail(listaEmails.size() + 1);
            }
        });


        return rowView;
    }

    private void masks() {
        MaskEditText maskCnpj = new MaskEditText("##.###.###/####-##", cnpj);
        cnpj.addTextChangedListener(maskCnpj);

        MaskEditText maskTel = new MaskEditText("(##)#########", tel);
        tel.addTextChangedListener(maskTel);
    }


    public void insertTel(int id){
        EditText editText = new EditText(context);
        editText.setId(id);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 15, 0, 0);

        editText.setLayoutParams(params);
        editText.setHint("Telefone " + (id+1));
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
        listaTelefones.add(editText);
        linearTelefones.addView(editText);
    }

    public void insertMail(int id){
            EditText editText = new EditText(context);
            editText.setId(id);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 15, 0, 0);

            editText.setLayoutParams(params);
            editText.setHint("Email " + (id+1));
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            listaEmails.add(editText);
            linearEmails.addView(editText);
        }





}
