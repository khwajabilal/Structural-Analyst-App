package com.example.khwajabilal.structural_a8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.khwajabilal.structural_a8.Beam.Joint;

/**
 * Created by KhwajaBilal on 7/16/2016.
 */
public class AnalyzedModelView extends View {
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    Paint paint4 = new Paint();
    Coordinates modelCordinates;
    Joint modelJointList;


    public AnalyzedModelView(Context context) {
        super(context);
        paint1.setColor(Color.BLACK);
        paint2.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(0, 0, 20, 20, paint1);
        canvas.drawLine(20, 0, 0, 20, paint1);
    }

}
