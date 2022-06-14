package br.com.carlosnazario.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.carlosnazario.modelo.Banco;
import br.com.carlosnazario.modelo.Empresa;

public class NovaEmpresa implements Acao{
	
	public String executa(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		// le os parametros da requisicao
		String nome = request.getParameter("nome");
		String data = request.getParameter("data");
		Date dataAbertura;
		
		// fazer o parsing nos parametros necessarios
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			dataAbertura = sdf.parse(data);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		// popular os atributos do objeto
		Empresa empresa = new Empresa();
		empresa.setNome(nome);
		empresa.setDataAbertura(dataAbertura);
		
		// realizar a logica do projeto
		Banco banco = new Banco();
		banco.adiciona(empresa);

		return "redirect:entrada?acao=ListaEmpresas";
	}
}
