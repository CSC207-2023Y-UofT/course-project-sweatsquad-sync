package abr;

public interface InputBoundary<R extends RequestModel> {

    public void receiveRequest(R rm);
}
