package com.example.khwajabilal.structural_a8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class AnalyzedModel extends AppCompatActivity {
    ZoomableImageView zoomableImageView1;
    Bitmap bitmap;
    double[] reactions,displacments;
    TransferClass t1;
    String result;
    ModelDetails modelDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyzed_model);
        zoomableImageView1=(ZoomableImageView)findViewById(R.id.analayzed_model);
        Intent intent=getIntent();
        bitmap=t1.getMybitmap();

        //bitmap=Bitmap.createBitmap(t1.getMybitmap());
       // bitmap=(Bitmap)intent.getParcelableExtra("bitmap");
        modelDetails=(ModelDetails)intent.getSerializableExtra("ModelDetails");
        reactions=intent.getDoubleArrayExtra("reactions");
        displacments=intent.getDoubleArrayExtra("displacments");
        result=intent.getStringExtra("resultString");
        zoomableImageView1.setImageBitmap(bitmap);
        Canvas canvas=new Canvas(bitmap);
        Bitmap reac_Nve = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_arrow_bottom);
        Bitmap reac_pve = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_arrow_top);
        Bitmap disp_Nve = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_arrow_bottom_blue);
        Bitmap disp_pve = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_arrow_up_blue);
        Paint p1=new Paint();
        canvas.drawBitmap(reac_Nve,200,600,p1);
        canvas.drawBitmap(disp_Nve,300,600,p1);
        canvas.drawBitmap(reac_pve,350,750,p1);
        canvas.drawBitmap(disp_pve,200,750,p1);
        Paint p2=new Paint();
        p2.setColor(Color.BLUE);
        p2.setStrokeWidth(3.0f);
        p2.setTextSize(20);
        canvas.drawText(String.valueOf(Math.round(reactions[0]*100)/100), 200, 600, p2);
        canvas.drawText(String.valueOf(Math.round(reactions[1])*100/100), 300, 600, p2);
        canvas.drawText(String.valueOf(Math.round(displacments[0]*100)/100), 350, 830, p2);
        canvas.drawText(String.valueOf(Math.round(displacments[1]*100)/100), 200, 830, p2);
        canvas.drawText(String.valueOf(Math.round(displacments[2]*100)/100),200,600,p2);
//        for (int i=0;i<reactions.length;i++)
//        {
//            if (reactions[i]<0)
//            {
//                canvas.drawBitmap(reac_Nve,200,650,p1);
//            }
//        }
    }




    public void reportGenerationOnclick(View view)
    {
        Intent i=new Intent(AnalyzedModel.this,Analyze.class);
        i.putExtra("resultString",result);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(this,DrawingPlatform.class);
        i.putExtra("updatedModel", (Serializable) modelDetails);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
