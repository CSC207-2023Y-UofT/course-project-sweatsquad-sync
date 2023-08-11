package abr;

public interface InputBoundary<R extends RequestModel> {

    void receiveRequest(R rm);
}
