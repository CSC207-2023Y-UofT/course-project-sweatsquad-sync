package abr.IODataModels;

import abr.ResponseModel;

public record ActiveUserIDResponse(String username, String firstname, String lastname, String email) implements ResponseModel {}
