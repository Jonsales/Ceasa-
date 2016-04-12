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
 * Created by paulo on 26/01/16.
 */
public class AdapterConsolidacao extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> values = new ArrayList<>();
    private ArrayList<ItemConsolidacao> list = new ArrayList<>();
    View rowView;

    public static TextView produto, qtd, unitario, total;

    public AdapterConsolidacao(Context context, ArrayList<String> values, ArrayList<ItemConsolidacao> list) {
        super(context, R.layout.item_list_venda_individual, values);
        this.context = context;
        this.values = values;
        this.list = list;


    }

    private void declarate() {
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
        produto.setText(list.get(position).getProduto());
        qtd.setText(list.get(position).getQtd());
        unitario.setText(list.get(position).getValorUnitario());
        total.setText(list.get(position).getTotal());

        return rowView;
    }




}
