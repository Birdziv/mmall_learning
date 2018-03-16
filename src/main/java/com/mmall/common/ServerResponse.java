package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 通用的数据端的响应对象
 * Created by lengzefu on 2018/3/12.
 * 传一个泛型T，并实现序列化接口
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //当有些字段没有的时候，就不用返回相应的null
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data; //返回的数据类型可能多样

    //构造方法
    private ServerResponse(int status){
        this.status = status;
    }
    private ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }
    private ServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    //问题：第二个构造器与第四个构造器，当T类型为String的时候，将如何响应，该如何规避这个问题？
    //答案：优先响应第四个，若为T类型且不是String类型则用第二个
    //规避的方法：可以使用如下的public一些方法进行规避

    @JsonIgnore //不再显示在json里
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode(); //想到用一个枚举类将status进行封装-->common->ResponseCode
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }

    //继续开放，用静态方法---对应着上方的构造函数，解决了上面说的那个问题
    public static <T> ServerResponse <T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> ServerResponse <T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServerResponse <T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> ServerResponse <T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    //创建失败的响应
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    //把code变成变量
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}
