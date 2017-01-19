package com.pallefire.b_34asynctaskexam1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button start;
    ProgressBar progressBar;
    TextView textView;

    //create the one inner class for async task

    public class MyTask extends AsyncTask<Integer,Integer,Float>{
        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "i am here in onpreexecute", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected Float doInBackground(Integer... p1) {
            float sum=0;
            Log.d("BATCH 34",""+p1[0]);
           // Toast.makeText(MainActivity.this, "p1="+p1[0], Toast.LENGTH_SHORT).show();
            for(int i=10;i<=100;i=i+10){
                sum=sum+i;
                publishProgress(i);
                try {

                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return sum;// will go to onpost execute
        }
        @Override
        protected void onProgressUpdate(Integer... p2) {
            progressBar.setProgress(p2[0]);
            //here we can pass the multiple value because of array so if we want to print then give the positions also
           // Toast.makeText(MainActivity.this, "progressing..."+p2[0], Toast.LENGTH_SHORT).show();
            super.onProgressUpdate(p2);
        }

        @Override
        protected void onPostExecute(Float p3) {
          //  Toast.makeText(MainActivity.this, "sum="+p3, Toast.LENGTH_SHORT).show();
            textView.setText(p3.toString());
            // textView.setText("");
            super.onPostExecute(p3);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.click);
        progressBar = (ProgressBar)findViewById(R.id.progressbar1);
        textView = (TextView)findViewById(R.id.tv1);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                MyTask myTask=new MyTask();
                myTask.execute(10);

            }
        });
    }

    }
