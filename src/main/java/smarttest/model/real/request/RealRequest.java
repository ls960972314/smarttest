package smarttest.model.real.request;

/**
 * 真实请求主类
 * @author superlishun
 *
 */
public class RealRequest {

	private String drcTn;
	
	private String msgTp;
	
	private String data;

	public RealRequest() {}
	
	public RealRequest(String drcTn, String msgTp, String data) {
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
