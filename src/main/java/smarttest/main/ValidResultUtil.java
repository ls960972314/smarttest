package smarttest.main;

/**
 * 验证结果是否正确的工具类
 * @author superlishun
 *
 */
public class ValidResultUtil {

	public static boolean validReturnCode(String mockReturnCode, String realReturnCode) {
		if (mockReturnCode.contains(realReturnCode)) {
			return false;
		}
		return true;
	}
}
