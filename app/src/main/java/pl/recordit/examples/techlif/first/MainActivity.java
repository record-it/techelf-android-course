package pl.recordit.examples.techlif.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class ProperNameTextView extends TextView{
    String name;
    public ProperNameTextView(Context context) {
        super(context);
    }

    public void setName(CharSequence name){
        String str = name.toString();
        setText(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
    }
}
interface Sender{
    String prefix = "Mr.";
    void send(TextView view);
    default void sendMessage(String message, TextView view){
        send(view);
    }
}

class EmailSender implements Sender{
    String message = "";
    final TextView view;

    EmailSender(TextView view) {
        this.view = view;
    }

    @Override
    public void send(TextView view) {
        view.setText(message);
    }

    public void setMessage(String message) {
        this.message = message;
        sendMessage(this.message, view);
    }
}

//Zdefiniuj interfejs ABC z metodą XYZ zwracająca łańcuch i z parametrami: łańcuch i liczba int
@FunctionalInterface
interface ABC{
    String XYZ(String str, int n);
}

class SimpleABC implements ABC{

    @Override
    public String XYZ(String str, int n) {
        String result = "";
        for(int i = 0; i < n; i++){
            result += str;
        }
        return result;
    }
}
//Zaimplementuj interfejs ABC w postaci klasy. Matoda XYZ powinna zwracać łańcuch złożony
// z n powtórzeń parametru łańcuchowego
interface Operation {
    double function(double a, double b);
}

public class MainActivity extends AppCompatActivity{
    private TextView message;
    private EmailSender sender;
    private ABC reapeter;
    private Button createActivityButton;
    private Button constraintActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = findViewById(R.id.message);
        createActivityButton = findViewById(R.id.create_activity_button);
        constraintActivityButton = findViewById(R.id.constraint_activity_button);
        MainActivity mainActivity = this;
        createActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, CreateActivity.class);
                intent.putExtra("message", "Run from MainActivity");
                intent.putExtra("counter", 10);
                startActivity(intent);
            }
        });
        constraintActivityButton.setOnClickListener(v ->
                startActivity(new Intent(this, PeopleListActivity.class))
        );




        //interfaceAndLambdaExercises();

    }

    private void interfaceAndLambdaExercises() {
        reapeter = new SimpleABC();
        sender = new EmailSender(message);
        //sender.setMessage(reapeter.XYZ("abc", 3));
        //klasa anonimowa
        reapeter = new ABC() {
            @Override
            public String XYZ(String str, int n) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < n; i++){
                   sb.append(str);
                }
                return sb.toString();
            }
        };
        sender.setMessage(reapeter.XYZ("abc", 3));
        reapeter = (String str, int n) -> {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++){
                sb.append(str);
            }
            return sb.toString();
        };
        //Stwórz lambdę dodającą a + b jako implementacja interfejsu Operation
        Operation add = (a, b) -> a + b;
        BiFunction<Double, Double, Double> funcAdd = (a, b) -> a + b;
        Predicate<String> fourLetterStringPredicate = str -> str.length() == 4;
        Consumer<String> displayInLog = str -> Log.i("APP", str);
        Supplier<String> singleNameGenerator = () -> "Beata";
        Operation mul = (a, b) -> a * b;
        add.function(4, 5);
        mul.function(4, 5);
        List<String> names = new ArrayList<>();
        names.add("Karol");
        names.add("Ewa");
        names.add("Adam");
        names.add("Robert");
        List<String> fourLettersNames = new ArrayList<>();
        for(String name: names){
            if(name.length() == 4){
                fourLettersNames.add(name);
            }
        }
        ;
        //w fourLetterNames mamy wyszukane imiona czteroliterowe
        List<String> namesWithA = new ArrayList<>();
        for(String name: names){
            if(name.startsWith("A")){
                namesWithA.add(name);
            }
        }
        ;
        List<String> strings = names.stream()
                .filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
    }
}