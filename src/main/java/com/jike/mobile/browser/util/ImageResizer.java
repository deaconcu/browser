package com.jike.mobile.browser.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResizer {
	private int height = 100;
	private int width = 100;
	private BufferedImage originalImage;
	private BufferedImage resizedImage;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public BufferedImage getResizedImage() {
		return resizedImage;
	}

	public void setOriginalImage(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}
	
	
	public void execute(){
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedImage originalImage = ImageIO.read(new File("c:\\image\\mkyong.jpg"));
			ImageResizer imageResizer = new ImageResizer();
			imageResizer.setWidth(300);
			imageResizer.setHeight(300);
			imageResizer.setOriginalImage(originalImage);
			
			imageResizer.execute();
			BufferedImage resizedImage = imageResizer.getResizedImage();
			
			ImageIO.write(resizedImage, "jpg", new File("c:\\image\\mkyong_jpg.jpg")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
