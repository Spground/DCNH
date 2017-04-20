package dcnh.global;

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

@Data
@Component
public class ProjectManagementInfo implements InitializingBean{
	
	public final static String projectGroupCountKey = "projectGroupCount";
	public final static String judgeGroupCountKey = "judgeGroupCount";
	
	private int projectGroupCount;
	private int judgeGroupCount;
	
	public void writeInfo(int projectGroupCount,int judgeGroupCount){
		this.judgeGroupCount = judgeGroupCount;
		this.projectGroupCount = projectGroupCount;
		URL url = ClassLoader.getSystemClassLoader().getResource("");
		 Properties properties = new Properties();
		//System.out.println(url.getPath());
		String filePath = url.getPath()+"/"+"projectManagementInfo.properties";
		try(OutputStream output = new FileOutputStream(filePath) ){
			properties.setProperty(projectGroupCountKey, String.valueOf(projectGroupCount));
			properties.setProperty(judgeGroupCountKey, String.valueOf(judgeGroupCount));
			 properties.store(output, "modify" + new Date().toString());// 保存键值对到文件中
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readInfo(){
		Properties prop = new Properties(); 
		try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("projectmanagementInfo.properties")){
			prop.load(inputStream);
			
			projectGroupCount = Integer.valueOf( prop.getProperty(projectGroupCountKey));
			judgeGroupCount = Integer.valueOf(prop.getProperty(judgeGroupCountKey));
			
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
