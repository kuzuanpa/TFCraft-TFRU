package com.bioxx.tfc.api;

import cn.kuzuanpa.ktfruaddon.EnvironmentHelper;

public class TFRUEnvHelper implements EnvironmentHelper.TFRUModEnvHelper {
    public static boolean isInTFRU = false;

    @Override
    public boolean isModTFRU() {
        return true;
    }

    @Override
    public void setModInTFRU() {
        isInTFRU = true;
    }
}