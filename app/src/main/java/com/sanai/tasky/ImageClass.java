package com.sanai.tasky;

//ok

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImageClass {



    public void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName ) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/tasky/");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/tasky/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/tasky/"), fileName);
        if (file.exists()) {
            file.delete();
            // return "delete";
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //return "add";
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///////////////////////////////////////////////////////////////////////
    public Bitmap loadImageFromStorage(String path , String folder)

    {

        return  BitmapFactory.decodeFile("/sdcard/tasky/"+path);

    }

////////////////////////////////////////////////////////////////////////


   //need
    public static byte[] loadImageFromStorage1(String path ){

        try {
            FileInputStream fis = new FileInputStream("/sdcard/tasky/"+path);
            byte[] image=new byte[fis.available()];
            fis.read(image);



            fis.close();

            //Toast.makeText(this,"insert successfull ",Toast.LENGTH_LONG).show();
            return image;
        }
        catch (IOException e ){
            e.printStackTrace();
            return null;
        }

    }


    //need
    public static Bitmap byteArrayToBitMap(byte[] encodedString) {
        try {
            //byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodedString, 0, encodedString.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}
