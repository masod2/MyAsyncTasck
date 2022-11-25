package com.example.myasynctasck;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import com.example.myasynctasck.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(v -> {
            MyAsync myAsync = new MyAsync();
            myAsync.execute();
        });
    }

    class MyAsync extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(1000);
                publishProgress();


            }
            return null;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textView1.setVisibility(View.VISIBLE);
            binding.progressBar1.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            binding.button1.setVisibility(View.GONE);
            binding.progressBar1.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            binding.progressBar1.incrementProgressBy(10);
            binding.textView2.setText(binding.textView2.getText() + "\n     *");
            binding.textView1.setText("progress "+binding.progressBar1.getProgress());


        }
    }
}

