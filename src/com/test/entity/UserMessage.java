package com.test.entity;

import java.util.Objects;

/**
 * 用户模块的实体类：当前类的一个对象可以封装USER_MESSAGE表中‘一行数据’
 *
 * @author qings
 */
public class UserMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //....



    private int userId;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private String userSex;

    //定义有参构造函数
    public UserMessage( String userName, String userPassword, String userPhone, String userEmail, String userSex) {

        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userSex = userSex;
    }
    //定义无参构造函数
    public UserMessage() {
//        super();
    }

    //Getters 与 Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    //如果当前类的对象需要判断是否相等时，需要重写类的hashCode()与equals()方法


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMessage that = (UserMessage) o;
        return userId == that.userId;
    }

    //重写toString()方法
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSex='" + userSex + '\'' +
                '}';
    }









}
