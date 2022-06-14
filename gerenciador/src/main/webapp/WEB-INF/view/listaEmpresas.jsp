<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import= "java.util.List, br.com.carlosnazario.modelo.Empresa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Java Standard Taglib</title>
</head>
<body>
	<c:import url="LogoutParcial.jsp"/>
	Usuario Logado: ${usuarioLogado.login}<br>
	<a href="/gerenciador/entrada?acao=FormNovaEmpresa">Nova Empresa</a><br><br>
	Lista de Empresas <br/>
	<ul>
		<c:forEach items="${empresas}" var="empresa">
			<li> ${empresa.nome} </li>
			<li> Fundacao: <fmt:formatDate value="${empresa.dataAbertura}"/> </li>
			<li> <a href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}"> Edita Dados </a> </li>
			<li> <a href="/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}"> Remove </a> </li><br>
		</c:forEach>
	</ul>
</body>
</html>