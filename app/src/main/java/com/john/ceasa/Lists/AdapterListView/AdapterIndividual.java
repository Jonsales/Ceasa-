package com.john.ceasa.Lists.AdapterListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
public class AdapterIndividual extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> values = new ArrayList<>();
    private ArrayList<ItemIndividual> list = new ArrayList<>();
    View rowView;

    public static TextView data, nome, inicial, email, produto, qtd, unitario, total;

    public AdapterIndividual(Context context, ArrayList<String> values, ArrayList<ItemIndividual> list) {
        super(context, R.layout.item_list_venda_individual, values);
        this.context = context;
        this.values = values;
        this.list = list;


    }

    private void declarate() {
        data = (TextView)rowView.findViewById(R.id.data);
        nome = (TextView)rowView.findViewById(R.id.nome);
        inicial = (TextView)rowView.findViewById(R.id.inicial);
        produto = (TextView)rowView.findViewById(R.id.produto);
        qtd = (TextView)rowView.findViewById(R.id.qtd);
        unitario = (TextView)rowView.findViewById(R.id.unitario);
        total = (TextView)rowView.findViewById(R.id.total);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.item_list_venda_individual, parent, false);

        declarate();
        data.setText(list.get(position).getData());
        nome.setText(list.get(position).getNome());
        inicial.setText(list.get(position).getNome().substring(0,1).toUpperCase());
        produto.setText(list.get(position).getProduto());
        qtd.setText(list.get(position).getQtd());
        unitario.setText(list.get(position).getValorUnitario());
        total.setText(list.get(position).getTotal());

        return rowView;
    }




}
