package smarttest.model.mock.request;

/**
 * 模拟请求主类
 * @author superlishun
 *
 */
public class MockRequest {

	private String drcTn;
	
	private String msgTp;
	
	private String data;

	public MockRequest() {}
	
	public MockRequest(String drcTn, String msgTp, String data) {
		super();
		this.drcTn = drcTn;
		this.msgTp = msgTp;
		this.data = data;
	}

	public String getDrcTn() {
		return drcTn;
	}

	public void setDrcTn(String drcTn) {
		this.drcTn = drcTn;
	}

	public String getMsgTp() {
		return msgTp;
	}

	public void setMsgTp(String msgTp) {
		this.msgTp = msgTp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
