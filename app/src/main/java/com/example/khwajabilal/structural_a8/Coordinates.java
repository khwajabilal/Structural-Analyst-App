package com.example.khwajabilal.structural_a8;

import java.io.Serializable;

/**
 * Created by KhwajaBilal on 5/1/2016.
 */

public class Coordinates implements Serializable
{
    float x_Axis_Start,y_Axis_Start,x_Axis_End,y_Axis_End;
    Coordinates(){}
    Coordinates(float x_Axis_Start,float y_Axis_Start,float x_Axis_End,float y_Axis_End)
    {
        this.x_Axis_Start=x_Axis_Start;
        this.y_Axis_Start=y_Axis_Start;
        this.x_Axis_End=x_Axis_End;
        this.y_Axis_End=y_Axis_End;

    }

    public float getX_Axis_Start() {
        return x_Axis_Start;
    }

    public float getY_Axis_Start() {
        return y_Axis_Start;
    }

    public float getX_Axis_End() {
        return x_Axis_End;
    }

    public float getY_Axis_End() {
        return y_Axis_End;
    }

    public void setX_Axis_Start(float x_Axis_Start) {
        this.x_Axis_Start = x_Axis_Start;
    }

    public void setY_Axis_Start(float y_Axis_Start) {
        this.y_Axis_Start = y_Axis_Start;
    }

    public void setX_Axis_End(float x_Axis_End) {
        this.x_Axis_End = x_Axis_End;
    }

    public void setY_Axis_End(float y_Axis_End) {
        this.y_Axis_End = y_Axis_End;
    }
}
