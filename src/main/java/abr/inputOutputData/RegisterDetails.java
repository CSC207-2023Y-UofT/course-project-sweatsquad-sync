package abr.inputOutputData;

public record RegisterDetails(String firstName, String lastName, String username, String email, String password, String confirmPassword, AccountType accountType) implements AuthenticationRequestModel {


    public boolean anyBlank() {
        return firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty();
    }

    public boolean correctEmailFormat() {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
