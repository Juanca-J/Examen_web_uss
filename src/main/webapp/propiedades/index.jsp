<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="quevedo.dominio.Propiedades,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%

	String texto = (String) request.getAttribute("texto");
	String mensaje=(String) request.getAttribute("mensaje");
	if(texto==null){
		texto="";
	}
	List<Propiedades> lista = (ArrayList<Propiedades>) request.getAttribute("lista");
%>


<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Propiedades</title>
<%@include file="../layouts/maincss.jsp"%>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<%@include file="../layouts/navbar.jsp"%>
		<%@include file="../layouts/menu.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header"></div>
			<!-- /.content-header -->
			<!--CONTENIDO-->
			<!-- TABLA -->
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- /.col-md-6 -->
						<div class="col-lg-12">
							<div class="card">
								<div class="card-header">
									<h5 class="m-0">
										Propiedades
										<a class="btn btn-primary" href="propiedades?accion=create">
											<i class="fas fa-file"></i> Nuevo
										</a>
									 
										<a href="" class="btn btn-success"><i
											class="fas fa-file-csv"></i> CSV</a>
										
									</h5>
								</div>
								<div class="card-body">
									<form action="#" method="get">
										<div class="input-group">
											<input name="texto"  autocomplete="off" type="text" class="form-control" value="<%=texto %>">
											<div class="input-group-append">
												<button type="submit" class="btn btn-info">
													<i class="fas fa-search"></i> Buscar
												</button>
											</div>
										</div>
									</form>
									<c:if test="${mensaje!= null}">
									<div class="alert alert-info alert-dismissible fade show mt-2">
										<span class="alert-icon"><i class="fa fa-info"></i></span> <span
											class="alert-text"> <%=mensaje %> </span>
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									</c:if>
									<c:if test="${lista== null}">
									<div class="alert alert-secondary mt-2" role="alert">No
										hay registros para mostrar</div>
									</c:if>
									<div class="mt-2 table-responsive">
										<table
											class="table table-striped table-bordered table-hover table-sm">
											<thead>
												<tr>
													<th style="width: 10%">Opciones</th>
													<th style="width: 10%">Id</th>
													<th style="width: 20%">Nombre</th>
													<th style="width: 30%">Direccion</th>
													<th style="width: 10%">Caracteristica</th>
													<th style="width: 10%">Estado</th>
													<th style="width: 10%">Precio</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<c:if test="${lista.size() <= 0}">
														<tr>
															<td colspan="6">No hay resultados</td>
															<td>${lista.size()}</td>
														</tr>
													</c:if>
												</tr>
												<c:forEach items="${lista}" var="reg">
													<tr>
														<td><a href="propiedades?accion=edit&id=${reg.getId() }" class="btn btn-warning btn-sm"><i
																class="fas fa-edit"></i> </a>
															<a class="btn btn-danger btn-sm" href="propiedades?accion=delete&id=${reg.getId() }">
																<i class="fas fa-trash"></i>
															</a></td>
														<td>${reg.getId()}</td>
														<td>${reg.getNombre()}</td>
														<td>${reg.getDireccion()}</td>
														<td>${reg.getCaracteristicas()}</td>
														<td>${reg.getEstado()}</td>
														<td>${reg.getPrecioalquiler()}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- /.col-md-6 -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- FIN TABLA -->
			<!--FIN CONTENIDO-->
		</div>
		<!-- /.content-wrapper -->

		<%@include file="../layouts/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@include file="../layouts/mainjs.jsp"%>
</body>

</html>