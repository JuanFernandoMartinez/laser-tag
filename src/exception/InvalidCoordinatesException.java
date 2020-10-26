package exception;

public class InvalidCoordinatesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCoordinatesException() {
		super("La entraga ingresada es invalida");
	}
}
