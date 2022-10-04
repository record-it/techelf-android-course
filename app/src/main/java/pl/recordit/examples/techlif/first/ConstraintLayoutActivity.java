package pl.recordit.examples.techlif.first;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.material.navigation.NavigationBarView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConstraintLayoutActivity extends AppCompatActivity {
    private EditText timeEditText;
    private EditText nameEditText;
    private SwitchCompat switchParent;
    private RadioGroup radioGroupColors;
    private Spinner spinnerCity;
    private Button submitButton;
    private List<String> cities = new ArrayList<>();
    private List<PersonModel> people = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        timeEditText = findViewById(R.id.editTextTime);
        nameEditText = findViewById(R.id.editTextTextPersonName);
        switchParent = findViewById(R.id.switch1);

        radioGroupColors = findViewById(R.id.radioGroup);
        spinnerCity = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.button);
        cities.add("Łódź");
        cities.add("Kalisz");
        cities.add("Gdańsk");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                cities
        );

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("APP", "Selected city  " + cities.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);
        timeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("APP", "Text changed " + charSequence.toString());
//                String input = charSequence.toString();
//                if (!(input.length() <= 2 && input.chars().allMatch(c -> Character.isDigit(c)))){
//                    timeEditText.setText(input.substring(0, input.length() - 1));
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        submitButton.setOnClickListener(v -> {
            PersonModel person = new PersonModel();
            person.isParent = switchParent.isChecked();
            person.name = nameEditText.getText().toString();
            try {
                person.birthDate = LocalDate.parse(timeEditText.getText().toString());
                people.add(person);
            } catch (Exception e){
                //obsług abłędu i zatrzymanie zapisu
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Błąd danych")
                        .setMessage("Błędny format daty!!! Data w formacie rrrr-mm-dd")
                        .setPositiveButton("OK", (inter, i) -> {

                        }).show();
                return;
            }
            Log.i("APP", "Is Parent " + switchParent.isChecked());
            Log.i("APP", "Selected color " + radioGroupColors.getCheckedRadioButtonId());
            Log.i("APP", "Selected city " + spinnerCity.getSelectedItem().toString());

        });
    }
}