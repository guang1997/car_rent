package com.atguigu.car.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码的工具类
 * 
 * @author LJH
 *
 */
public class ZXingCodeEncodeUtils {
                     
	// 二维码颜色     #FFF 15*15*15   #FFFFFF  255*255*255
	private static final int BLACK = 0xFF000000;
	// 二维码背景颜色
	private static final int WHITE = 0xFFFFFFFF;
	
	// 二维码格式参数
	private static final EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(
			EncodeHintType.class);
	static {
		/*
		 * 二维码的纠错级别(排错率),4个级别： L (7%)、 M (15%)、 Q (25%)、 H (30%)(最高H)
		 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用讯息就越少；共有四级； 选择M，扫描速度快。
		 */
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 二维码边界空白大小 1,2,3,4 (4为默认,最大)
		hints.put(EncodeHintType.MARGIN, 1);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	}
	
	/**
	 * 生成二维码保存到某个路径
	 */
	public static void createZXingCodeSaveToDisk(String content, Integer width, Integer height, String outPutPath, String imageType) {
		try {
			// 1、生成二维码
			/**
			 * 参数1：二维码存放的内容
			 * 参数2：生成的算法方式
			 */
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 2、获取二维码宽高
			int codeWidth = encode.getWidth();
			int codeHeight = encode.getHeight();
			//创建一个空的图片对象
			BufferedImage image=new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < codeWidth; i++) {
				for (int j = 0; j < codeHeight; j++) {
					// 4、循环将二维码内容定入图片
					image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
				}
			}
			//构造一个硬盘文件对象
			File outPutImage = new File(outPutPath);
			// 如果图片不存在创建图片
			if (!outPutImage.exists()){
				outPutImage.createNewFile();
			}
			// 5、将二维码写入图片
			ImageIO.write(image, imageType, outPutImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成
	 * @param content
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage createZxingCodeNormal(String content, int width, int height) {
		try {
			// 1、生成二维码
			/**
			 * 参数1：二维码存放的内容
			 * 参数2：生成的算法方式
			 */
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 2、获取二维码宽高
			int codeWidth = encode.getWidth();
			int codeHeight = encode.getHeight();
			//创建一个空的图片对象
			BufferedImage image=new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < codeWidth; i++) {
				for (int j = 0; j < codeHeight; j++) {
					// 4、循环将二维码内容定入图片
					image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
				}
			}
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 生成一个带logo的二维码返回BufferedImage
	 */
	public static BufferedImage createZXingCodeLogo(String content, int width, int height,InputStream logoStream) {
		//生成二维码
		BufferedImage codeImage = createZxingCodeNormal(content, width, height);
		if(null==codeImage) {
			throw new RuntimeException("生成二维异常");
		}
		if(null==logoStream) {
			return codeImage;
		}
		//把logoStream转成图片对象
		try {
			BufferedImage logoImage=ImageIO.read(logoStream);
			//得到logo的宽高
			int logo_width = logoImage.getWidth();
			int logo_height = logoImage.getHeight();
			
			//得到二维码的宽度
			int code_width=codeImage.getWidth();
			int code_height=codeImage.getHeight();
			//计算最大的logo宽高
			int max_logo_width=code_width/5;
			int max_logo_height=code_height/5;
			//比较最大的logo宽高和logo的宽高 确定使用哪个做为code_logo的宽高
			int code_logo_width=logo_width>max_logo_width?max_logo_width:logo_width;
			int code_logo_height=logo_height>max_logo_height?max_logo_height:logo_height;
			//计算开始画logo的坐标
			int x=(code_width-code_logo_width)/2;
			int y=(code_height-code_logo_height)/2;
			//得到画笔
			Graphics2D createGraphics = codeImage.createGraphics();
			//画一个图片
			createGraphics.drawImage(logoImage, x, y, code_logo_width, code_logo_height, null);
			//画一个圆角矩形
			createGraphics.drawRoundRect(x, y, code_logo_width, code_logo_height, 15, 15);
			// 设置画笔粗细
			createGraphics.setStroke(new BasicStroke(2));
			// 设置画笔颜色
			createGraphics.setColor(Color.WHITE);
			createGraphics.drawRect(x, y, code_logo_width, code_logo_height);
			createGraphics.dispose();//生效
			
			logoImage.flush();
			codeImage.flush();
			return codeImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	

}
