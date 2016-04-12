package com.john.ceasa.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.android.volley.Request;

import com.john.ceasa.ClienteActivity;
import com.john.ceasa.VolleyConection.*;

/**
 * Created by paulo on 06/01/16.
 */
public class Alerts {
    private static Alerts ourInstance = new Alerts();

    public static Alerts getInstance() {
        return ourInstance;
    }

    private Alerts() {
    }

    public static void menssage(String title, String message, Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    public void aceitaExcluirCliente(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Connection task = new Connection(Constants.URLdeleteClients + ClienteActivity.idCliente + "/?format=json",
                        Request.Method.DELETE, "deleteClient",AllActivits.clienteActivity/*,mountDataToDelete()*/);
                task.callByJsonStringRequest();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setMessage("Tem certeza que deseja excluir esse cliente?");
        builder.setTitle("Aviso!");
        builder.create().show();
    }

//    public HashMap<String, String> mountDataToDelete(){
//        HashMap<String,String> params = new HashMap<>();
//        params.put("id", ClienteActivity.idCliente);
//        return params;
//    }


}
