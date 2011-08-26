package gitoscope.domain;

import org.eclipse.jgit.lib.PersonIdent;

public class Person {

    public Person(PersonIdent person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }

    public String getEmailAddress() {
        return person.getEmailAddress();
    }

    private PersonIdent person;
}
