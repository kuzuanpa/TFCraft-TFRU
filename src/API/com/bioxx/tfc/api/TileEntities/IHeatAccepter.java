package com.bioxx.tfc.api.TileEntities;

public interface IHeatAccepter {
    /**@return Heat Amount consumed, in HU(gt6 unit)**/
    float consumeHeat(IHeater heater);
}
