package br.com.actions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import br.com.model.produto.Produto;

public class ImageSaveOnDisk {
	
	/**
	 * Busca a imagem no disco, passando um endereço
	 * */
	
	public byte[] getImageToDisco(String local_image){
		
		BufferedImage imagem = null;
		
		try {
			imagem = ImageIO.read(new File(local_image));  
			
			//OBTEM A IMAGEM E TRANSFORMA EM BYTES[]  
			ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();  
			
			ImageIO.write((BufferedImage)imagem, "png", bytesImg);//seta a imagem para bytesImg  
			bytesImg.flush();
			byte[] byteArray = bytesImg.toByteArray(); 
			bytesImg.close();
			
			return byteArray;
		
		} catch (Exception e) {
			return null;
		} 
 	}
	
	/**
	 * Retorna um ImageIcon, passando a imagem em bytes e um nome pro arquivo
	 * 
	 * */
	public ImageIcon saveImageToDisk(byte[] imagem, Produto produto){
		
		String img_nome = new ImageNameOnDisk().getNomeImg(produto);

		ImageIcon img_foto;
		
		BufferedImage img = null;
		
		try {
			File file_img = new File("/images/"+img_nome);
			img = ImageIO.read(new ByteArrayInputStream(imagem));
			img_foto = new ImageIcon(img);
			ImageIO.write(img, "jpg", file_img);
		
			return img_foto;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
