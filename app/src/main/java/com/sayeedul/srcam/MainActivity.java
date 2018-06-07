package com.sayeedul.srcam;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CAPTURE = 1;
    ImageView resultPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button click =(Button)findViewById(R.id.imageButton);
        resultPhoto = (ImageView)findViewById(R.id.photo);

        if(!hasCamera())
        {
            click.setEnabled(false);
        }

    }

    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==REQUEST_CAPTURE && resultCode == RESULT_OK)
       {
           Bundle bundle = data.getExtras();
           Bitmap bitmap = (Bitmap)bundle.get("data");
           resultPhoto.setImageBitmap(bitmap);
       }
    }
}
