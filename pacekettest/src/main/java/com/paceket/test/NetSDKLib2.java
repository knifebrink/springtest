package com.paceket.test;

import com.sun.jna.Library;

public interface NetSDKLib2  extends Library {
    public int CLIENT_GetSDKVersion();
}
