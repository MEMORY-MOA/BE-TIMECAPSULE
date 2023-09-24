package com.moa.timecapsule.service.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.moa.timecapsule.service.TimeCapsuleContentService;

@SpringBootTest
class TimeCapsuleContentServiceImplTest {

	@Autowired
	TimeCapsuleContentService timeCapsuleContentService;

	@Test
	public void imageUploadTest() throws IOException {
		String fileName = "testImage";
		String contentType = "jpg";
		String filePath = "src/test/lizard.jpg";
		MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName,contentType,filePath);
		String url = timeCapsuleContentService.uploadFile(mockMultipartFile, UUID.randomUUID(), UUID.randomUUID());
		System.out.println(url);
	}

	private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(path));
		return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
	}

}
