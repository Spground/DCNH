package dcnh.global;

import java.io.File;
/**
*@author WuJie
*@date 2017年4月25日下午2:52:25
*@version 1.0
**/
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.Data;

//TODO 打成jar包后， 属性文件不能写入，只能读取
@Data
@Component
public class ProjectManagementInfo implements InitializingBean {

	public final static String PROJECT_GROUP_COUNT_KEY = "projectGroupCount";
	public final static String JUDGE_GROUP_COUNT_KEY = "judgeGroupCount";
	public final static String PM_CONFIG_FILE_NAME = "projectmanagementinfo.properties";
	private int projectGroupCount;
	private int judgeGroupCount;

	public boolean writeInfo(int projectGroupCount, int judgeGroupCount) {
		this.judgeGroupCount = judgeGroupCount;
		this.projectGroupCount = projectGroupCount;
		URL url = this.getClass().getResource("/" + PM_CONFIG_FILE_NAME);
		Properties properties = new Properties();
		System.out.println("====PM_CONFIG_FILE_NAME file URL location is" + url.getPath());
		System.out.println("====PM_CONFIG_FILE_NAME file location is" + url.toExternalForm());

		OutputStream output = null;
		String protocol = url.getProtocol();
		System.out.println("====protocol" + protocol);
		String path = url.getPath().replace(protocol, "").trim();
		System.out.println("====path" + path);
		try {
			output = new FileOutputStream(new File(path));
			properties.setProperty(PROJECT_GROUP_COUNT_KEY, String.valueOf(projectGroupCount));
			properties.setProperty(JUDGE_GROUP_COUNT_KEY, String.valueOf(judgeGroupCount));
			properties.store(output, "modify" + new Date().toString());// 保存键值对到文件中
			output.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public void readInfo() {
		Properties prop = new Properties();
		InputStream inputStream = this.getClass().getResourceAsStream("/" + PM_CONFIG_FILE_NAME);
		assert (inputStream != null);
		try {
			prop.load(inputStream);
			projectGroupCount = Integer.valueOf(prop.getProperty(PROJECT_GROUP_COUNT_KEY));
			judgeGroupCount = Integer.valueOf(prop.getProperty(JUDGE_GROUP_COUNT_KEY));
			System.out.println(String.format("===readInfo projectGroupCount=%d, judgeGroupCount= %d", projectGroupCount,
					judgeGroupCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		readInfo();
	}
}
