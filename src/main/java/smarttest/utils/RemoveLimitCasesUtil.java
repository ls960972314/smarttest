package smarttest.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author superlishun
 *
 */
public class RemoveLimitCasesUtil {

	public static void main(String[] args) {
		
	}
	
	public static List<String> removeLimitCases(List<String> testcases, String limitJsonPath) {
		List<Map<String, String>> limitJsonMapList = getLimitJsonLeaf2MapList(limitJsonPath);
		Iterator<String> iterator = testcases.iterator();
		while (iterator.hasNext()) {
			String testcase = iterator.next();
			// 将用例中的key-value存到map集合中
			Map<String, String> testcase2Map = getLeaf2Map(JSON.parseObject(testcase));
			for (Map<String, String> limitJsonMap : limitJsonMapList) {
				if (preMapContainsAfterMap(testcase2Map, limitJsonMap)) {
					iterator.remove();
				}
			}
		}
		return testcases;
	}
	
	/**
	 * 判断后面一个map中所有key-value是否被前一个map包含
	 * @param preMap    前一个map(范围大)
	 * @param afterMap  后一个map(范围小)
	 * @return
	 */
    private static boolean preMapContainsAfterMap(Map<String, String> preMap, Map<String, String> afterMap) {
    	for (Entry<String, String> afterMapEntry : afterMap.entrySet()) {
    		if (!afterMapEntry.getValue().equals(preMap.get(afterMapEntry.getKey()))) {
    			return false;
    		}
    	}
    	return true;
    }
	
	
	private static List<Map<String, String>> getLimitJsonLeaf2MapList(String limitJsonPath) {
		List<Map<String, String>> mapList = new ArrayList<>();
		String limitJson = ReadJson.readJson(limitJsonPath);
		JSONObject limitJsonObject = JSON.parseObject(limitJson);
		JSONArray limitJsonArray = limitJsonObject.getJSONArray("limitList");
		Iterator<Object> iterator = limitJsonArray.iterator();
		while (iterator.hasNext()) {
			Map<String, String> map = new HashMap<>();
			JSONObject jsonObj = (JSONObject) iterator.next();
			for (String key : jsonObj.keySet()) {
				map.put(key, jsonObj.getString(key));
			}
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * 得到请求模版字符串
	 * 存储子节点的值
	 * @param jsonObject
	 * @param index
	 * @return
	 */
	private static Map<String, String> getLeaf2Map(JSONObject jsonObject) {
		Map<String, String> leafMap = new HashMap<>();
		for (String key : jsonObject.keySet()) {
			if (jsonObject.get(key) instanceof JSONObject) {
				leafMap.putAll(getLeaf2Map(jsonObject.getJSONObject(key)));
			}
			
			if (jsonObject.get(key) instanceof String) {
				leafMap.put(key, jsonObject.getString(key));
			}
		}
		return leafMap;
	}
	
}