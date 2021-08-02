package pl.sda.crm.exception;

public final class CustomerNameRegisteredException extends BusinessServiceException {

    public CustomerNameRegisteredException(String message) {
        super(message);
    }

    public CustomerNameRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
