package com.mmall.common;

/**
 * Created by lengzefu on 2018/3/13.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //通过一个内部接口类进行分组，避免使用了枚举类
    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 0;//管理员
    }
}
