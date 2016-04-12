package com.john.ceasa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.john.ceasa.Lists.AdapterListView.AdapterCliente;
import com.john.ceasa.Lists.ItemListView.ItemCliente;
import com.john.ceasa.Utils.Alerts;
import com.john.ceasa.Utils.AllActivits;
import com.john.ceasa.Utils.Constants;
import com.john.ceasa.VolleyConection.Connection;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    //private FloatingActionButton fab;
    private FloatingActionMenu fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    ListView list;
    public static String idCliente;
    public static int positionClient;
    ArrayList<ItemCliente> arrayListItens = new ArrayList<>();
    ArrayList<String> arrayvalues = new ArrayList<>();
    public static FrameLayout frameExcluirCLiente;
    AdapterCliente listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        AllActivits.clienteActivity = ClienteActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameExcluirCLiente = (FrameLayout)findViewById(R.id.frameExcluirCLiente);
        list = (ListView) findViewById(R.id.list);


        fab = (FloatingActionMenu)findViewById(R.id.fab);
        //fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        //fab3 = (FloatingActionButton)findViewById(R.id.fab3);

        /*fab.setMenuButtonColorNormal(R.color.orange);
        fab.setMenuButtonColorPressed(R.color.orange_very_light);
        fab.setMenuButtonColorRipple(R.color.orange_light);*/

        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {

            }
        });
        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        connectionGetUsers();

    }

    private void connectionGetUsers() {
        Connection connection = new Connection(Constants.URLclients, Request.Method.GET, "getClient", AllActivits.clienteActivity);
        connection.callByJsonStringRequest();
    }

    private JSONArray stringToJson(String response) throws JSONException {
        JSONObject obj1 = new JSONObject(response);
        JSONArray obj = obj1.getJSONArray("objects");
        return obj;

    }

    public static void returnConnectionGetClient(Activity activity, String response){
        ((ClienteActivity)activity).inflateList(response);
    }

    public void inflateList(String response){
        try {JSONArray obj = stringToJson(response);
            for (int i = 0; i < obj.length(); i++) {
                JSONObject obj1 = obj.getJSONObject(i);
                JSONArray emails = new JSONArray(obj1.getString("email"));
                arrayListItens.add(new ItemCliente(obj1.getString("name"), (emails.getJSONObject(0).getString("email").replace("u'", "")).replace("'","")));
                arrayvalues.add(obj1.getString("id"));
            }

            listAdapter = new AdapterCliente(AllActivits.clienteActivity, arrayvalues, arrayListItens);
            list.setAdapter(listAdapter);
        } catch (JSONException e) {
            e.printStackTrace();}
    }

    /****************************inicio metodos de click*********************************/
    public void floatingButtonMenuClick(View view){

    }

    //    public void floatingButtonFirstClick(View view){
//                Toast.makeText(ClienteActivity.this, "item 1", Toast.LENGTH_LONG).show();
//    }
    public void floatingButtonSecondClick(View view){
        Intent i = new Intent(ClienteActivity.this, AdicionarClienteActivity.class);
        startActivity(i);
        finish();
    }
//    public void floatingButtonThirdClick(View view){
//                    Toast.makeText(ClienteActivity.this, "item 3", Toast.LENGTH_LONG).show();
//        }

    public void menuDrawerClick(View view){
        MenuDrawer.whoCalled = AllActivits.clienteActivity;
        Intent i = new Intent(ClienteActivity.this, MenuDrawer.class);
        startActivity(i);
    }

    public void onBackPressed(){
        if (fab.isOpened())
            fab.close(true);
        if(frameExcluirCLiente.getVisibility() == View.VISIBLE)
            frameExcluirCLiente.setVisibility(View.GONE);
        if (frameExcluirCLiente.getVisibility() == View.VISIBLE)
            visibilityExcluirClienteFrame(false);
    }

    public void visibilityExcluirClienteFrame(boolean b) {
        frameExcluirCLiente.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    public void deleteClient(View view){
        Alerts.getInstance().aceitaExcluirCliente(AllActivits.clienteActivity);
        visibilityExcluirClienteFrame(false);
    }
    public void frameDeleteClient(View view){
        visibilityExcluirClienteFrame(false);
    }

    public static void responseConnectionDeleteClient(String response, Activity activity) {
        ((ClienteActivity)activity).removeListOfClient();
    }

    private void removeListOfClient() {
        AdapterCliente.values.remove(positionClient);
        listAdapter.notifyDataSetChanged();
    }

    /****************************fim metodos de click*********************************/
}
