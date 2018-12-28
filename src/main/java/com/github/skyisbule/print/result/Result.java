package com.github.skyisbule.print.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 结果集
 * @Copyright: 2017 www.fallsea.com Inc. All rights reserved.
 * @author: fallsea
 * @date: 2017年10月22日 下午8:29:07
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -5063527180151987941L;

    private int errorNo;

    private String errorInfo;

    private Map<String, Object> results = new HashMap<>();

    public void setResults(String key,Object v){
        results.put(key,v);
    }

    public Result() {
        this.errorNo = 0;
    }

    public Result(int errorNo, String errorInfo) {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
    }


    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Map<String, Object> getResults() {
        return results;
    }

}
