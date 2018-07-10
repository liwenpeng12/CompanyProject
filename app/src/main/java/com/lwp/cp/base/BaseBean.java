package com.lwp.cp.base;

/**
 * liwenpeng
 * 2018/6/30 14:45
 * CompanyProject
 * Descrobe:reponse基类
 */
public class BaseBean<T> {
    public int errorCode;
    public String errorMsg;
    public T t;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
