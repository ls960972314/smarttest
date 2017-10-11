package smarttest.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 读取文件工具类
 * @author superlishun
 *
 */
public class ReadJson {

	public static String readJson(String path) {
		BufferedReader bufferReader;
		StringBuffer bf = new StringBuffer();
        try {
            bufferReader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/src/main/java/smarttest/json/" + path )));
            String sname = null;  
            while ((sname = bufferReader.readLine()) != null) {  
            	bf.append(sname);
            }  
            bufferReader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return bf.toString(); 
	}
}
