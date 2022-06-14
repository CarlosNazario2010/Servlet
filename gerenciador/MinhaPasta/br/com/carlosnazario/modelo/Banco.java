package br.com.carlosnazario.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {

	private static List<Empresa> lista = new ArrayList<>();
	private static List<Usuario> listaUsuarios = new ArrayList<>();

	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa = new Empresa();
		empresa.setId(chaveSequencial++);
		empresa.setNome("StaticCompany");
		lista.add(empresa);
		Empresa empresa1 = new Empresa();
		empresa1.setId(chaveSequencial++);
		empresa1.setNome("StaticCompany2");
		lista.add(empresa1);
		
		Usuario usuario = new Usuario();
		usuario.setLogin("Carlos");
		usuario.setSenha("1234");
		listaUsuarios.add(usuario);
		Usuario usuario1 = new Usuario();
		usuario1.setLogin("Nazario");
		usuario1.setSenha("4321");
		listaUsuarios.add(usuario1);	
	}
	
	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		Banco.lista.add(empresa);
	}
	
	public List<Empresa> getEmpresas() {
		return Banco.lista;
	}
	
	public List<Usuario> getUsuarios() {
		return Banco.listaUsuarios;
	}

	public void remove(Integer id) {
		Iterator<Empresa> it = lista.iterator();
		while (it.hasNext()) {
			Empresa empresa = it.next();
			if (empresa.getId() == id) {
				it.remove();
			}
		}
	}

	public Empresa buscaEmpresa(Integer id) {
		for (Empresa empresa : lista) {
			if (empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
	}

	public Usuario existeUsuario(String login, String senha) {
		for (Usuario usuario : listaUsuarios) {
			if (usuario.ehIgual(login, senha)) {
				return usuario;
			}
		}
		return null;
	}
}
