package com.bioxx.tfc.api.TileEntities;

public interface IHeater {
    float getCurrentTemperature();
    void heat(IHeatAccepter accepter);
}
