package smarttest.main;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import smarttest.model.mock.request.MockRequest;
import smarttest.model.mock.request.MockRequestData;
import smarttest.model.mock.response.MockResponse;
import smarttest.model.mock.response.MockResponseResult;

/**
 * 模拟服务
 * @author superlishun
 *
 */
public class MockServer {

	public static String handleRequest (MockRequest req) {
		StringBuffer sb = new StringBuffer();
		MockRequestData mockRequestData = JSON.parseObject(req.getData(), MockRequestData.class);
		
		if (mockRequestData.getSinkInstgId().equals(mockRequestData.getSrcInstgId())) {
			sb.append(JSON.toJSONString(new MockResponse<>(false, "ERROR-01", "", new MockResponseResult("", "20", "epcc.301.001.01"))));
		}
		
		if (StringUtils.isBlank(mockRequestData.getOriTrxId())) {
			sb.append(JSON.toJSONString(new MockResponse<>(false, "ERROR-02", "", new MockResponseResult("", "20", "epcc.301.001.01"))));
		}
		
		if (sb.length() <= 0) {
			sb.append(JSON.toJSONString(new MockResponse<>(true, "000000", "", "")));
		}
		
		return sb.toString();
	}
	
}
