package com.example.khwajabilal.structural_a8;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.khwajabilal.structural_a8.Beam.Joint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by KhwajaBilal on 7/17/2016.
 */
public class ModelDetails implements Serializable {
    ArrayList<Joint> jointList = new ArrayList<>();
    ArrayList<Coordinates> mCoordinatesList = new ArrayList<>();
    ArrayList<Float> loadsList=new ArrayList<>();
    ArrayList<Float> displacementList=new ArrayList<>();
    Bitmap drawableBitmap;
    String detailedreport;
    double[][] displacments,reactions;

    ModelDetails()
    {

    }

    public ModelDetails(ArrayList<Joint> jointList, ArrayList<Coordinates> mCoordinatesList, ArrayList<Float> loadsList, ArrayList<Float> displacementList) {
        this.jointList = jointList;
        this.mCoordinatesList = mCoordinatesList;
        this.loadsList = loadsList;
        this.displacementList = displacementList;
      //  this.drawableBitmap = drawableBitmap;
    }

    public ModelDetails(Parcel in) {
        this.detailedreport=in.readString();
        //this.jointList=in.re
    }

    public void setJointList(ArrayList<Joint> jointList) {
        this.jointList = jointList;
    }

    public void setmCoordinatesList(ArrayList<Coordinates> mCoordinatesList) {
        this.mCoordinatesList = mCoordinatesList;
    }

    public void setLoadsList(ArrayList<Float> loadsList) {
        this.loadsList = loadsList;
    }

    public void setDisplacementList(ArrayList<Float> displacementList) {
        this.displacementList = displacementList;
    }

    public void setDrawableBitmap(Bitmap drawableBitmap) {
        this.drawableBitmap = drawableBitmap;
    }

    public ArrayList<Joint> getJointList() {

        return jointList;
    }

    public ArrayList<Coordinates> getmCoordinatesList() {
        return mCoordinatesList;
    }

    public ArrayList<Float> getLoadsList() {
        return loadsList;
    }

    public ArrayList<Float> getDisplacementList() {
        return displacementList;
    }

    public Bitmap getDrawableBitmap() {
        return drawableBitmap;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeArray(jointList.toArray());
//        dest.writeArray(mCoordinatesList.toArray());
//        dest.writeArray(displacementList.toArray());
//        dest.writeArray(loadsList.toArray());
//        dest.writeParcelable(getDrawableBitmap(),PARCELABLE_WRITE_RETURN_VALUE);
//        dest.writeString(detailedreport);
////        dest.writeDoubleArray(displacments);
////        dest.writeDoubleArray(reactions);
//
//
//    }
    public static final Parcelable.Creator<ModelDetails> CREATOR = new Parcelable.Creator<ModelDetails>() {
        public ModelDetails createFromParcel(Parcel in) {
            return new ModelDetails(in);
        }

        public ModelDetails[] newArray(int size) {
            return new ModelDetails[size];

        }
    };
}
