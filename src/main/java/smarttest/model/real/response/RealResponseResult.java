package smarttest.model.real.response;

/**
 * 真实返回值result
 * @author superlishun
 *
 */
public class RealResponseResult {

	private String data;
	
	private String msgTp;
	
	private String drctn;

	public RealResponseResult(String data, String msgTp, String drctn) {
		super();
		this.data = data;
		this.msgTp = msgTp;
		this.drctn = drctn;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMsgTp() {
		return msgTp;
	}

	public void setMsgTp(String msgTp) {
		this.msgTp = msgTp;
	}

	public String getDrctn() {
		return drctn;
	}

	public void setDrctn(String drctn) {
		this.drctn = drctn;
	}
	
	
}
