package com.example.takenogaku.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.util.Log.v;

//import android.text.format.DateFormat;

public class Util {



	public static int changePxtoPd (Context context, int px) {
		
		// Convert the dips to pixels  
        float scale = context.getResources().getDisplayMetrics().density;  
        int mGestureThreshold = (int) (px * scale + 0.5f);  
        return mGestureThreshold;
		
	}


    /**
     * @param bm
     */
    public int bitmapFormCheck(Bitmap bm) {

        int _w = bm.getWidth();
        int _h = bm.getHeight();

        int res = 0;
        if (_h == _w) {
            res = 1;//正方形
        } else if (_h > _w) {
            res = 2;//縦長
        } else {
            res = 3;//横長

        }
        return res;

    }

        /**
         * trim square
         * @param bm
         * @param w
         * @param h
         */
    public Bitmap bitmapTrimSquare(Bitmap bm,int w,int h) {

        int _w = bm.getWidth();
        int _h = bm.getHeight();

        int left = (_w/2)-(w/2);
        int top = (_h/2)-(h/2);

        BitmapTrim bitmapTrim = new BitmapTrim(w, h);
        bitmapTrim.setTrimRect(new RectF(0, 0, w, h));

        int _top = -top;
        int _left = -left;

        bitmapTrim.drawBitmap(bm, _left,_top);
        Bitmap bitmap = bitmapTrim.getBitmap();
        return bitmap;

    }

    /*
	 * 画像を回転させる
	 */
    public Bitmap RotateImg(Bitmap bitmapOrg ,int angle) {

        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
        Bitmap bitmap;
        //縦長の場合
        if (width > height) {
            // createa matrix for the manipulation
            Matrix matrix = new Matrix();
            // rotate the Bitmap
            matrix.postRotate(angle);
            // recreate the new Bitmap
            bitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,width, height, matrix, true);
            v("orienta",":"+width+":"+height);

        } else {
            v("orienta","::"+width+":"+height);
            bitmap = bitmapOrg;
        }
        bitmapOrg=null;
        return bitmap;
    }


    public byte[] changeBitmapToByte (Bitmap bm) {

        byte[] _bArray = null;
        try {
            ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, baoStream);

            baoStream.flush();
            _bArray = baoStream.toByteArray();
            baoStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return _bArray;

    }


}