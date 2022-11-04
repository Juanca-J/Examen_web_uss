<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="quevedo.dominio.Propiedades,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String control;
	Propiedades reg = (Propiedades) request.getAttribute("reg");
	
	if(reg==null){
		reg.setId(0);
	}
	if(reg.getId()==0){
		control="store";
	}else{
		control="update";
	}

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
										Productos
									</h5>
								</div>
								<div class="card-body">
									
									<form class="align-items-center" action="propiedades?accion=<%=control%>" method="post">
										<div class="col-lg-6">
											<div class="form-group">
												<label>Nombre: </label>
												<input type="hidden" name="id"
												class="form-control" value="<%=reg.getId()%>" />
												
												<input name="nombreP" type="text" class="form-control" value="<%=reg.getNombre()%>">
											</div>
											
											<div class="form-group">
												<label>Dirección: </label>
												<input name="direccionP" type="text" class="form-control" value="<%=reg.getDireccion()%>">
											</div>
											
											<div class="form-group">
												<label>Caracteristica: </label>
												<input name="caracteristicaP" type="text" step=".01" class="form-control" value="<%=reg.getCaracteristicas()%>">
											</div>
											
											<div class="form-group">
												<label>Estado: </label>
												<input name="estadoP" type="text" class="form-control" value="<%=reg.getEstado()%>">
											</div>
											
											<div class="form-group">
												<label>Precio: </label>
												<input name="precioP" type="number" step=".01" class="form-control" value="<%=reg.getPrecioalquiler()%>">
											</div>
											
											<div class="form-group">
												<button type="submit" class="btn btn-outline-success">Guardar</button>
												<a href="propiedades"  class="btn btn-outline-danger">Cancelar</a>
											</div>
											
										</div>
									</form>
									
									
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
	<div class="modal fade" id="modal-update">
		<div class="modal-dialog modal-lg"></div>
	</div>
	<%@include file="../layouts/mainjs.jsp"%>
	
</body>

</html>