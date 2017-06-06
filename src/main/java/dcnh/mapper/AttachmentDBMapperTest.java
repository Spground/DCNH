package dcnh.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dcnh.ApplicationContext;
import dcnh.model.Attachment;

/**
 * @author WuJie
 * @date 2017年5月8日下午5:42:10
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class AttachmentDBMapperTest {

	@Autowired
	AttachmentDBMapper mapper;

	@Test
	public void testInsertAttachementDBMapper() {
		Attachment attachement = new Attachment();
		attachement.setAttachmentId("ABCDEFGHIJKLMNFF");
		attachement.setFilePath("E:/");
		attachement.setProjectId(0);
		int r = mapper.insertAttachmentDBMapper(attachement);
		assert (r == 1);
	}

	@Test
	public void testGetAttachementById() {
		Attachment attachement = mapper.getAttachmentById("ABCDEFGHIJKLMN");
		assert (attachement != null);
		System.out.println(attachement);
	}

}
