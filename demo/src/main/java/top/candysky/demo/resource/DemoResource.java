package top.candysky.demo.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.*;

public class DemoResource {

	public static void main(String[] args) throws IOException {

		FileSystemResource fileSystemResource = new FileSystemResource("D:\\spring-framework\\demo\\src\\main\\java\\top\\candysky\\demo\\resource\\text.txt");
		File file = fileSystemResource.getFile();
		System.out.println(file.length());
		OutputStream outputStream = fileSystemResource.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		writer.write("hello");
		writer.flush();
		outputStream.close();
		writer.close();
	}

}
