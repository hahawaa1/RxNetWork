package com.flyinbed.fnetwork;


/**
 *
 */
public class BaseResponseEntity<T>  {

    public int count;
    public int start;
    public int code;
    public int total;
    public String title;
    public String message;
    public T subjects;
    public T data;
}
