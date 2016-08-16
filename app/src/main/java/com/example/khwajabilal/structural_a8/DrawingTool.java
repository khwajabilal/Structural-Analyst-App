package com.example.khwajabilal.structural_a8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.khwajabilal.structural_a8.Beam.Joint;
import com.example.khwajabilal.structural_a8.Beam.Member;

import java.util.ArrayList;

/**
 * Created by KhwajaBilal on 5/23/2016.
 */
public class DrawingTool {
    Context context;

    Bitmap drawableBitmap;
    Bitmap myBitmapgraphed;
    Canvas canvas;
    Paint p;

    ArrayList<Joint> jointList = new ArrayList<>();
    ArrayList<Coordinates> mCoordinatesList = new ArrayList<>();
    ArrayList<Member> membersList =new ArrayList<>();

    ZoomableImageView zoomableImageView;

    DrawingTool()
    {

    }
    DrawingTool(Context context,ZoomableImageView zoomableImageView, Bitmap drawableBitmap,Bitmap myBitmapgraphed, ArrayList<Joint> jointList, ArrayList<Coordinates> mCoordinatesList, ArrayList<Member> membersList, Canvas canvas)
    {
        this.context=context;
        this.zoomableImageView=zoomableImageView;
        this.drawableBitmap=drawableBitmap;
        this.myBitmapgraphed=myBitmapgraphed;
        this.jointList=jointList;
        this.mCoordinatesList=mCoordinatesList;
        this.membersList=membersList;
        this.canvas=canvas;
        p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(10.0f);
    }

    //Add Joints Method called to add joints to DrawingBitmap
    public void addJoints(float length)
    {
        Coordinates jointCordinates=new Coordinates();
        jointCordinates.setY_Axis_Start(710);
        jointCordinates.setY_Axis_End(730);
        float tempxstart,tempxend;
        Joint tempJoint;

        if(length==0)
        {
            jointCordinates.setX_Axis_Start(200);
            tempxstart=200;
            jointCordinates.setX_Axis_End(210);
            tempxend=200;
            tempJoint=new Joint(0,0);
            tempJoint.setyRestrained(true);
            tempJoint.setIsFySet(false);
            jointList.add(tempJoint);
        }
        else
        {
            float x=200+(length*50);
            jointCordinates.setX_Axis_Start(x);
            tempxstart=x;
            tempxend=x;
            jointCordinates.setX_Axis_End(x);
            tempJoint=new Joint(length,0);
            tempJoint.setyRestrained(true);
            tempJoint.setIsFySet(false);
            jointList.add(tempJoint);
        }
        //canvas.drawLine(tempxstart,710,tempxend,730,p);
        Bitmap hinchsup_drawing_icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.hinch_support_drawing_icon);
        Paint p = new Paint();
        // p.setAlpha(127);

        canvas.drawBitmap(hinchsup_drawing_icon, (tempxstart-30), 705, p);
    }


    //length to line method scaling here
    public void lengthtoLine(float length,float x_Axis_Start,float y_Axis_Start,float x_Axis_End,float y_Axis_End)
    {
        x_Axis_Start=200; //center values
        y_Axis_Start=700;

//        x_Axis_Start=start*80;
//        y_Axis_Start=start*200;
        x_Axis_End=200+(length*50);
        y_Axis_End=700;
        Coordinates mCoordinates=new Coordinates(x_Axis_Start,y_Axis_Start,x_Axis_End,y_Axis_End);
        mCoordinatesList.add(mCoordinates);
        canvas.drawLine(x_Axis_Start,y_Axis_Start,x_Axis_End,y_Axis_End,p);
    }
}
