<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
		<c:if test="${not empty empresa}">
			Empresa ${empresa} cadastrada no banco
		</c:if>
		<c:if test="${empty empresa}">
			Nenhuma empresa cadastrada neste comando
		</c:if>
	</body>
</html>
