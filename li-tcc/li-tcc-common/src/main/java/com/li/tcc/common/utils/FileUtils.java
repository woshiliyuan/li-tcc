package com.li.tcc.common.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileUtils
 * 
 * @author yuan.li
 */
public class FileUtils {

	/**
	 * 写入文件
	 * 
	 * @param fullFileName
	 *            文件路径全称
	 * @param contents
	 *            内容
	 */
	public static void writeFile(final String fullFileName, final byte[] contents) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(fullFileName, "rw");
			try (FileChannel channel = raf.getChannel()) {
				ByteBuffer buffer = ByteBuffer.allocate(contents.length);
				buffer.put(contents);
				buffer.flip();
				while (buffer.hasRemaining()) {
					channel.write(buffer);
				}
				channel.force(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
