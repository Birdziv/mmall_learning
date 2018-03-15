package com.mmall.dao;

import com.mmall.pojo.Cart;

public interface CartMapper { //购物车的接口
    //对应的方法在mappers中能找到
    int deleteByPrimaryKey(Integer id);//根据主键删除
    //插入购物车，将一个对象完全插入表中
    int insert(Cart record);//
    //根据入参Cart的对象哪些为空
    int insertSelective(Cart record);
    //根据指间查询
    Cart selectByPrimaryKey(Integer id);
    //根据主键更新，下面两个方法与上面插入的方法是类似的，有字段的空判断
    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}