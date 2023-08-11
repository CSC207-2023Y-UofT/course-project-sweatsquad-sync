package abr.IODataModels.authenticationFields;

import abr.IODataModels.RegisterDetails;
import abr.RequestModel;

public record DetailChangeRequest(RegisterDetails registerDetails) implements RequestModel {
}
