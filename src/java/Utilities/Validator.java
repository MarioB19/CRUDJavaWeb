package Utilities;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Validator {

    private static final String NAME_PATTERN = "^[\\p{L} .'-]+$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.]).{8,}$";

    public static boolean isValidName(String name) {
        return name != null && name.length() <= 100 && Pattern.matches(NAME_PATTERN, name);
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.length() <= 100 && Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() <= 250 && Pattern.matches(PASSWORD_PATTERN, password);
    }

    public static boolean isValidBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            return false;
        }
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears() >= 18;
    }
}
