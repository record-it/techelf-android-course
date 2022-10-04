package pl.recordit.examples.techlif.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    private static final String TAG = "APP";
    private Button sendEmailButton;
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
        sendEmailButton = findViewById(R.id.sendEmailButton);
        sendEmailButton.setOnClickListener(v -> {
            Intent actionIntent = new Intent(Intent.ACTION_SENDTO);
            actionIntent.setData(Uri.parse("mailto:boss@techelf.pl"));
            actionIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
            actionIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
            actionIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(actionIntent, "Wybierz klienta poczty"));
        });
        //Dodaj przycisk Wyświetl pierwszą osobę z ksiązki telefonicznej lub połącz z numerem
        //w obłudze przycisku uruchom intencję typu ACTION_DIAL z URI tel:123
        //lub akcję typu ACTION_VIEW z URI content://contacts/people/1
        //lub ACTION_VIEW z adresem strony w URI
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data","1234");
        Log.i(TAG, "Data saved ");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String data = savedInstanceState.getString("data");
        Log.i(TAG, "Restored data: " + data);
    }
}