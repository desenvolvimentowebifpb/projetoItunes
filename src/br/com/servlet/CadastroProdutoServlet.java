package br.com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.converters.StringToBigDecimal;
import br.com.dao.ArtistaDAO;
import br.com.dao.GeneroDAO;
import br.com.dao.ProdutoDAO;
import br.com.dao.TipoProdutoDAO;
import br.com.model.produto.Produto;
import br.com.validated.ProdutoValidated;

/**
 * Servlet implementation class CadastroProdutoServlet
 */
@WebServlet("/cp.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 , // 1MB
        maxFileSize = 1024 *  16,   // 16 KB
        maxRequestSize = 1024 * 1024 * 16  // 16 MB
)
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroProdutoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		byte[] imgArquivo = null;
		
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
					if (item.getFieldName().equals("descricao")) {
						produto.setDescricao(item.getString());
						System.out.println(produto.getDescricao());}
					if (item.getFieldName().equals("cbGenero")) {produto.setGenero(new GeneroDAO().buscar(item.getString()));}
					if (item.getFieldName().equals("cbArtista")) {produto.setArtista(new ArtistaDAO().buscar(item.getString()));}
					if (item.getFieldName().equals("cbTipoProduto")) {produto.setTipoProduto(new TipoProdutoDAO().buscar(item.getString()));}
					if (item.getFieldName().equals("precoPadrao")){produto.setPrecoPadrao(new StringToBigDecimal().StringToBigDecimalValue(item.getString()));}
					if (item.getFieldName().equals("precoPromocional")){produto.setPrecoPromocional(new StringToBigDecimal().StringToBigDecimalValue(item.getString()));}
				}else{
					System.out.println(item.getContentType());
					if (item.getContentType().equals("image/pjpeg")) {
						imgArquivo = item.get();
						produto.setImage(imgArquivo);
						System.out.println("Imagem salva...");
					}
				}
				produto.setLoginCadastro(new Long(1));
				produto.setDataCadastro(Calendar.getInstance());
				produto.setDataUltAlteracao(Calendar.getInstance());
			}
			
			HashMap<String, String> map = new ProdutoValidated().isValid(produto);
			if (map.get("boolean").equals("true")) {
				new ProdutoDAO().inserir(produto);
				request.setAttribute("produto", produto);
				request.getRequestDispatcher("./cadastro_produto_view.jsp").forward(request, response);
			}else{
				System.out.println(map.get("message"));
				request.setAttribute("message", map.get("message"));
				request.getRequestDispatcher("./cadastro_produto_error.jsp").forward(request, response);
			}
	}	
	
}
