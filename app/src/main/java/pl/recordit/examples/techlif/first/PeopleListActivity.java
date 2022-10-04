package pl.recordit.examples.techlif.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeopleListActivity extends AppCompatActivity {
    ListView peopleListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);
        peopleListView = findViewById(R.id.peopleListView);
        peopleListView.setAdapter(
                new PeopleListAdapter(
                        getList()
                        ,
                        this.getLayoutInflater()
                )
        );

    }

    private List<PersonModel> getList(){
        List<PersonModel> result = new ArrayList<>();
        PersonModel person = new PersonModel();
        person.name = "Adam Kowalski";
        person.isParent = true;
        person.birthDate = LocalDate.of(2000,10, 10);
        result.add(person);
        PersonModel person1 = new PersonModel();
        person1.name = "Ewa Nowak";
        person1.isParent = false;
        person1.birthDate = LocalDate.of(1995,11, 11);
        result.add(person1);
        PersonModel person2 = new PersonModel();
        person2.name = "Karol Noga";
        person2.isParent = false;
        person2.birthDate = LocalDate.of(1993,1, 21);
        result.add(person2);
        return result;
    }
}