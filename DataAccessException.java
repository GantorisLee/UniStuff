/*
 * Student name: XXX
 * Student ID: YYY
 * LMS username: ZZZ
 */

public class DataAccessException extends Exception {
    public DataAccessException(){
        super("Data Access error");
    }

    public DataAccessException(String aMessage) {
        super(aMessage);
    }
}
