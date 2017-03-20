package com.example.zacharybryant.eagleeyeapp;

/**
 * Created by jameslarger on 3/5/17.
 */

public class Buildings
{
    private String buildingName;

    public Buildings()
    {

    }

    public Buildings(String name)
    {
        this.buildingName = name;
    }

    public String getBuildingName()
    {
        return buildingName;
    }

    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }
}
