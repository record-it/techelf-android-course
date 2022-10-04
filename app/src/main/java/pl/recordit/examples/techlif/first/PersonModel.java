package pl.recordit.examples.techlif.first;

import java.time.LocalDate;

public class PersonModel {
    public String name;
    public boolean isParent;
    public LocalDate birthDate;

    @Override
    public String toString() {
        return "PersonModel{" +
                "name='" + name + '\'' +
                ", isParent=" + isParent +
                ", birthDate=" + birthDate +
                '}';
    }
}
