package com.john.ceasa;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.john.ceasa.Utils.AllActivits;

public class MenuDrawer extends AppCompatActivity {

    public static Activity whoCalled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
    }


    /************************** inicio metodos de clique ********************/
    public void  closeActivity(View view){
        finish();
    }
    public void clientesOnClick(View view){
        if (whoCalled == AllActivits.clienteActivity && whoCalled != null)
            finish();
        else{
            Intent i = new Intent(MenuDrawer.this, ClienteActivity.class);
            startActivity(i);
            finish();
        }

    }
    //    public void usuariosOnClick(View view){
//
//    }
//    public void produtosOnClick(View view){
//
//    }
//    public void estoqueOnClick(View view){
//
//    }
    public void vendasOnClick(View view){
        if (whoCalled == AllActivits.vendasActivity && whoCalled != null)
            finish();
        else{
            Intent i = new Intent(MenuDrawer.this, VendasActivity.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    public void onBackPressed() {

    }
    /************************** fim metodos de clique ********************/
}
