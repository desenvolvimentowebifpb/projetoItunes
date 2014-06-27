package br.com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.dao.ProdutoDAO;
import br.com.dao.ProdutoFileDAO;
import br.com.model.produto.Produto;
import br.com.model.produto.ProdutoFile;

/**
 * Servlet implementation class CadastroProdutoMp3Servlet
 */
@WebServlet("/cpm.do")
public class CadastroProdutoMp3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroProdutoMp3Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		ProdutoFile produtoFile = new ProdutoFile();
		byte[] mp3Arquivo = null;
		
		FileItemFactory itemFile = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemFile);
		List<?> items = null;
		
			try {
				items = upload.parseRequest(request);
			} catch (org.apache.commons.fileupload.FileUploadException e) {
				e.printStackTrace();
			}
			
			Iterator<?> it = items.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (item.isFormField()) {
					if (item.getFieldName().equals("cbMp3")) {produto = new ProdutoDAO().buscar(item.getString());}
					System.out.println("Produto Descricao: "+produto.getDescricao());
					produtoFile.setCodProduto(produto.getId());
					produtoFile.setDataCadastro(Calendar.getInstance());
					produtoFile.setDataUltAlteracao(Calendar.getInstance());
					produtoFile.setLoginCadastro(new Long(1));
				}else{
					System.out.println(item.getContentType());
					if (item.getContentType().equals("audio/mpeg")) {
						mp3Arquivo = item.get();
						produtoFile.setArquivo(mp3Arquivo);
						System.out.println("Musica salva...");
					}
				}
			}

			if (produtoFile.getArquivo()!=null) {
				new ProdutoFileDAO().inserir(produtoFile);
				request.setAttribute("produto", produto);
				request.getRequestDispatcher("./cadastro_produto_mp3_view.jsp").forward(request, response);
			}else{
				
			}
	}

}
