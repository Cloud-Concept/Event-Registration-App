package Interface;

/**
 * Created by Abanoub Wagdy on 8/8/2016.
 */
public interface LongOperationDelegate {

    void didFinishLoadWithSuccess(String response);

    void didFinishLoadWithFail(String exception);
}
