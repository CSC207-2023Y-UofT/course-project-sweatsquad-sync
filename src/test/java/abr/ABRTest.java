package abr;

import abr.IODataModels.AccountType;
import abr.IODataModels.AuthenticationResponseModel;
import abr.IODataModels.RegisterDetails;
import abr.IODataModels.authenticationFields.Field;
import fd.FileDatabase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ABRTest {
    private final String validFirstName = "Nick";
    private final String validLastName = "Chen";
    private final String validUsername = "NickC64";
    private final String validEmail = "nickc64@gmail.com";
    private final String validPassword = "nickpass123";

    @Test
    public void invalidRegistrationDetailsTest() {
        GymManager gymManager = new GymManager(new FileDatabase(), new PasswordHashSHA256());
        List<RegisterDetails> invalidDetails = new ArrayList<>();
        // Test blank field
        invalidDetails.add(new RegisterDetails(validFirstName, validLastName, "", validEmail, validPassword, validPassword, AccountType.REGULAR));
        // Test invalid email format
        invalidDetails.add(new RegisterDetails(validFirstName, validLastName, validUsername, "asd", validPassword, validPassword, AccountType.REGULAR));
        // Test unequal password
        invalidDetails.add(new RegisterDetails(validFirstName, validLastName, validUsername, validEmail, validPassword, "unequal", AccountType.REGULAR));
        OutputBoundary<AuthenticationResponseModel<? extends Field>> outputCatcher = new OutputBoundary<AuthenticationResponseModel<? extends Field>>() {
            @Override
            public void receiveResponse(AuthenticationResponseModel<? extends Field> rm) {
                assert !rm.isSuccessful();
            }

        };
        gymManager.setAuthenticationListener(outputCatcher);
        for (RegisterDetails details : invalidDetails) {
            gymManager.getAuthenticationRequestHandler().receiveRequest(details);
        }
        assert gymManager.getGym().getUsers().isEmpty();
    }

    @Test
    public void userAlreadyExistsTest() {

        GymManager gymManager = new GymManager(new FileDatabase(), new PasswordHashSHA256());
        RegisterDetails details = new RegisterDetails(validFirstName, validLastName, validUsername, validEmail, validPassword, validPassword, AccountType.REGULAR);
        OutputBoundary<AuthenticationResponseModel<? extends Field>> outputCatcher = new OutputBoundary<AuthenticationResponseModel<? extends Field>>() {
            int counter = 0;
            @Override
            public void receiveResponse(AuthenticationResponseModel<? extends Field> rm) {
                if (counter == 0) {
                    counter ++;
                    assert rm.isSuccessful();
                } else {
                    assert  !rm.isSuccessful();
                }
            }

        };
        gymManager.setAuthenticationListener(outputCatcher);
        gymManager.getAuthenticationRequestHandler().receiveRequest(details);
        gymManager.getAuthenticationRequestHandler().receiveRequest(details);
        assert  gymManager.getGym().getUsers().size() == 1;


    }

}