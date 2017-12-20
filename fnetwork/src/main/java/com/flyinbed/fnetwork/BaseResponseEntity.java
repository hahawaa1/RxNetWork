package com.flyinbed.fnetwork;


/**
 *  可以根据自己接口的返回值来定义成员变量
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
