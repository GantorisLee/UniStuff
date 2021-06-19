/*
 * Student name: XXX
 * Student ID: YYY
 * LMS username: ZZZ
 */

public class DataFormatException extends Exception {
    public DataFormatException(){
        super("Data Format error");
    }

    public DataFormatException(String aMessage) {
        super(aMessage);
    }
}
