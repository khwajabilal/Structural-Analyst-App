package com.example.khwajabilal.structural_a8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.widget.Toast;

import com.example.khwajabilal.structural_a8.Beam.Joint;

import java.util.ArrayList;

/**
 * Created by KhwajaBilal on 5/20/2016.
 */
public class SelcetionClass {
    boolean isSelect=false;
    boolean itemselectd=false;
    Bitmap drawblebitmap,myBitmapUnselected;
    ArrayList<Joint> jointList = new ArrayList<>();
    ArrayList<Coordinates> mCoordinatesList = new ArrayList<>();
    float x_CoordinateClicked,y_CoordinateClicked;
    float Distance,nearest=400;
    int IndexNearest;
    Canvas canvas;
    ZoomableImageView zoomableImageView;
    Paint p,removePaint;
    Context context;
    public  SelcetionClass(Context context,ZoomableImageView zoomableImageView, Bitmap bitmap, ArrayList<Joint> mjoint, ArrayList<Coordinates> mcoordinates)
    {
        this.context=context;
        this.zoomableImageView=zoomableImageView;
        drawblebitmap=bitmap;
        jointList=mjoint;
        mCoordinatesList=mcoordinates;
        canvas=new Canvas(drawblebitmap);
        p=new Paint();
        removePaint=new Paint();
        removePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

        p.setColor(Color.GREEN);
        p.setStrokeWidth(3.0f);
        p.setStyle(Paint.Style.STROKE);
    }
    public void getNearestItemSelected()
    {
        for (int i=0;i<mCoordinatesList.size();i++)
        {
            Log.d("x clicked in loop", String.valueOf(x_CoordinateClicked)+" "+String.valueOf(y_CoordinateClicked));

            Distance=pointToLineDistance(x_CoordinateClicked,y_CoordinateClicked,mCoordinatesList.get(i).getX_Axis_Start(),mCoordinatesList.get(i).getY_Axis_Start(),mCoordinatesList.get(i).getX_Axis_End(),mCoordinatesList.get(i).getY_Axis_End());
            Log.d("clicked distance",String.valueOf(Distance));
            if (Distance>=nearest)
            {
                itemselectd=true;
                nearest=Distance;
                IndexNearest=i;
            }
        }
        //canvas.drawLine(mCoordinatesList.get(IndexNearest).getX_Axis_Start(),mCoordinatesList.get(IndexNearest).getY_Axis_Start(),mCoordinatesList.get(IndexNearest).getX_Axis_End(),mCoordinatesList.get(IndexNearest).getY_Axis_End(),p);
        //canvas.drawRect(197,695,405,705,p);
        if (itemselectd)
        {
            myBitmapUnselected=drawblebitmap;
            Toast.makeText(context,"i am in if part"+nearest,Toast.LENGTH_LONG).show();
            canvas.drawRect(mCoordinatesList.get(IndexNearest).getX_Axis_Start() - 7, mCoordinatesList.get(IndexNearest).getY_Axis_Start() - 7, mCoordinatesList.get(IndexNearest).getX_Axis_End() + 7, mCoordinatesList.get(IndexNearest).getY_Axis_End() + 7, p);
        }
        else
        {
            Toast.makeText(context,"i am in else part",Toast.LENGTH_LONG).show();
            drawblebitmap=myBitmapUnselected;
            zoomableImageView.setImageBitmap(myBitmapUnselected);
            //canvas.drawRect(mCoordinatesList.get(IndexNearest).getX_Axis_Start()-7,mCoordinatesList.get(IndexNearest).getY_Axis_Start()-7,mCoordinatesList.get(IndexNearest).getX_Axis_End()+7,mCoordinatesList.get(IndexNearest).getY_Axis_End()+7 , removePaint);
        }
    }

    public void unSelectItem()
    {
        canvas.drawRect(mCoordinatesList.get(IndexNearest).getX_Axis_Start()-7,mCoordinatesList.get(IndexNearest).getY_Axis_Start()-7,mCoordinatesList.get(IndexNearest).getX_Axis_End()+7,mCoordinatesList.get(IndexNearest).getY_Axis_End()+7 , removePaint);
    }

    public void setX_CoordinateClicked(float x_CoordinateClicked) {
        this.x_CoordinateClicked = x_CoordinateClicked;
        Log.d("x clicked in set",String.valueOf(this.x_CoordinateClicked));
    }

    public void setY_CoordinateClicked(float y_CoordinateClicked) {
        this.y_CoordinateClicked = y_CoordinateClicked;
        Log.d("y clicked in set",String.valueOf(this.y_CoordinateClicked));
    }


    //distance function
    public float Distance(float x1,float x2,float y1,float y2)
    {
        return (float)Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
    }

    public float pointToLineDistance(float x0,float y0,float x1,float y1,float x2,float y2) {
        double normalLength = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return (float) (Math.abs((x0-x1)*(y2-y1)-(y0-y1)*(x2-x1))/normalLength);
    }

    //distance from line
    public float DistanceLine(float x0,float y0,float x1,float y1,float x2,float y2)
    {
        float A = x0 - x1;
        float B = y0 - y1;
        float C = x2 - x1;
        float D = y2 - y1;

        float dot = A * C + B * D;
        float len_sq = C * C + D * D;
        float param = -1;
        if (len_sq != 0) //in case of 0 length line
            param = dot / len_sq;

        float xx, yy;

        if (param < 0) {
            xx = x1;
            yy = y1;
        }
        else if (param > 1) {
            xx = x2;
            yy = y2;
        }
        else {
            xx = x1 + param * C;
            yy = y1 + param * D;
        }

        float dx = x0 - xx;
        float dy = y0 - yy;
        float temp=(dx * dx + dy * dy);
        Double dtemp= Double.valueOf(temp);
        return (float) Math.sqrt(dtemp);
       // return (Math.abs((y2-y1)*x0-(x2-x1)*y0+x2*y1-y2*x1))/Distance(x1,x2,y1,y2);
    }
    //
}
