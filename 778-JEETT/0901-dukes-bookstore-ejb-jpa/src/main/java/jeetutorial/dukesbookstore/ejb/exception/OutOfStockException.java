package jeetutorial.dukesbookstore.ejb.exception;

public class OutOfStockException extends OrderException {

	public OutOfStockException() {
		super();
		
	}

	public OutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public OutOfStockException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public OutOfStockException(String message) {
		super(message);
		
	}

	public OutOfStockException(Throwable cause) {
		super(cause);
		
	}

}
