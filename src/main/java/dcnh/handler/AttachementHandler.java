package dcnh.handler;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import dcnh.mapper.AttachmentDBMapper;
import dcnh.model.Attachment;
import dcnh.utils.UUIGenerator;

@Slf4j
@Component
public class AttachementHandler {

	@Autowired
	private Environment env;

	@Autowired
	private AttachmentDBMapper attachementDBMapper;

	public void getFile(HttpServletResponse response, String attachementId) {
		Attachment attachement = attachementDBMapper.getAttachmentById(attachementId);
		if (attachement == null) {
			System.out.println("#### attachement is null &&&&&&");
			return;
		}
		String path = attachement.getFilePath();
		System.out.println("####### path %%%%% " + path);
		String fileType = path.substring(path.lastIndexOf(".") + 1, path.length());
		System.out.println("######file type ######" + fileType);
		response.setContentType(String.format("application/%s;charset=UTF-8", fileType));
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(path);
			StreamUtils.copy(inputStream, response.getOutputStream());
		} catch (IOException e) {
			log.warn("StreamUtils copy warn", e);
		}
		try {
			response.getOutputStream().flush();
		} catch (IOException e) {
			log.warn("getOutputStream flush warn", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.warn("close inputstream of upload file failed ", e);
				}
			}
		}

	}

	/**
	 * 上传附件
	 * 
	 * @param file
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String uploadFile(MultipartFile file, String fileName) throws IOException {
		String path = env.getProperty("rootPath");
		String uuid = UUIGenerator.getUUID();
		String newFileName = uuid + "." + getFileType(fileName);
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path, newFileName)))) {
			out.write(file.getBytes());
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Attachment attachement = new Attachment();
		attachement.setAttachmentId(uuid);
		attachement.setFilePath(path + "/" + newFileName);
		attachementDBMapper.insertAttachmentDBMapper(attachement);
		return newFileName;
	}

	private String getFileType(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

}
