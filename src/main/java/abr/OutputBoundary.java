package abr;

public interface OutputBoundary<R extends ResponseModel> {

    void receiveResponse(R rm);
}
