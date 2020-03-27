/*
 * Copyright 2009-2016 Billy Zhang
 */
package com.mobaas.stfx;

public class JsonResult<T> {

	private int errCode;
	private String errMsg = "";
	private T data;
	
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	private JsonResult() {
    }

	public static <T> JsonResult<T> ok(T data) {
        JsonResult<T> jsonRet = new JsonResult<T>();
        jsonRet.setData(data);
        return jsonRet;
    }

	public static <T> JsonResult<T> fail(int errCode ,String errMsg) {
		JsonResult<T> jsonRet = new JsonResult<T>();
		jsonRet.errCode = errCode;
		jsonRet.errMsg = errMsg;
		return jsonRet;
	}
	
}
