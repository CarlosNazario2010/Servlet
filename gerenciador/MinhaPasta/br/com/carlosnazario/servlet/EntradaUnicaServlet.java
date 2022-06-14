package br.com.carlosnazario.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.carlosnazario.acao.Acao;

@WebServlet("/entrada")
public class EntradaUnicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
//		HttpSession sessao = request.getSession();
//		boolean usuarioNaoLogado = sessao.getAttribute("usuarioLogado") == null; 
//		boolean acaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("FormLogin"));
//		
//		if (acaoProtegida && usuarioNaoLogado) {
//			response.sendRedirect("entrada?acao=FormLogin");
//			return;
//		}
		
		String nomeDaClasse = "br.com.carlosnazario.acao." + paramAcao;
		
		String nome;
		try {
			Class<?> classe = Class.forName(nomeDaClasse);
			@SuppressWarnings("deprecation")
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException |
				IllegalAccessException | ServletException |IOException e) {
			throw new ServletException(e);
		} 
		
		String[] tipoEndereco = nome.split(":");
		
		if (tipoEndereco[0].equals("forward")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(tipoEndereco[1]);
		}
		
//		if (paramAcao.equals("ListaEmpresas")) {			
//			ListaEmpresas acao = new ListaEmpresas();
//			nome = acao.executa(request, response);
//		}
//		else if (paramAcao.equals("RemoveEmpresa")) {
//			RemoveEmpresa acao = new RemoveEmpresa();
//			nome = acao.executa(request, response);
//		}
//		else if (paramAcao.equals("MostraEmpresa")) {
//			MostraEmpresa acao = new MostraEmpresa();
//			nome = acao.executa(request, response);
//		}
//		else if (paramAcao.equals("EditaEmpresa")) {
//			EditaEmpresa acao = new EditaEmpresa();
//			nome = acao.executa(request, response);
//		}
//		else if (paramAcao.equals("NovaEmpresa")) {
//			NovaEmpresa acao = new NovaEmpresa();
//			nome = acao.executa(request, response);
//		}
	} 
}
