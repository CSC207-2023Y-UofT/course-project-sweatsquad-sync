package abr;

public interface OutputBoundary<R extends ResponseModel> {

    public void receiveResponse(R rm);
}
