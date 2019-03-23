package com.springboot.demo.entity.base;

import java.io.Serializable;

public class UserKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.Host
     *
     * @mbggenerated
     */
    private String host;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.User
     *
     * @mbggenerated
     */
    private String user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.Host
     *
     * @return the value of user.Host
     *
     * @mbggenerated
     */
    public String getHost() {
        return host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.Host
     *
     * @param host the value for user.Host
     *
     * @mbggenerated
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.User
     *
     * @return the value of user.User
     *
     * @mbggenerated
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.User
     *
     * @param user the value for user.User
     *
     * @mbggenerated
     */
    public void setUser(String user) {
        this.user = user;
    }
}