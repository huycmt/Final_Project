package helpers;

import com.github.javafaker.Faker;

import static utilities.Log.info;

public class DataHelper {
    static Faker faker = new Faker();

    public static String email() {
        info("\tCreate new random email");
        return faker.internet().emailAddress();
    }
    public static String title(){
        info("\tCreate new random title");
        return faker.job().title();
    }

    public static String articleText() {
        info("\tCreate new random article text");
        return faker.harryPotter().quote();
    }

    public static String name(){
        info("\tCreate new random name");
        return faker.funnyName().name();
    }

    public static String pid() {
        return faker.bothify("??######");
    }
}
