package com.employee.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseStatus {

	@XmlElement(name="status")
	public int status=0;

	@XmlElement(name="message")
	public String msg="";
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResponseStatus(){
		super();
	}

	public ResponseStatus(int status, String msg){
		super();
		this.status =status;
		this.msg=msg;
	}
	
}
