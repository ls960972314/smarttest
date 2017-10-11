# 功能
自动创建测试案例进行测试及测试结果分析

# 代码结构说明
<pre>
smarttest.json.*                                请求json串
smarttest.main.TestRun.java                     执行自动化测试的入口类
smarttest.resultValid.ResultValid.java          根据真实返回值和模拟返回值,校验这个测试用例是否达标
smarttest.mockServer.MockServer.java            模拟服务器接口,自定义规则,返回命中的返回码集合
smarttest.model.mock.request.*                  模拟请求对象
smarttest.model.mock.response.*                 模拟返回对象
smarttest.model.real.request.*                  真实请求对象(可引入maven jar代替)
smarttest.model.real.response.*                 真实返回对象(可引入maven jar代替)
smarttest.service.Send2Server.java              请求真实接口类
smarttest.utils.ReadJson.java                   读取文件工具类
</pre>

# 使用说明
## 配置json请求对象
> {
"drcTn":"epcc.303.001.01",
"msgTp":"20",
"data":{
		"oriTrxId":",20171010123456010010",
		"oriTrxCtgy":"0110",
		"srcInstgId":"C123456,C6542321",
		"sinkInstgId":"C123456,C6542321"
		}
}


拥有单个值时就填写该值
drcTn的值为epcc.303.001.01
msgTp的值为20
oriTrxCtgy的值为0110

拥有多个值时使用逗号隔开
oriTrxId的值为空和20171010123456010010
srcInstgId的值为C123456,C6542321
sinkInstgId的值为C123456,C6542321

所以该用例的组合就有1 \*1 \* 1 \* 2 \* 2 \* 2 = 8个

- 用例1{"data":{"oriTrxCtgy":"0110","oriTrxId":"","sinkInstgId":"C123456","srcInstgId":"C123456"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例2{"data":{"oriTrxCtgy":"0110","oriTrxId":"","sinkInstgId":"C6542321","srcInstgId":"C123456"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例3{"data":{"oriTrxCtgy":"0110","oriTrxId":"","sinkInstgId":"C123456","srcInstgId":"C6542321"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例4{"data":{"oriTrxCtgy":"0110","oriTrxId":"","sinkInstgId":"C6542321","srcInstgId":"C6542321"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例5{"data":{"oriTrxCtgy":"0110","oriTrxId":"20171010123456010010","sinkInstgId":"C123456","srcInstgId":"C123456"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例6{"data":{"oriTrxCtgy":"0110","oriTrxId":"20171010123456010010","sinkInstgId":"C6542321","srcInstgId":"C123456"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例7{"data":{"oriTrxCtgy":"0110","oriTrxId":"20171010123456010010","sinkInstgId":"C123456","srcInstgId":"C6542321"},"drcTn":"epcc.303.001.01","msgTp":"20"}
- 用例8{"data":{"oriTrxCtgy":"0110","oriTrxId":"20171010123456010010","sinkInstgId":"C6542321","srcInstgId":"C6542321"},"drcTn":"epcc.303.001.01","msgTp":"20"}

## 配置模拟服务接口

参考smarttest.mockServer.MockServer.java,填写自己的规则,输出对应的返回码
```
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
```

## 测试结果判断
参考smarttest.resultValid.ResultValid.java,实现自己的结果判断,根据真实返回值和模拟返回值,校验这个测试用例是否达标

```
public static boolean validReturnCode(String mockReturnCode, String realReturnCode) {
		if (mockReturnCode.contains(realReturnCode)) {
			return false;
		}
		return true;
	}

```

