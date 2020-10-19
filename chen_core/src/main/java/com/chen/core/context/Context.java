package com.chen.core.context;

/**
 * 上下文执行类
 */
public class Context {
    /**
     * 传入参数
     */
    private String params;
    /**
     * 执行参数
     */
    private String firstResult;
    private String secondResult;
    /**
     * 结果参数
     */
    private String result;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(String firstResult) {
        this.firstResult = firstResult;
    }

    public String getSecondResult() {
        return secondResult;
    }

    public void setSecondResult(String secondResult) {
        this.secondResult = secondResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
