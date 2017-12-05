package myprojects.automation.assignment5.model;

import java.util.Random;

public class UniqueEmail {
    private String email;

    public UniqueEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public static UniqueEmail generate() {
        Random random = new Random();
        return new UniqueEmail("kate" + (new Integer(random.nextInt(100) + 1)).toString() + "@someemail.com");
    }
}
