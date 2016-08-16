package com.example.khwajabilal.structural_a8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Selcet_Structure_Type extends Activity {
    Button drawBeam_Button,drawTruss_Button,drawFrame_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selcet__structure__type);
        drawBeam_Button=(Button)findViewById(R.id.drawBeam_Button);
        drawTruss_Button=(Button)findViewById(R.id.drawTruss_Button);
        drawFrame_Button=(Button)findViewById(R.id.drawFrame_Button);
    }

    //onClick Of draw Beam
    public void drawBeamOnClick(View view)
    {
        Intent intent = new Intent(Selcet_Structure_Type.this, DrawingPlatform.class);
        startActivity(intent);

    }
    public void drawTrussOnClick(View view)
    {
        Intent intent = new Intent(Selcet_Structure_Type.this, DrawingPlatform.class);
        startActivity(intent);
    }
    public void drawFrameOnClick(View view)
    {
        Toast.makeText(Selcet_Structure_Type.this,"Frame module not interated yet",Toast.LENGTH_SHORT).show();
    }
}
