package com.john.ceasa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.john.ceasa.Internet.VerifyInternet;
import com.john.ceasa.Utils.*;
import com.john.ceasa.VolleyConection.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue rq;
    public static RelativeLayout progress;
    ImageView esterEgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        AllActivits.loginActivity = LoginActivity.this;

        progress = (RelativeLayout)findViewById(R.id.relative_progress);
        esterEgg = (ImageView)findViewById(R.id.easter_egg);
        esterEgg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (Constants.debug) {
                    ((EditText) findViewById(R.id.email)).setText("admin@admin.com");
                    ((EditText) findViewById(R.id.senha)).setText("pallet0605");
                }

                return true;
            }
        });
    }
    /****************************Começo dos metodos de click***************************/
    public void onClickCadastrar(View view){
        doRegister();
    }

    public void onClickEntrar(View view){
        doLogin();

    }

    public void onClickEsqueci(View view){
        ((RelativeLayout)findViewById(R.id.relative_esqueci)).setVisibility(View.VISIBLE);
    }
    public void onClickCancelarEsqueci(View view){
        closeEsqueciSenha();
    }

    public void onClickEnviar(View view){
        doRecoverPassword();
    }

    private void doRecoverPassword() {
        if(VerifyInternet.isOnline(AllActivits.loginActivity)){
            LoginActivity.progress.setVisibility(View.VISIBLE);
            String email = String.valueOf(((EditText)findViewById(R.id.email_recover)).getText());
            Connection task = new Connection(Constants.URLrecover, Request.Method.POST,
                    "recoverPassword", AllActivits.loginActivity,
                    createMapRecover(email));


            task.callByJsonStringRequest();

        }
    }
    public HashMap createMapRecover(String email){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        return params;
    }

    public void onBackPressed() {
        if (((RelativeLayout)findViewById(R.id.relative_esqueci)).getVisibility() == View.VISIBLE)
            closeEsqueciSenha();
    }

    /****************************Fim dos metodos de click***************************/

    public void closeEsqueciSenha(){
        ((RelativeLayout)findViewById(R.id.relative_esqueci)).setVisibility(View.GONE);
    }

    private String[] getData(){
        String login = String.valueOf(((EditText)findViewById(R.id.email)).getText());
        String password = String.valueOf(((EditText)findViewById(R.id.senha)).getText());
        String[] dados = {login, password};
        return dados;
    }
    private HashMap<String,String> mountDataToPost() {
        HashMap<String, String> params = new HashMap<>();
        String[] dados = getData();
        params.put("email", dados[0]);
        params.put("password", dados[1]);
        params.put("username", dados[0]);

        return params;
    }

    private void doRegister() {
        if (VerifyInternet.isOnline(AllActivits.loginActivity)){
            progress.setVisibility(View.VISIBLE);
            callRegisterTask();


        }
    }

    public void doLogin() {
        if (VerifyInternet.isOnline(AllActivits.loginActivity)){
            progress.setVisibility(View.VISIBLE);
            callLoginTask();

        }
    }

    private void callRegisterTask(){
        HashMap<String,String> params = mountDataToPost();
        Connection task = new Connection(Constants.URLregister, Request.Method.POST, "register", AllActivits.loginActivity,params);
        task.callByJsonStringRequest();


    }



    private void callLoginTask() {
        String[] dados =  getData();
        Connection task = new Connection(Constants.URLlogin, Request.Method.GET, "login", AllActivits.loginActivity);
        task.setLogin(dados[0]);
        task.setPassword(dados[1]);
        task.callByJsonStringRequest();

    }

    public static void returnConnectionLogin(Activity activity, String response){
        ((LoginActivity)activity).goToHome(response);
    }

    public static void returnConnectionRegister(Activity activity){
        ((LoginActivity)activity).changeActivity();
    }

    public void goToHome(String response) {
        saveData(response);
        changeActivity();
    }

    public void changeActivity(){
        LoginActivity.progress.setVisibility(View.GONE);
        Intent i = new Intent(LoginActivity.this, MenuDrawer.class);
        startActivity(i);
        finish();
    }
    private void saveData(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            JSONObject objects = obj.getJSONArray("objects").getJSONObject(0);
            Preferences.savePreferences("username", objects.getString("username"), AllActivits.loginActivity);
            Preferences.savePreferences("api_key", objects.getString("api_key"), AllActivits.loginActivity);
        } catch (JSONException e) {
            if (Constants.debug)
                Alerts.menssage("Aviso", "erro ao salvar os dados no app", AllActivits.loginActivity);
            else
                e.printStackTrace();
        }
    }


}
