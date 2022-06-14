package br.com.carlosnazario.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * 	Usando o web-xml pode se configurar a ordem de excussao dos filters
 * 	Com a anotacao WebFilter o TomCat é responsavel pela execucao e dessa
 * 	forma nao temos o controle da ordem de execucao
 */

//@WebFilter("/entrada")
public class MonitoramentoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
				
		long antes = System.currentTimeMillis();
		String acao = request.getParameter("acao");
		
		chain.doFilter(request, response);
		
		long depois = System.currentTimeMillis();
		System.out.println("Tempo de execucao: " +
				acao + " >> " + (depois - antes));
	}	
}
