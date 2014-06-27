package br.com.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ProdutoDAO;
import br.com.model.produto.Produto;

/**
 * Servlet implementation class ProdutoImagemServlet
 */
@WebServlet("/imagem.do")
public class ProdutoImagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoImagemServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");
		 
        // Recupera o parâmetro
        String text = request.getParameter("texto");
        System.out.println("PArametro recebido: "+text);
        Produto produto = new ProdutoDAO().buscar(text);
        System.out.println(produto.getDescricao());
        InputStream in = new ByteArrayInputStream(produto.getImage());
        BufferedImage image = ImageIO.read(in);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            // Escreve a imagem no outputstream da response no formato jpg
            ImageIO.write(image, "JPG", out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
	}

}
