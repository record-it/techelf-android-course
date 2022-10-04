package pl.recordit.examples.techlif.first;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PeopleListAdapter extends BaseAdapter {
    private final List<PersonModel> people;
    private final LayoutInflater inflater;

    public PeopleListAdapter(List<PersonModel> people, LayoutInflater inflater) {
        this.people = people;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int i) {
        return people.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.poeple_list_item, null);
        }
        TextView editName = view.findViewById(R.id.list_personName);
        TextView editDate = view.findViewById(R.id.list_birthDate);
        CheckBox parentBox = view.findViewById(R.id.list_isParent);
        Log.i("APP", people.get(i).toString());
        editDate.setText(people.get(i).birthDate.toString());
        editName.setText(people.get(i).name);
        parentBox.setChecked(people.get(i).isParent);
        return view;
    }
}
