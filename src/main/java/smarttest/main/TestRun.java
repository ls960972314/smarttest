package smarttest.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import smarttest.model.mock.request.MockRequest;
import smarttest.service.Send2Server;

/**
 * 执行自动测试任务
 * @author superlishun
 *
 */
public class TestRun {

	/** 各属性可能的值 */
	private static List<String> reqValues = new ArrayList<>();
	/** 生成的具体测试案例 */
	private static List<String> testcases = new ArrayList<>();
	/** 与预期不符合的案例数组 */
	private static List<String> errorTestCases = new ArrayList<>();
	
	public static void main(String[] args) {
		
		
		/**
		 * 交易类型
		 * 交易状态
		 * 清分状态
		 * 
		 */
		String dsptReqJson = "{\"drcTn\":\"epcc.303.001.01\","
				+             "\"msgTp\":\"20\","
				+             "\"data\":{"
				+                            "\"oriTrxId\":\",20171010123456010010\","
				+                            "\"oriTrxCtgy\":\"0110\","
				+                            "\"srcInstgId\":\"C123456,C6542321\","
				+                            "\"sinkInstgId\":\"C123456,C6542321\""
				+                      "}"
				+              "}";
		
		
		String templeJson = dealReq(JSON.parseObject(dsptReqJson), new AtomicInteger(0));
		
		// 将reqValues转换为二维数组
		String[][] arr = convert2array();
		
		String[] result = new String[arr.length];
		generateTestCases(arr, 0, result, templeJson);
		
		for (int i=0; i<testcases.size(); i++) {
			System.out.println("用例" + (i+1) + "---------------------");
			System.out.println("请求参数：" + testcases.get(i));
			String mockReturnCode = MockServer.handleRequest(JSON.parseObject(testcases.get(i), MockRequest.class));
			String realReturnCode = Send2Server.handleRequest(JSON.parseObject(testcases.get(i), MockRequest.class));
			System.out.println("MOCK请求结果" + mockReturnCode);
			System.out.println("REAL请求结果" + realReturnCode);
			if (ValidResultUtil.validReturnCode(mockReturnCode, realReturnCode)) {
				System.out.println("该请求结果与预期不符合");
				errorTestCases.add(testcases.get(i));
			}
		}
		
		printErrorCases();
	}



	private static void printErrorCases() {
		System.out.println("------------------------");
		System.out.println("请求结果与预期不符合的用例如下");
		for (String errorTestCase : errorTestCases) {
			System.out.println(errorTestCase);
		}
	}
	
	

	/**
	 * 将List转换为二维数组
	 * @return
	 */
	private static String[][] convert2array() {
		String [] reqArrays = new String[reqValues.size()];
		String [][] arr = new String[reqValues.size()][];
		reqValues.toArray(reqArrays);
		for (int i=0; i<reqArrays.length; i++) {
			String[] strArrays = reqArrays[i].split(",");
			arr[i] = strArrays;
		}
		return arr;
	}
	
	/**
	 * 组合所有可能的测试用例
	 * @param arr
	 * @param depth
	 * @param result
	 * @param templeJson
	 */
	private static void generateTestCases(String[][] arr, int depth, String[] result, String templeJson) {
		for (int i=0; i<arr[depth].length; i++) {
			result[depth] = arr[depth][i];
			if (depth != arr.length - 1) {
				generateTestCases(arr, depth + 1, result, templeJson);
			} else {
				for (int j=0; j<result.length; j++) {
					templeJson = templeJson.replace("$" + j, result[j]);
				}
				testcases.add(templeJson);
			}
		}
	}
	
	
	
	/**
	 * 得到请求模版字符串
	 * 存储子节点的值
	 * @param jsonObject
	 * @param index
	 * @return
	 */
	private static String dealReq(JSONObject jsonObject, AtomicInteger index) {
		for (String key : jsonObject.keySet()) {
			if (jsonObject.get(key) instanceof JSONObject) {
				dealReq(jsonObject.getJSONObject(key), index);
			}
			if (jsonObject.get(key) instanceof String) {
				reqValues.add(jsonObject.getString(key));
				jsonObject.put(key, "$" + index.get());
				index.incrementAndGet();
			}
		}
		return jsonObject.toJSONString();
	}
	
}
