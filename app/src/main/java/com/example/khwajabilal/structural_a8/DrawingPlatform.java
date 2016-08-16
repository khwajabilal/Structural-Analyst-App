package com.example.khwajabilal.structural_a8;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.khwajabilal.structural_a8.Beam.Compute;
import com.example.khwajabilal.structural_a8.Beam.Joint;
import com.example.khwajabilal.structural_a8.Beam.Member;
import com.example.khwajabilal.structural_a8.Beam.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class DrawingPlatform extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    ModelDetails modeldetatils;
    TransferClass transferClass;
    static boolean autodraw=false;


    Bitmap bitmap,drawableBitmap;
    Canvas canvas;
    Paint p;

    Bitmap myBitmapgraphed;

    float x_Axis_Start;
    float y_Axis_Start;
    float x_Axis_End;
    float y_Axis_End;


    ArrayList<Joint> jointList = new ArrayList<>();
    ArrayList<Coordinates> mCoordinatesList = new ArrayList<>();
    ArrayList<Float> loadsList=new ArrayList<>();
    ArrayList<Float> displacementList=new ArrayList<>();
    ArrayList<Member> membersList =new ArrayList<>();


    EditText beamstet,widthet,suppportET;
    ZoomableImageView zoomableImageView;
    ImageButton drawingImagebtn,selectionImagebtn,addSupportbtn;

    //selection
    boolean selectOption;
    SelcetionClass selcetionClass;
    Context context;

    TransferBoolean trasnferbool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_platform);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        trasnferbool=new TransferBoolean();

        //for drawer window
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //for navagition view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        context=this;

        //code for zoomableviamge view
        zoomableImageView=(ZoomableImageView)findViewById(R.id.drawing);
        myBitmapgraphed=setGraph(getBitmap());
        zoomableImageView.setImageBitmap(myBitmapgraphed);
        drawingImagebtn=(ImageButton)findViewById(R.id.drawing_Btn);
        selectionImagebtn=(ImageButton)findViewById(R.id.Selection_Mode_Btn);
        addSupportbtn=(ImageButton)findViewById(R.id.add_Support);

        //if model is updated or alerady drawn
        if (autodraw)
        {
            Intent i=getIntent();
            modeldetatils=(ModelDetails)i.getSerializableExtra("updatedModel");
            mCoordinatesList= modeldetatils.getmCoordinatesList();
            jointList=modeldetatils.getJointList();
            loadsList=modeldetatils.getLoadsList();
            displacementList=modeldetatils.getDisplacementList();
            autoDrawing();
            autodraw=false;
            Log.d("model updated","updatedmodel");
        }
    }

    //auto draw function
    public void autoDrawing()
    {
        canvas.drawLine(mCoordinatesList.get(0).getX_Axis_Start(),mCoordinatesList.get(0).getY_Axis_Start(),mCoordinatesList.get(0).getX_Axis_End(),mCoordinatesList.get(0).getY_Axis_End(),p);
        for(int i=0;i<3;i++) {
            float x= (float) (200+(jointList.get(i).getX()*50));
            Bitmap hinchsup_drawing_icon = BitmapFactory.decodeResource(getResources(), R.drawable.hinch_support_drawing_icon);  //here use if else for roll support
            Paint p = new Paint();
            canvas.drawBitmap(hinchsup_drawing_icon, (x - 30), 705, p);
        }
    }


//get Bitmap
    public Bitmap getBitmap()
    {
        Toast.makeText(this,"here 2",Toast.LENGTH_SHORT).show();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.backdrawing);
        drawableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        canvas = new Canvas(drawableBitmap);
        // set drawing colour
        p = new Paint();
        //noinspection ResourceAsColor
        p.setColor(context.getResources().getColor(R.color.colorModel));
        p.setStrokeWidth(15.0f);
//
//// draw a line onto the canvas
//        canvas.drawLine(0, 0, 50, 50, p);
        return drawableBitmap;
    }




    //add support Onclick
    public  void addSupport(View view)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.backdrawing);
        ImageButton hinch_support,roll_support,fixed_support_R,fixed_support_L;
        final Dialog alert = new Dialog(DrawingPlatform.this);

        alert.setContentView(R.layout.supportdropdown_layout);
        alert.setTitle("Draw Beam");
        hinch_support=(ImageButton) alert.findViewById(R.id.hinch_support);
        roll_support=(ImageButton) alert.findViewById(R.id.roll_support);
        fixed_support_R=(ImageButton) alert.findViewById(R.id.fixed_right);
        fixed_support_L=(ImageButton) alert.findViewById(R.id.fixed_left);


        // RelativeLayout lila1= new RelativeLayout(this);
        //  lila1.setOrientation(LinearLayout.VERTICAL); //1 is for vertical orientation

        hinch_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(DrawingPlatform.this,"Hinch Support selected",Toast.LENGTH_LONG).show();
                //  drawingPannel.drawMannually(10,300,400,300);
                alert.dismiss();
                final Dialog alerthinch = new Dialog(DrawingPlatform.this);

                alerthinch.setContentView(R.layout.hinch_support_setting);
                alerthinch.setTitle("Hinch support setting");
                final EditText positionAtX=(EditText)alerthinch.findViewById(R.id.AddSupportET);
                Button oKButton=(Button)alerthinch.findViewById(R.id.AddSupportOk);
                oKButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addJoints(Float.parseFloat( positionAtX.getText().toString()),R.drawable.hinch_support_drawing_icon);
                        alerthinch.dismiss();
                    }
                });
                alerthinch.show();

            }
        });
        roll_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawingPlatform.this,"Roll Support selected",Toast.LENGTH_LONG).show();
                //  drawingPannel.drawMannually(10,300,400,300);
                alert.dismiss();
                final Dialog alertroll = new Dialog(DrawingPlatform.this);

                alertroll.setContentView(R.layout.hinch_support_setting);
                alertroll.setTitle("Hinch support setting");
                final EditText positionAtX=(EditText)alertroll.findViewById(R.id.AddSupportET);
                Button oKButton=(Button)alertroll.findViewById(R.id.AddSupportOk);
                oKButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addJoints(Float.parseFloat( positionAtX.getText().toString()),R.drawable.roll_support_drawing_icon);
                        alertroll.dismiss();
                    }
                });
                alertroll.show();
            }
        });
        fixed_support_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawingPlatform.this,"fixed Right Support selected",Toast.LENGTH_LONG).show();
                //  drawingPannel.drawMannually(10,300,400,300);
                alert.dismiss();
                final Dialog alertroll = new Dialog(DrawingPlatform.this);

                alertroll.setContentView(R.layout.hinch_support_setting);
                alertroll.setTitle("Hinch support setting");
                final EditText positionAtX=(EditText)alertroll.findViewById(R.id.AddSupportET);
                Button oKButton=(Button)alertroll.findViewById(R.id.AddSupportOk);
                oKButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Float.parseFloat( positionAtX.getText().toString())== mCoordinatesList.get(0).getX_Axis_End()) {
                            addJoints(Float.parseFloat(positionAtX.getText().toString()), R.drawable.roll_support_drawing_icon);
                        }
                        else {
                            Toast.makeText(DrawingPlatform.this,"Cannot add this type of support at this length en",Toast.LENGTH_SHORT).show();
                        }
                        alertroll.dismiss();
                    }
                });
                alertroll.show();
            }
        });
        fixed_support_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawingPlatform.this,"fixed left Support selected",Toast.LENGTH_LONG).show();
                //  drawingPannel.drawMannually(10,300,400,300);
                alert.dismiss();
                final Dialog alertroll = new Dialog(DrawingPlatform.this);

                alertroll.setContentView(R.layout.hinch_support_setting);
                alertroll.setTitle("Hinch support setting");
                final EditText positionAtX=(EditText)alertroll.findViewById(R.id.AddSupportET);
                Button oKButton=(Button)alertroll.findViewById(R.id.AddSupportOk);
                oKButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Float.parseFloat( positionAtX.getText().toString())== mCoordinatesList.get(0).getX_Axis_Start()) {
                            addJoints(Float.parseFloat(positionAtX.getText().toString()), R.drawable.fixed_support_left_drawing_icon);
                        }
                        else
                        {
                            Toast.makeText(DrawingPlatform.this,"Cannot add this type of support at this length",Toast.LENGTH_SHORT).show();
                        }
                        alertroll.dismiss();
                    }
                });
                alertroll.show();
            }
        });
        alert.show();
    }

    //for materials
    public  void select_Material_OnClick(View view)
    {
        Toast.makeText(DrawingPlatform.this,"Material is added through code for time",Toast.LENGTH_SHORT).show();
    }






    //addjoint
    public void addJoints(float length,int icon)
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
        Bitmap hinchsup_drawing_icon = BitmapFactory.decodeResource(getResources(), icon);
        Paint p = new Paint();
       // p.setAlpha(127);
        if (icon==R.drawable.fixed_support_right_drawing_icon)
        {
            canvas.drawBitmap(hinchsup_drawing_icon,(mCoordinatesList.get(0).getX_Axis_End()+2),700,p);
        }
        else if (icon==R.drawable.fixed_support_left_drawing_icon)
        {
            canvas.drawBitmap(hinchsup_drawing_icon,(mCoordinatesList.get(0).getX_Axis_Start()-2),700,p);
        }
        else {
            canvas.drawBitmap(hinchsup_drawing_icon, (tempxstart - 30), 705, p);
        }
    }

//add loads
    public void addLoad_OnClick(View view)
    {
        final EditText load1;
        final float[] tempfloat = new float[1];
        final Dialog alertLoad = new Dialog(DrawingPlatform.this);
        alertLoad.setContentView(R.layout.loaddialogbox);
        load1=(EditText)alertLoad.findViewById(R.id.load1_et);
        alertLoad.setTitle("Add Loads");
        Button okLoads=(Button)alertLoad.findViewById(R.id.load_ok);
        okLoads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempfloat[0] = Float.parseFloat(load1.getText().toString());
                loadsList.add(tempfloat[0]);
                alertLoad.dismiss();
            }
        });
        alertLoad.show();
    }

    //add displacements
    public void set_displacementsOnClick(View view)
    {
        final EditText displacment1;
        final float[] tempfloat = new float[1];
        final Dialog alertdisplacment = new Dialog(DrawingPlatform.this);
        alertdisplacment.setContentView(R.layout.displacementdialogbox);
        displacment1=(EditText)alertdisplacment.findViewById(R.id.displacement1_et);
        alertdisplacment.setTitle("ADD DISPLACMENT");
        Button okDisplacement=(Button)alertdisplacment.findViewById(R.id.displacement_ok);
        okDisplacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempfloat[0]=Float.parseFloat(displacment1.getText().toString());
                displacementList.add(tempfloat[0]);
                alertdisplacment.dismiss();
            }
        });
        alertdisplacment.show();
    }


    //for drawing button draw mannualy through dialog box
    public void drawingOnClick(View view)
    {

        final Dialog alert = new Dialog(DrawingPlatform.this);

        alert.setContentView(R.layout.drawingdialog);
        alert.setTitle("Draw Beam");
        widthet=(EditText)alert.findViewById(R.id.beamwidet);

        // RelativeLayout lila1= new RelativeLayout(this);
        //  lila1.setOrientation(LinearLayout.VERTICAL); //1 is for vertical orientation
        Button dismissButton = (Button) alert.findViewById(R.id.drawingdialogokbt);
        //alert.set
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String length=widthet.getText().toString();
                lengthtoLine(Float.parseFloat(length));
                //  drawingPannel.drawMannually(10,300,400,300);
                alert.dismiss();
            }
        });
        alert.show();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        zoomableImageView.setImageBitmap(transferClass.getMybitmap());
    }

    public void analyze_Structure_OnClick(View view)
    {
        if(jointList.size()==3&& displacementList.size()==3&& loadsList.size()==3) {
            final String[] ResultString = new String[1];
            double E = 200e6;
            double I = 22e-6;
            Member tempMember;
            for (int i = 0; i < (jointList.size() - 1); i++) {
                tempMember = new Member(jointList.get(i), jointList.get(i + 1));
                tempMember.setE(E);
                tempMember.setI(I);
                membersList.add(tempMember);
            }
            Model model = new Model();
            model.getMemberAl().add(membersList.get(0));
            model.getMemberAl().add(membersList.get(1));
            model.jZLoads(jointList.get(0), loadsList.get(0));
            model.jZLoads(jointList.get(1), loadsList.get(1));
            model.jZLoads(jointList.get(2), loadsList.get(2));

            model.jYDisp(jointList.get(0), displacementList.get(0));
            model.jYDisp(jointList.get(1), displacementList.get(1));
            model.jYDisp(jointList.get(2), displacementList.get(2));
            Compute c = new Compute(model);
            ResultString[0] = c.getPrintingString();
            //modeldetatils=new ModelDetails(ResultString[0],jointList,mCoordinatesList,loadsList,displacementList,zoomableImageView.getPhotoBitmap());
            Intent i =new Intent(DrawingPlatform.this,AnalyzedModel.class);
//            Bitmap bmp1=zoomableImageView.getPhotoBitmap();
//            Bitmap bmp2=bmp1.copy(bmp1.getConfig(),true);
            transferClass.setMybitmap(zoomableImageView.getPhotoBitmap());
         //   transferClass.setMybitmap(bmp2);
           // i.putExtra("bitmap",zoomableImageView.getPhotoBitmap());
            i.putExtra("reactions", c.getReactions());
            i.putExtra("displacments",c.getDisplacments());
            i.putExtra("resultString", ResultString[0]);
            modeldetatils=new ModelDetails(jointList,mCoordinatesList,loadsList,displacementList);
            i.putExtra("ModelDetails", (Serializable) modeldetatils);
            autodraw=true;
            startActivity(i);
            finish();
//            Intent intent = new Intent(DrawingPlatform.this, Analyze.class);
//            intent.putExtra("resultString", ResultString[0]);
//            startActivity(intent);
        }
        else
        {
            Toast.makeText(DrawingPlatform.this,"soorry enter the support & Loads & displacments First",Toast.LENGTH_SHORT).show();
        }
//        final Dialog alertAnalyze = new Dialog(DrawingPlatform.this);
//        alertAnalyze.setContentView(R.layout.analyze_popup_layout);
//        final EditText load1,load2,load3,displac1,displac2,displac3;
//        alertAnalyze.setTitle("model to anlyze");
//        load1=(EditText)alertAnalyze.findViewById(R.id.load_no1);
//        load2=(EditText)alertAnalyze.findViewById(R.id.load_no2);
//        load3=(EditText)alertAnalyze.findViewById(R.id.load_no3);
//        displac1=(EditText)alertAnalyze.findViewById(R.id.displacement_no1);
//        displac2=(EditText)alertAnalyze.findViewById(R.id.displacement_no2);
//        displac3=(EditText)alertAnalyze.findViewById(R.id.displacement_no3);
//        Button analyzeResultbtn=(Button)alertAnalyze.findViewById(R.id.analyzeModel);
//        final Model model=new Model();
//        analyzeResultbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DrawingPlatform.this, "Its clicked working", Toast.LENGTH_SHORT).show();
//                model.getMemberAl().add(membersList.get(0));
//                model.getMemberAl().add(membersList.get(1));
//                model.jZLoads(jointList.get(0),Double.parseDouble(load1.getText().toString()));
//                model.jZLoads(jointList.get(1), Double.parseDouble(load2.getText().toString()));
//                model.jZLoads(jointList.get(2), Double.parseDouble(load3.getText().toString()));
//
//                model.jYDisp(jointList.get(0), Double.parseDouble(displac1.getText().toString()));
//                model.jYDisp(jointList.get(1), Double.parseDouble(displac2.getText().toString()));
//                model.jYDisp(jointList.get(2), Double.parseDouble(displac3.getText().toString()));
//                alertAnalyze.dismiss();
//                Compute c=new Compute(model);
//                ResultString[0] =c.getPrintingString();
//        Intent intent = new Intent(DrawingPlatform.this,analyze.class);
//        intent.putExtra("resultString", ResultString[0]);
//        startActivity(intent);
//            }
//
//        });
//        alertAnalyze.show();

       // Model model=new Model();
//        model.getMemberAl().add(membersList.get(0));
//        model.getMemberAl().add(membersList.get(1));
//
//        model.jZLoads(jointList.get(0), -4);
//        model.jZLoads(jointList.get(1), 0);
//        model.jZLoads(jointList.get(2), 4);
//
//        model.jYDisp(jointList.get(0), 0);
//        model.jYDisp(jointList.get(1), -0.0015);
//        model.jYDisp(jointList.get(2), 0);

    }



    //for select
    public void selectionOnClick(View view)
    {
        Bitmap tempBitmap =null;
//        tempBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        if(selectOption)
        {
            drawableBitmap=tempBitmap;
            zoomableImageView.setImageBitmap(drawableBitmap);
            Toast.makeText(DrawingPlatform.this,"Selection off",Toast.LENGTH_LONG).show();
            selectOption=false;
            selcetionClass.isSelect=false;
            zoomableImageView.isSelection=false;
        }
        else
        {
            tempBitmap=drawableBitmap;
            selcetionClass=new SelcetionClass(context,zoomableImageView,drawableBitmap,jointList,mCoordinatesList);
            selcetionClass.isSelect=true;
            zoomableImageView.setSelectclass(selcetionClass);
            zoomableImageView.isSelection=true;
            selectOption=true;
            Toast.makeText(DrawingPlatform.this,"Selection On",Toast.LENGTH_LONG).show();
        }

    }

    public void modelDetailsOnClick(View view)
    {
        openmodeldetailsActivity();
    }

    public void openmodeldetailsActivity()
    {
        modeldetatils=new ModelDetails(jointList,mCoordinatesList,loadsList,displacementList);
        Intent i = new Intent(this,ModelDetailsActivity.class);
        i.putExtra("ModelDetails", (Serializable) modeldetatils);
        autodraw=true;
        startActivity(i);
        finish();
    }

    public void selctionMethod()
    {
        Toast.makeText(DrawingPlatform.this,"done",Toast.LENGTH_LONG).show();
    }




    //for graph

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Bitmap setGraph(Bitmap mbitmap)
    {
        Canvas canvas = new Canvas(mbitmap);
        // set drawing colour
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        p.setStrokeWidth(4.5f);

        //
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//
//        Display display = getWindowManager().getDefaultDisplay();
//        int width = display.getWidth();  // deprecated
//        int height = display.getHeight();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
//        int width = size.x;
//        int height = size.y;  // not working isnt covering overall screen
//        int width = 1750;
//        int height = 1900;
        int width = drawableBitmap.getWidth();
        int height = drawableBitmap.getHeight();
        boolean v_center=false,h_center=false;


        float x_st_v=0,y_st_h=0;
        for (int i=0;i<=(width/20);i++)
        {

            //code for bold line of center vertically
            if (x_st_v>=200&&v_center==false)
            {
                p.setStrokeWidth(12f);
                v_center = true;
                canvas.drawLine(x_st_v,0,x_st_v,height,p);
                x_st_v=x_st_v+50f;
                p.setStrokeWidth(4.5f);
            }
            //draw default graph lines
            else
            {
                canvas.drawLine(x_st_v,0,x_st_v,height,p);
                x_st_v=x_st_v+50f;
            }
            //in case width is achived and loop is running get out of the loop
            if (x_st_v>=width)
            {
                break;
            }
        }
        //code for horizontal lines of graph
        for (int i=0;i<=(height/20);i++)
        {
            //code for center horizontal line
            if(y_st_h>=700&&h_center==false)
            {
                p.setStrokeWidth(12f);
                h_center=true;
                canvas.drawLine(0,y_st_h,width,y_st_h,p);
                y_st_h=y_st_h+50f;
                p.setStrokeWidth(4.5f);
            }
            //run default code for horizontal lines.
            else
            {
                canvas.drawLine(0, y_st_h, width, y_st_h, p);
                y_st_h = y_st_h + 50f;
            }
            //in case width is achived and loop is running get out of the loop
            if (y_st_h>=height)
            {
                break;
            }

        }

        return drawableBitmap;
    }

    //length to line method scaling here
    public void lengthtoLine(float length)
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


    //Auto Draw
//    public Bitmap auto_draw(Bitmap mbitmap)
//    {
//
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawing_platform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //code for drawer pannel
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.create_new) {
            // Handle the camera action
        } else if (id == R.id.open_existing) {

        } else if (id == R.id.save_prj) {

        } else if (id == R.id.settings_file) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
