package com.alanwalker.getuuid.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class DeviceInfo {

    private static String Device_Id;
    private static String MAC_Address;
    private static String Serial_Number;
    private static String Android_Id;

    public static String getDevice_Id(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId().toString();
    }

    public static String getMAC_Address(Context context){
        WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }

    public static String getSerial_Number(){
        return Build.SERIAL;
    }

    public static String getAndroid_Id(Context context){
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


}
