package smarttest.model.real.response;

/**
 * 真实返回值
 * @author superlishun
 * @param <T>
 *
 */
public class RealResponse<T> {
	
	private boolean success;
	
	private String errorCode;
	
	private String errorMsg;
	
	private T result;

	public RealResponse(boolean success, String errorCode, String errorMsg, T result) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
}
