package dcnh.handler;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Slf4j
@Component
public class AttachementHandler {
	
		@Autowired
		private Environment env;
	
	    public void getFile( HttpServletRequest request,HttpServletResponse response,String attachementId){
	    	String path1 = env.getProperty("rootPath")+request.getRequestURI();
	    	log.info("path = " + path1);
	    	FileInputStream inputStream = null; 
	    	try {
	    		inputStream = new FileInputStream(path1);
				StreamUtils.copy(inputStream , response.getOutputStream() );
			} catch (IOException e) {
				log.warn("StreamUtils copy warn",e );
			}
	    	try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				log.warn("getOutputStream flush warn",e );
			}finally{
				if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						log.warn("close inputstream of upload file failed ",e);
					}
				}
			}
	    	
	    }
	    
	    public void uploadFile( HttpServletResponse response,String fileName) {
	        	String path = env.getProperty("file.path");
	        	log.info("path = " + path);
	        	try {
	    			StreamUtils.copy( new FileInputStream(path + fileName) , response.getOutputStream() );
	    		} catch (IOException e) {
	    			log.warn("StreamUtils copy warn",e );
	    		}
	        	
	        	try {
	    			response.getOutputStream().flush();
	    		} catch (IOException e) {
	    			log.warn("getOutputStream flush warn",e );
	    		}
	   }
}
