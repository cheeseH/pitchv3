package pitch.manager;

import java.util.Map;

import com.xiao.util.json.xjson.JArrayObj;
import com.xiao.util.json.xjson.JMapObj;

public class Status {
	private boolean success;
	private int code;
	private JArrayObj msg;

	public Status() {
		success = true;
		code = 200;
		msg = new JArrayObj();
	}

	public Status(boolean result,int resultCode,JArrayObj errMsg) {
		success = result;
		code = resultCode;
		this.msg = errMsg;
	}

	public boolean isOk() {
		return success;
	}

	public JArrayObj getMsg() {
		return this.msg;
	}

	public void setMsg(JArrayObj msg) {
		this.msg = msg;
	}

	public void setSuccess(boolean result) {
		this.success = result;
	}
	

	static public Status OK() {
		return new Status();
	}
	
	static public Status OK(String token) {
		Status st = new Status();
		return st;
	}

	static public Status Error(int resultCode,Map<String, Integer> error) {
		JArrayObj array = new JArrayObj();
		for (Map.Entry<String, Integer> entry : error.entrySet()) {
			String paramString = entry.getKey();
			int code = entry.getValue();
			JMapObj pair = new JMapObj();
			pair.put("key", paramString);
			pair.put("code", code);
			array.add(pair);

		}
		return new Status(false,resultCode,array);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
