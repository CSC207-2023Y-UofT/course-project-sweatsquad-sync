package abr;

public record FieldIssue<T extends Field>(T field, String issue) {}
