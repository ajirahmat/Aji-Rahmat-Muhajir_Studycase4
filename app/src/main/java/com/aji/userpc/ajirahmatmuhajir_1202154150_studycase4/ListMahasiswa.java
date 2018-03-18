package com.aji.userpc.ajirahmatmuhajir_1202154150_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {

    //deklarasi variable
    public ListView listView;
    Button button;
    ArrayAdapter adapter;
    ArrayList<String> list;
    ProgressDialog progress;

    //inisiasi variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        listView = (ListView)findViewById(R.id.listView);
        button = (Button)findViewById(R.id.button);

    }

    //class asynctask
    //contructor
    //menginisiasi variabel
    public class AsyncTask extends android.os.AsyncTask<String, Integer, String> {

        public AsyncTask(ListView listView){

            progress = new ProgressDialog(ListMahasiswa.this);
            list = new ArrayList<>();

        }

        //method preExecuted
        //title progress dialog
        //ukuran minimal progres
        //ukuran maksismal dari progress
        //menampilkan progress

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progress.setTitle("Loading");
            progress.setIndeterminate(false);
            progress.setProgress(0);
            progress.setMax(100);

            progress.setCancelable(true);
            progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progress.dismiss();
                    AsyncTask.this.cancel(true);
                }
            });
            progress.show();
        }


        //method AsyncTask doinbackground
        //inisiasi adapter
        //mengambil array
        //membuat pengkondisian untuk menyimpan arrah ke dalam variabel a
        //inisiasi kondisi
        //membuat formula untukj presentase progress setaip item array
        //mengatur waktu delay
        //menambahkan item ke dalam variabel yang memuat arraylist

        @Override
        protected String doInBackground(String... strings){
            adapter = new ArrayAdapter<>(ListMahasiswa.this, android.R.layout.simple_list_item_1, list);

            String [] data = getResources().getStringArray(R.array.mahasiswa);

            for (int i=0; i<data.length; i++){
                final long proses = 100*i/data.length;
                final String nama = data[i];
                try{
                    Runnable pesan = new Runnable() {
                        @Override
                        public void run() {
                            progress.setMessage((int)proses+"% - Adding - "+nama);
                        }
                    };
                    runOnUiThread(pesan);
                    Thread.sleep(300);
                    list.add(data[i]);

                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        //method onPost Execute
        //setting adapter
        //menutup progres bar
        @Override
        protected void onPostExecute (String result){
            super.onPostExecute(result);
            listView.setAdapter (adapter);
            progress.dismiss();
        }
    }

    //method onclick
    //menjalankan AsyncTask pada listview
    public void onClick (View view){
        new AsyncTask(listView).execute();
    }
}
