package com.example.khwajabilal.structural_a8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khwajabilal.structural_a8.Beam.Joint;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelDetailsActivity extends AppCompatActivity {

    TransferBoolean transferBoolean;

    ModelDetails modelDetails;
    ArrayList<Joint> jointList = new ArrayList<>();
    ArrayList<Coordinates> mCoordinatesList = new ArrayList<>();
    ArrayList<Float> loadsList=new ArrayList<>();
    ArrayList<Float> displacementList=new ArrayList<>();
    Bitmap drawableBitmap;



    // private ArrayList<String> arrText = new ArrayList<>();
    private String[] arrText =
            new String[]{"Enter the length", "Add joints", "Add joints", "Add joints", "Add load", "Add load", "Add load", "Add displacment", "Add displacment", "Add displacment"};
    private String[] arrTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_details);
        Intent i = getIntent();
        transferBoolean=new TransferBoolean();
        modelDetails=(ModelDetails)i.getSerializableExtra("ModelDetails");
        //setTexts();
       mCoordinatesList= modelDetails.getmCoordinatesList();
        jointList=modelDetails.getJointList();
        Log.d("list trasferd", String.valueOf(jointList.size()));
        loadsList=modelDetails.getLoadsList();
        displacementList=modelDetails.getDisplacementList();
        Log.d("list trasferd", String.valueOf(mCoordinatesList.get(0).getX_Axis_Start()));
        arrTemp = new String[arrText.length];

        this.setValues();
        MyListAdapter myListAdapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.c_listview_layout);

        listView.setAdapter(myListAdapter);

//        ListUtils.setDynamicHeight(listView);
//        ListUtils.setDynamicHeight(listView1);
    }
    public void setValues()
    {
        for (int i=0;i<10;i++)
        {
            if(i==0)
            {
                arrTemp[i]= String.valueOf((mCoordinatesList.get(i).getX_Axis_End()-200)/50);
            }
            else if(i<=3)
            {
                arrTemp[i]=String.valueOf(jointList.get(i-1).getX());
            }
            else if (i<=6)
            {
                arrTemp[i]= String.valueOf(loadsList.get(i-4));
            }
            else if (i<=9)
            {
                arrTemp[i]= String.valueOf(displacementList.get(i-7));
            }
        }
    }




    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (arrText != null && arrText.length != 0) {
                return arrText.length;
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arrText[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //ViewHolder holder = null;
            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                LayoutInflater inflater = ModelDetailsActivity.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.custom_listview_modeldetail, null);
                holder.textView1 = (TextView) convertView.findViewById(R.id.c_r_l_textView);
                holder.editText1 = (EditText) convertView.findViewById(R.id.c_r_l_edittext);
                holder.button1 = (Button) convertView.findViewById(R.id.c_r_l_okbtn);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.ref = position;

            holder.textView1.setText(arrText[position]);
            holder.editText1.setText(arrTemp[position]);
            holder.editText1.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    arrTemp[holder.ref] = arg0.toString();
                }
            });
            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ModelDetailsActivity.this,"I am here ",Toast.LENGTH_LONG).show();
                    for (int i=0;i<arrTemp.length;i++)
                    {
                        if(i==0)
                        {
                            mCoordinatesList.get(i).setX_Axis_End((Float.parseFloat(arrTemp[i])*50)+200);
                        }
                        else if(i<=3)
                        {
                            jointList.get(i-1).setX(Float.parseFloat(arrTemp[i]));
                        }
                        else if(i<=6)
                        {
                            loadsList.set(i-4,Float.parseFloat(arrTemp[i]));
                        }
                        else if(i<=9)
                        {
                            displacementList.set(i-7,Float.parseFloat(arrTemp[i]));
                        }
                        Log.d("listitems",arrTemp[i]);
                    }
                    onBackPressed();
                }
            });

            return convertView;
        }

        private class ViewHolder {
            TextView textView1;
            EditText editText1;
            Button button1;
            int ref;
        }


    }
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

    @Override
    public void onBackPressed() {
        modelDetails=new ModelDetails(jointList,mCoordinatesList,loadsList,displacementList);
        transferBoolean.setAutodraw(true);
        Intent i =new Intent(this,DrawingPlatform.class);
        i.putExtra("updatedModel", (Serializable) modelDetails);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
