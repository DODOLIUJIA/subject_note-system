package com.zr.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

public class Ocr {
	private String contextPath = "";
	
	public Ocr(){
		
	}
	
	public Ocr(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getResult(String filePath){
		File imgFile = new File(filePath);
		ITesseract instance = new Tesseract(); // JNA Interface Mapping 
		instance.setDatapath(contextPath+"tessdata");
		String result = "";
		try {
			instance.setLanguage("chi_sim");
			result = instance.doOCR(change(imgFile));
			
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static BufferedImage change(File file) {
		BufferedImage textImg = null;

		InputStream in;
		try {
			in = new FileInputStream(file);
			BufferedImage image = ImageIO.read(in);
			textImg = ImageHelper
					.convertImageToGrayscale(ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight()));//对图片进行处理
			textImg = ImageHelper.getScaledInstance(image, image.getWidth() * 5, image.getHeight() * 5);  //将图片扩大5倍
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return textImg;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	
}
