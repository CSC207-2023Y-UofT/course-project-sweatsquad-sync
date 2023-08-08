package abr.IODataModels;

import abr.ResponseModel;

public record LoginEvent(AccountType accountType, String firstName, String lastName, String username, String email) implements ResponseModel {
}
