package smarttest.service;

import com.alibaba.fastjson.JSON;

import smarttest.model.mock.request.MockRequest;
import smarttest.model.mock.request.MockRequestData;

/**
 * 向服务器发送测试用例
 * @author superlishun
 *
 */
public class Send2Server {

	public static String handleRequest (MockRequest req) {
		MockRequestData mockRequestData = JSON.parseObject(req.getData(), MockRequestData.class);
		if (mockRequestData.getSinkInstgId().equals(mockRequestData.getSrcInstgId())) {
			return "ERROR-01";
		}
		return "000000";
	}
	
}
