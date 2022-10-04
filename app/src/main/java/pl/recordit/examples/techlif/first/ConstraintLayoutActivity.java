package pl.recordit.examples.techlif.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

public class ConstraintLayoutActivity extends AppCompatActivity {
    private EditText timeEditText;
    private SwitchCompat switchParent;
    private RadioGroup radioGroupColors;
    private Spinner spinnerCity;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        timeEditText = findViewById(R.id.editTextTime);
        switchParent = findViewById(R.id.switch1);
        radioGroupColors = findViewById(R.id.radioGroup);
        spinnerCity = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.button);

        timeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("APP", "Text changed " + charSequence.toString());
                String input = charSequence.toString();
                if (!(input.length() <= 2 && input.chars().allMatch(c -> Character.isDigit(c)))){
                    timeEditText.setText(input.substring(0, input.length() - 1));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        submitButton.setOnClickListener(v -> {
            Log.i("APP", "Is Parent " + switchParent.isChecked());
            Log.i("APP", "Selected color " + radioGroupColors.getCheckedRadioButtonId());
            Log.i("APP", "Selected city " + spinnerCity.getSelectedItem().toString());

        });
    }
}