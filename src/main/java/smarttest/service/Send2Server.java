package smarttest.service;

import com.alibaba.fastjson.JSON;

import smarttest.model.real.request.RealRequest;
import smarttest.model.real.request.RealRequestData;

/**
 * 向真实服务器发送测试用例
 * @author superlishun
 *
 */
public class Send2Server {

	public static String handleRequest (RealRequest req) {
		RealRequestData mockRequestData = JSON.parseObject(req.getData(), RealRequestData.class);
		if (mockRequestData.getSinkInstgId().equals(mockRequestData.getSrcInstgId())) {
			return "ERROR-01";
		}
		return "000000";
	}
	
}
