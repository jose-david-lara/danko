package com.wposs.danko.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.wposs.danko.BuildConfig;
import com.wposs.danko.interfaces.ModelInterface;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class InfoDevice {


    @SuppressLint("HardwareIds")
    public static String getSerial(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getVersion(Context context) {
        return  BuildConfig.VERSION_NAME;
    }

    public static String getIPAddressIPv4(String id, Context context) {
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                System.out.println("interfaces:::"+intf.getName());
                if (intf.getName().contains(id)) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {

                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            if (addr instanceof Inet4Address) {
                                return sAddr;
                            }
                        }

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String infoRedConnect(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        ModelInterface.user.setRedConnect(false);
        try {
            if (networkInfo != null) {

                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                     ModelInterface.user.setRedConnect(true);
                    if (networkInfo.getTypeName().equals("WIFI") || networkInfo.getTypeName().equals("wifi")) {

                        ModelInterface.user.setNameInterfaceConnect(networkInfo.getTypeName());
                    } else if (networkInfo.getTypeName().equals("MOBILE") || networkInfo.getTypeName().equals("mobile")) {
                        ModelInterface.user.setNameInterfaceConnect(networkInfo.getTypeName());
                    }
                    System.out.println("::::INFORMACION RED::::"+networkInfo.getExtraInfo());
                } else {
                    return "Redes de internet no disponible - 404";
                }
            }else {
                return "Redes de internet no disponible - 500";
            }
        }catch (Exception ex){
            return "error exception";
        }
        return "";
    }

}
