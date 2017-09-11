package <%= appPackage %>.interactors.remote.error;

import retrofit2.Response;

/**
 * Created by abbas on 5/21/17.
 */

public class GeneralApiException extends Exception {
    public int code;
    public String message;
    private final transient Response<?> response;


    public GeneralApiException(Response<?> response) {
        this.response = response;
    }

    /** HTTP status code. */
    public int code() {
        return code;
    }

    /** HTTP status message. */
    public String message() {
        return message;
    }

    /**
     * The full HTTP response. This may be null if the exception was serialized.
     */
    public Response<?> response() {
        return response;
    }
}
