package com.kuan.retrofitrxjavacamrea;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private static String BASE_FILE_PATH="";
    public static String PICTURE_FILE_PATH="";
    private static final int REQUEST_CODE_PERMISSION = 1;
    // Storage Permissions
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        boolean avialbe_permission = true;
        // For API 23+ you need to request the read/write permissions even if they are already in your manifest.
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M ) {
            avialbe_permission = verifyPermissions(this);
        }
        if (avialbe_permission && null == savedInstanceState) {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                BASE_FILE_PATH = Environment.getExternalStorageDirectory().toString()+"/com.kuan.application/camera";
            }else{
                BASE_FILE_PATH = Environment.getDataDirectory().toString()+"/com.kuan.application/camera";
            }
            File file=new File(BASE_FILE_PATH);
            if(!file.exists()){
                file.mkdirs();
            }
            File f=new File(BASE_FILE_PATH+"/picture");
            if(!f.exists()){
                f.mkdirs();
            }
            PICTURE_FILE_PATH=f.getAbsolutePath();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }

    }
    private static boolean verifyPermissions(Activity activity) {
        // Check if we have write permission
        int write_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_persmission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int camera_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (write_permission != PackageManager.PERMISSION_GRANTED ||
                read_persmission != PackageManager.PERMISSION_GRANTED ||
                camera_permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_REQ,
                    REQUEST_CODE_PERMISSION
            );
            return false;
        } else {
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            // Restart it after granting permission
            if (requestCode == REQUEST_CODE_PERMISSION) {
                finish();
                startActivity(getIntent());
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

}