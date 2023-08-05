package abr.inputOutputData;

public record LoginDetails(String username, String password) implements AuthenticationRequestModel {}
