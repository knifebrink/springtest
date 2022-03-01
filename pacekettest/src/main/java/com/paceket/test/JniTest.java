package com.paceket.test;

import com.sun.jna.Native;

public class JniTest {
    public static void main(String[] args){
        NetSDKLib2 netSdk = Native.load("E:\\project\\dragon-platform\\dragon-business\\iot-center\\src\\main\\resources\\win64\\dhnetsdk.dll", NetSDKLib2.class);
//        NetSDKLib2 configSdk = Native.load("C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\dhconfigsdk.dll", NetSDKLib2.class);
        try {
            NetSDKLib2 native3 = Native.load("dhconfigsdk", NetSDKLib2.class);
            System.out.println("测试调用3："+native3);
        }catch (Throwable e){
            System.out.println(e.getMessage());
            System.out.println("\n");
        }
        NetSDKLib2 native4 = Native.load("win64/dhconfigsdk", NetSDKLib2.class);
//        NetSDKLib2 native4 = Native.loadLibrary("dhnetsdk.dll", NetSDKLib2.class);


        System.out.println("版本号是："+netSdk.CLIENT_GetSDKVersion());
//        System.out.println("版本号是："+configSdk);

        System.out.println("测试调用4："+native4);

    }
}
