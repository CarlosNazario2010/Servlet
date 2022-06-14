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

public class EditaEmpresa implements Acao {
	
	public String executa(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String data = request.getParameter("data");
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		Date dataAbertura;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			dataAbertura = sdf.parse(data);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		Banco banco = new Banco();
		Empresa empresa = banco.buscaEmpresa(id);
		empresa.setNome(nome);
		empresa.setDataAbertura(dataAbertura);
		
		return "redirect:entrada?acao=ListaEmpresas";
	}
}
