package com.javaex.vo;

public class JsonResultVo {

	// 필드
	private String result; // 'success' 'fail'
	private Object data; // 성공했을때
	private String failMsg; // 실패했을때 참고 할 수 있는 메세지 코드값

	// 생성자
	public JsonResultVo() {
		super();
	}

	public JsonResultVo(String result, Object data, String failMsg) {
		super();
		this.result = result;
		this.data = data;
		this.failMsg = failMsg;
	}
	
	// 메소드 gs

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getFailMsg() {
		return failMsg;
	}
	
	// 메소드 일반

	public void success(Object data) {
		this.result = "success";
		this.data = data;
	}

	public void fail(String failMsg) {
		this.result = "fail";
		this.failMsg = failMsg;

	}

}
