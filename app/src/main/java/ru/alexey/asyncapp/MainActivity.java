package ru.alexey.asyncapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextForInsert;
    Button buttonForInsert;
    TextView textViewInsert;
    Button buttonCalc;
    TextView textViewResult;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextForInsert = findViewById(R.id.general_thread_gui_edit);
        textViewInsert = findViewById(R.id.general_thread_gui_text);
        textViewResult = findViewById(R.id.async_result);
        progressBar = findViewById(R.id.progressBar);

        buttonForInsert = findViewById(R.id.insert_text);
        buttonForInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str;
                str = editTextForInsert.getText().toString();
                textViewInsert.setText(str);
            }
        });

        buttonCalc = findViewById(R.id.calculate);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateAsync calculateAsync = new CalculateAsync();
                calculateAsync.execute(50000);
            }
        });
    }

    class CalculateAsync extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int s = 1;
            for (int j = 0; j < integers[0]; j++) {
                s = j^2 - j*2;
                int value = (j * 100 / integers[0]);
                publishProgress(value);
            }
            return s;
        }

        @Override
        protected void onPostExecute(final Integer integer) {
            super.onPostExecute(integer);
                    textViewResult.setText(String.valueOf(integer));
        }
    }
}
