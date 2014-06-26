package br.com.actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.model.produto.Produto;

public class ImageNameOnDisk {
	public String getNomeImg(Produto produto){
		String nome = "img_p_";
		nome+=produto.getId().toString();
		return nome;
	}

	public String getNomeImgTemp(Produto produto){
		String nome = "img_temp_";
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		String date_s = f.format(date);
		date_s = date_s.replaceAll("[^0-9]", "");
		nome=nome+date_s+".jpg";
		
		return nome;
	}
}
