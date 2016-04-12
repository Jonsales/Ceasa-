package com.john.ceasa.VolleyConection;

import android.app.Activity;
import android.view.View;

import com.john.ceasa.ClienteActivity;
import com.john.ceasa.Fragments.*;
import com.john.ceasa.*;
import com.john.ceasa.Utils.*;

/**
 * Created by paulo on 22/02/16.
 */
public class ReturnResponse {
    public static String login;
    public static String password;

    private static ReturnResponse ourInstance = new ReturnResponse();

    public static ReturnResponse getInstance() {
        return ourInstance;
    }

    private ReturnResponse() {
    }

    public void goTo(String whoCalled, String response, Activity activity){
        if (whoCalled.equals("login"))
           LoginActivity.returnConnectionLogin(activity, response);
        else if (whoCalled.equals("register"))
            LoginActivity.returnConnectionRegister(activity);
        else if (whoCalled.equals("getClient"))
            ClienteActivity.returnConnectionGetClient(activity, response);
        else if (whoCalled.equals("getVendas"))
            IndividualFragment.responseConnection(response);
        else if (whoCalled.equals("recoverPassword")) {
            LoginActivity.progress.setVisibility(View.GONE);
            Alerts.menssage("Sucesso!",
                    "Informações para recuperação da senha serão enviadas para o e-mail cadastrado",
                    AllActivits.loginActivity);
        }
        else if(whoCalled.equals("sendClient"))
            Alerts.menssage("Sucesso!\n", "Cliente cadastrado!", AllActivits.adicionarClienteActivity);
        else if (whoCalled.equals("deleteClient"))
            ClienteActivity.responseConnectionDeleteClient(response,activity);

    }


}
