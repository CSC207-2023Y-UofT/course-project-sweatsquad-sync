package abr.requestAndResponse.authenticationFields;

public record FieldIssue<T extends Field>(T field, String issue) {}
