package dcnh.myutil;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HTMLToWord {

	@SuppressWarnings("unused")
	public static String createWord(String rootPath, String fileName, String htmlContent) throws IOException {
		// String path = "E:\\web_project\\WordProject";
		// File fileDir = new File(path);
		// String fileName = "a.doc";

		byte b[] = htmlContent.getBytes("GBK");
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		POIFSFileSystem poifs = new POIFSFileSystem();
		DirectoryEntry directory = poifs.getRoot();

		DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);

		FileOutputStream ostream = new FileOutputStream(rootPath + "\\" + fileName);
		poifs.writeFilesystem(ostream);
		ostream.close();
		poifs.close();
		bais.close();
		return fileName;
	}
}
