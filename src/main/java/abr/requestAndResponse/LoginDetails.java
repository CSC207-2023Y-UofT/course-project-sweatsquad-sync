package abr.requestAndResponse;

public record LoginDetails(String username, String password) implements AuthenticationRequestModel {}
