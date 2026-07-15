package tests.testsdata;

import com.github.javafaker.Faker;
import tests.utils.RandomUtils;

import java.util.Locale;

public class TestData {

    private final Faker faker = new Faker();
private final Faker fakerRU = new Faker(new Locale("ru"));

    public String userName = fakerRU.name().fullName();
    public String userEmail = faker.internet().emailAddress();
    public String currentAddress = fakerRU.address().fullAddress();
    public String permanentAddress = fakerRU.address().fullAddress();;

    public String invalidMail = fakerRU.internet().emailAddress();

    public String firstName = fakerRU.name().firstName();
    public String lastName = fakerRU.name().lastName();
    public String email = faker.internet().emailAddress();
    public String genter = faker.options().option("Female", "Male", "Other");
    public String userNumber =faker.phoneNumber().subscriberNumber(10);
    public String birthMonth = faker.options().option("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");
    public String birthYear = String.valueOf(faker.number().numberBetween(1900,2026));
    public String birthDay = String.valueOf(faker.number().numberBetween(1,29));
    public String subject = faker.options().option("Physics", "English", "Maths", "Biology");
    public String hobbie = faker.options().option("Sports", "Reading", "Music");
    public String picture = "files/foto.jpg";
    public String pictureName = "foto.jpg";
    public String address = fakerRU.address().fullAddress();
    public String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = randomCity(state);

    private String randomCity(String state){
        switch (state){
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                return "";
        }
    }

    public String invalidPhone = faker.phoneNumber().subscriberNumber(2);
    public String invalidEmailForRegistration = RandomUtils.getRandomEmail();;
    public String emailWithoutDomain = RandomUtils.getRandomEmailWithoutDomain();
}






