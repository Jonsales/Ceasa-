package com.john.ceasa.Lists.AdapterListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.john.ceasa.ClienteActivity;
import com.john.ceasa.Lists.ItemListView.*;
import com.john.ceasa.R;

import java.util.ArrayList;

/**
 * Created by paulo on 08/01/16.
 */
public class AdapterCliente  extends ArrayAdapter<String> {
    private final Context context;
    public static ArrayList<String> values = new ArrayList<>();
    public static ArrayList<ItemCliente> list = new ArrayList<>();

    TextView inicial;
    TextView nome;
    TextView email;
    TextView profissao;
    LinearLayout contato;

    public AdapterCliente(Context context, ArrayList<String> values, ArrayList<ItemCliente> list) {
        super(context, R.layout.item_list_clientes, values);
        this.context = context;
        this.values = values;
        this.list = list;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_list_clientes, parent, false);

        inicial = (TextView)rowView.findViewById(R.id.inicial);
        nome = (TextView)rowView.findViewById(R.id.nome);
        email = (TextView)rowView.findViewById(R.id.email);
        contato = (LinearLayout)rowView.findViewById(R.id.contato);

        inicial.setText(list.get(position).getNome().substring(0, 1).toUpperCase());
        nome.setText(list.get(position).getNome());
        email.setText(list.get(position).getEmail());

        contato.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClienteActivity.idCliente = values.get(position);
                ClienteActivity.positionClient = position;
                ClienteActivity.frameExcluirCLiente.setVisibility(View.VISIBLE);
                return false;
            }
        });
        return rowView;
    }



}
