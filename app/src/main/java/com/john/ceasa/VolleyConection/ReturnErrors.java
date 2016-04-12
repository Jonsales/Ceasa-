package com.john.ceasa.VolleyConection;

import android.app.Activity;
import android.view.View;

import com.john.ceasa.LoginActivity;
import com.john.ceasa.Utils.Alerts;

/**
 * Created by paulo on 22/02/16.
 */
public class ReturnErrors {

    public static void errors(String whoCalled, Activity activity, String statusCode){
        if (whoCalled.equals("login")) {
            Alerts.menssage("Erro!", "Usuário ou senha Inválidos", activity);
            LoginActivity.progress.setVisibility(View.GONE);
        }

        else if (whoCalled.equals("register")) {
            LoginActivity.progress.setVisibility(View.GONE);
            if (statusCode.equals("400"))
                Alerts.menssage("Erro!", "O usuário já existe", activity);
            else
                Alerts.menssage("Erro!", "Erro ao cadastrar o usuário", activity);
        }

        else if (whoCalled.equals("getClient"))
            Alerts.menssage("Erro!", "Erro ao trazer os clientes", activity);

        else if (whoCalled.equals("getVendas"))
            Alerts.menssage("Erro!", "Erro ao ao trazer informações", activity);

        else if (whoCalled.equals("recoverPassword"))
            Alerts.menssage("Erro!", "Erro ao tentar recuperar senha\nTente novamente mais tarde!", activity);
        else if (whoCalled.equals("deleteClient"))
            Alerts.menssage("Erro!", "Erro ao tentar excluir o cliente\nTente novamente mais tarde!", activity);
        else if(whoCalled.equals("sendClient"))
            if (statusCode.equals("400"))
                Alerts.menssage("Erro!", "O usuário já existe", activity);
            else
                Alerts.menssage("Erro!", "Erro ao cadastrar um cliente o usuário", activity);
    }
}
