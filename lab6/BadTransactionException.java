package lab6;

public class BadTransactionException extends Exception {


	  public int amount;  // The invalid account number.

	  /**
	   *  Creates an exception object for nonexistent account "badAcctNumber".
	   **/
	  public BadTransactionException(int badamount) {
	    super("Invalid amount " + badamount);

	    amount = badamount;
	  }
	}
