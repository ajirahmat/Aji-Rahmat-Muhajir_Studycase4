package com.aji.userpc.ajirahmatmuhajir_1202154150_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CariGambar extends AppCompatActivity {

    //deklarasi variable
    private ImageView ImageView;
    private ProgressDialog progress;
    private EditText editText;

    //Inisiasi variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        ImageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);

    }

    //method onclick, menalannkan asynctask
    //mengambil url pada editext
    //menjalankan asynctask
    public void onClick(View view) {

        String url = editText.getText().toString();


        if(url.isEmpty()){
            Toast.makeText(CariGambar.this,"Masukkan URL gambar terlebih dahulu",Toast.LENGTH_SHORT).show();
        }else {
            new ImageAsyncTask().execute(url);
        }
    }

    //class imageaasynctask
    private class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //onpreExceute, menjalaankan progress dialog
        //inisiasi progress dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress = new ProgressDialog(CariGambar.this);
            progress.setTitle("Search Image");
            progress.setIndeterminate(false);
            progress.show();// show progress dialog
        }

        //doInbackground
        //mengambilinputan url image
        //menjalankan bitmap
        @Override
        protected Bitmap doInBackground(String... URL) {

            String urlImage = URL[0];

            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(urlImage).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //onPostExecute
        // set bitmap ke ImageView
        // tutup dialog
        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView.setImageBitmap(result);
            progress.dismiss();
        }
    }
}