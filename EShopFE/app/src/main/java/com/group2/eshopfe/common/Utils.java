package com.group2.eshopfe.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

public class Utils {
    private Utils(){

    }
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
    public static Bitmap convertBytesToBitMap(byte[] data){
        byte[] image = Utils.decompressImage(data);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, data.length);
        return bitmap;
    }
}
