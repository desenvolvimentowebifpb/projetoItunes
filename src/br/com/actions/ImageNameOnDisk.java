package br.com.actions;

import br.com.model.produto.Produto;

public class ImageNameOnDisk {
	public String getNomeImg(Produto produto){
		String nome = "img_p_";
		nome+=produto.getId().toString();
		return nome;
	}
}
