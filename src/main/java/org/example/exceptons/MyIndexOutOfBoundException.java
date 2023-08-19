package org.example.exceptons;

public class MyIndexOutOfBoundException extends IndexOutOfBoundsException{
    public MyIndexOutOfBoundException(String s) {
        super(s);
    }
}
