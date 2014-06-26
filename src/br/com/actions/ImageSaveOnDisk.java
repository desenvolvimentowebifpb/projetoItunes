package br.com.actions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;

import br.com.model.produto.Produto;

public class ImageSaveOnDisk {
	
	/**
	 * Retorna um ImageIcon, passando a imagem em bytes e um nome pro arquivo
	 * 
	 * */
	public String saveImageToDisk(byte[] imagem, Produto produto){
		String img_nome = new ImageNameOnDisk().getNomeImg(produto);

		BufferedImage img = null;
		
		try {
			File file_img = new File("/images/"+img_nome);
			img = ImageIO.read(new ByteArrayInputStream(imagem));

			if (file_img.exists()) {
				ImageIO.write(img, "jpg", file_img);
			}else{
				file_img.createNewFile();
				ImageIO.write(img, "jpg", file_img);
			}
			System.out.println("Absolute Path: "+file_img.getAbsolutePath());
			System.out.println("Canonical Path: "+file_img.getCanonicalPath());
			System.out.println(" Path: "+file_img.getPath());
			return file_img.getPath();
		} catch (Exception e) {
			System.out.println("Arquivo nao foi salvo.");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Retorna um ImageIcon, passando a imagem em bytes e um nome pro arquivo
	 * 
	 * */
	/*public String saveImageToDiskTemp(byte[] imagem, Produto produto){
		final String IMAGE_DIR = "/images/";
		String img_nome = new ImageNameOnDisk().getNomeImgTemp(produto);
		BufferedImage img = null;
		try {
			File file_img = new File(IMAGE_DIR+File.separator+img_nome);
			img = ImageIO.read(new ByteArrayInputStream(imagem));
			
			if (file_img.canRead()&& file_img.canWrite()) {
				ImageIO.write(img, "jpg", file_img);
				
				System.out.println("Absolute Path: "+file_img.getAbsolutePath());
				System.out.println("Canonical Path: "+file_img.getCanonicalPath());
				System.out.println(" Path: "+file_img.getPath());
				return file_img.getPath();
			}else{
				System.out.println("Nao gravou o arquivo");
				return null;
			}
			
		} catch (Exception e) {
			System.out.println("Arquivo nao foi salvo.");
			e.printStackTrace();
			return null;
		}
		
	}*/

	/**
	 * Retorna um ImageIcon, passando a imagem em bytes e um nome pro arquivo
	 * 
	 * */
	public String saveImageToDiskTemp(FileItem file, Produto produto){
		final String IMAGE_DIR = "/images/";
		String img_nome = new ImageNameOnDisk().getNomeImgTemp(produto);
		
		File file_ = new File(IMAGE_DIR+img_nome);
		try {
			System.out.println("Tentando Salvar a imagem...");
			file.write(file_);
			String path="";
			path += file_.getAbsolutePath();
			System.out.println("Imagem salva...");
			return path;
		} catch (Exception e) {
			System.out.println("Nao encontrou a imagem");
			e.printStackTrace();
			return new String("./images/naocadastrada.jpg");
		}
		
		
	}
	
}
