package smarttest.model.mock.request;

/**
 * 模拟请求子类
 * @author superlishun
 *
 */
public class MockRequestData {

	private String oriTrxId;
	
	private String oriTrxCtgy;
	
	private String srcInstgId;
	
	private String sinkInstgId;
	
	public MockRequestData() {}
	
	public MockRequestData(String oriTrxId, String oriTrxCtgy, String srcInstgId, String sinkInstgId) {
		super();
		this.oriTrxId = oriTrxId;
		this.oriTrxCtgy = oriTrxCtgy;
		this.srcInstgId = srcInstgId;
		this.sinkInstgId = sinkInstgId;
	}

	public String getOriTrxId() {
		return oriTrxId;
	}

	public void setOriTrxId(String oriTrxId) {
		this.oriTrxId = oriTrxId;
	}

	public String getOriTrxCtgy() {
		return oriTrxCtgy;
	}

	public void setOriTrxCtgy(String oriTrxCtgy) {
		this.oriTrxCtgy = oriTrxCtgy;
	}

	public String getSrcInstgId() {
		return srcInstgId;
	}

	public void setSrcInstgId(String srcInstgId) {
		this.srcInstgId = srcInstgId;
	}

	public String getSinkInstgId() {
		return sinkInstgId;
	}

	public void setSinkInstgId(String sinkInstgId) {
		this.sinkInstgId = sinkInstgId;
	}
	
	
}
