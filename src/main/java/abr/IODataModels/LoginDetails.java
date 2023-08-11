package abr.IODataModels;

public record LoginDetails(String username, String password) implements AuthenticationRequestModel {}
