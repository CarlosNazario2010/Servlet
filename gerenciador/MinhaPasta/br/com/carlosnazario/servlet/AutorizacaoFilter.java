package br.com.carlosnazario.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 	Usando o web-xml pode se configurar a ordem de excussao dos filters
 * 	Com a anotacao WebFilter o TomCat é responsavel pela execucao e dessa
 * 	forma nao temos o controle da ordem de execucao
 */

//@WebFilter("/entrada")
public class AutorizacaoFilter implements Filter {
	
	/*
	 *  Para rodar do TomCat nao seria necessario sobrescrever os metodos
	 *  init e destroy da interface Filter (tanto que os metodos estao vazios)
	 *  Mas para realizar o deploy no Jetty, este exige a implementacao desses
	 *  metodos
	 */
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}
  
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain chain) throws IOException, ServletException {
				
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");

		HttpSession sessao = request.getSession();
		boolean usuarioNaoLogado = sessao.getAttribute("usuarioLogado") == null; 
		boolean acaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("FormLogin"));
		
		if (acaoProtegida && usuarioNaoLogado) {
			response.sendRedirect("entrada?acao=FormLogin");
			return;
		}

		chain.doFilter(request, response);
	}
}
