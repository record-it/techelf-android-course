package pl.recordit.examples.techlif.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    private static final String TAG = "APP";

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Activity visible");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Activity not visible");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Activity paused");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "Back pressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activity destroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity resumed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        int counter = intent.getIntExtra("counter", 0);
        Log.i(TAG, "Wiadomość z aktywności:" + message);
        Toast
                .makeText(this, "Wartość licznika " + counter, Toast.LENGTH_SHORT)
                .show();
    }
}