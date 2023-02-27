package excepciones;

public class CarCreationException extends Exception {

	public CarCreationException() { }

	public CarCreationException(String message) {
		super(message);
	}

	public CarCreationException(Throwable cause) {
		super(cause);
	}

	public CarCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CarCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
