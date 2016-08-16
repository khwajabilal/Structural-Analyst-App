package com.example.khwajabilal.structural_a8;

import android.graphics.Bitmap;

/**
 * Created by KhwajaBilal on 7/18/2016.
 */
public class TransferClass {
    static Bitmap mybitmap;

    public static Bitmap getMybitmap() {
        return mybitmap;
    }

    public static void setMybitmap(Bitmap mybitmap) {

        TransferClass.mybitmap = mybitmap;
    }
}
