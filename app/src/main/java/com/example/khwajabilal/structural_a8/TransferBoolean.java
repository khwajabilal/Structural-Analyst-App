package com.example.khwajabilal.structural_a8;

/**
 * Created by KhwajaBilal on 8/5/2016.
 */
public class TransferBoolean {
    static boolean autodraw = false;

    public static boolean isAutodraw() {
        return autodraw;
    }

    public static void setAutodraw(boolean autodraw) {

        TransferBoolean.autodraw = autodraw;
    }
}
