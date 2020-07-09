package com.atguigu.car.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * 解析二维码 1，使用二维码文件的路径 2，使用二维码的流
 * 
 * @author Administrator
 *
 */
public class ZXingCodeDecodeUtils {
	// 二维码格式参数
	private static final EnumMap<DecodeHintType, Object> decodeHints = new EnumMap<DecodeHintType, Object>(
			DecodeHintType.class);
	static {
		decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
	}

	/**
	 * 解析二维码，使用google的zxing
	 * 
	 * @param imgFile
	 *            二维码路径
	 * @return content 二维码内容
	 */
	public static String decodeZXingCodeUseFile(File imgFile) {
		String content = null;
		if (!imgFile.isFile()) {
			System.out.println("输入非文件");
			return null;
		}
		try {
			content = decodeZXingCodeUseStream(new FileInputStream(imgFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码，使用google的zxing
	 * 
	 * @param codeStream
	 *            二维码的流
	 * @return content 二维码内容
	 */
	public static String decodeZXingCodeUseStream(InputStream codeStream) {
		String content = null;
		if (codeStream == null) {
			System.out.println("二维码流异常");
			return null;
		}
		try {
			BufferedImage image = ImageIO.read(codeStream);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			MultiFormatReader reader = new MultiFormatReader();
			Result result = reader.decode(binaryBitmap, decodeHints);
			content = result.getText();
		} catch (NotFoundException e) {
			System.out.println("二维码解析NotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("二维码解析IOException");
			e.printStackTrace();
		}
		return content;
	}

}
