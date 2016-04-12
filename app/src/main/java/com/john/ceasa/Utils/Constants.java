package com.john.ceasa.Utils;

/**
 * Created by paulo on 06/01/16.
 */
public class Constants {

    public static boolean debug = true;

//    private String url = "http://ec2-52-26-239-24.us-west-2.compute.amazonaws.com/api/v1/user/";

    //private static String url = "http://ec2-52-25-120-159.us-west-2.compute.amazonaws.com";
    private static String url = "http://192.168.0.12:8000";

    public static String URLlogin = url + "/api/user/?format=json";
    public static String URLregister = url + "/api/register_user/?format=json";
    public static String URLclients = url + "/api/client/?format=json";
    public static String URLdeleteClients = url + "/api/client/";
    public static String URLrecover = url + "/api/password_recover/?format=json";

}
