package com.john.ceasa.Internet;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by paulo on 05/01/16.
 */
public class VerifyInternet {

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm =(ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }else{
            Toast.makeText(activity, "É necessário uma conexão com a internet.", Toast.LENGTH_LONG).show();
        }
        return false;
    }


}
