<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <c:choose>
  <c:when test="${autenticado}">
  	<head>
	 <title>Vers&atilde;o do Sistema</title>
  	 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	 <meta charset="utf-8" />
	 <meta name="viewport" content="width=device-width, initial-scale=1"/>
	 <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	 <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	 <meta http-equiv="Pragma" content="no-cache" />
	 <meta http-equiv="Expires" content="-1" />
	 <meta http-equiv="Cache-Control" content="no-cache" />
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"/>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"/>
	</head>
	<body>
	 <div class="container">
	  <br/>
	  <h1>${nome} </h1>
	  <div class="panel-group" id="accordion">
	   <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#sistema">Sistema</a>
	      </h4>
	    </div>
	    <div id="sistema" class="panel-collapse collapse in">
	      <div class="panel-body">
	       <div class="alert alert-danger"><b>Ambiente</b>  ${ambiente}</div>
			<div class="row">
			  <div class="col-md-4"><div class="alert alert-info"><b>Vers&atilde;o</b>  ${versao}</div></div>
			  <div class="col-md-4"><div class="alert alert-info"><b>Build</b>  ${build}</div></div>
			  <div class="col-md-4"><div class="alert alert-info"><b>JDK</b>  ${java}</div></div>
			</div>
			<div class="row">
			  <div class="col-md-4"><div class="alert alert-info"><b>Servidor</b>  ${servidor}</div></div>
			  <div class="col-md-4"><div class="alert alert-info"><b>Servlet API</b>  ${servlet}</div></div>
			  <div class="col-md-4"><div class="alert alert-info"><b>JSP</b>  <%=JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %></div></div>
			</div>
	       <div class="alert alert-danger">Servidor iniciado em <b>${dataDeStartup}</b> - h&aacute; ${uptimeEmDias} <b>dias</b> ${uptimeEmHoras} <b>horas</b> ${uptimeEmMinutos} <b>minutos</b> ${uptimeEmSegundos} <b>segundos</b></div>
	      </div>
	    </div>
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#classLoader">ClassLoader</a>
	      </h4>
	    </div>
	    <div id="classLoader" class="panel-collapse collapse">
	      <div class="panel-body">
	        <p><pre>${classloader}</pre></p>
	      </div>
	    </div>
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#bibliotecas">Bibliotecas</a>
	      </h4>
	    </div>
	    <div id="bibliotecas" class="panel-collapse collapse">
	      <div class="panel-body">
	        <p> ${libs}</p>
	      </div>
	    </div>
	  </div>
	 </div>
   </div>

   </body>
   </html>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  </c:when>
  <c:otherwise>
	<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	 <title> Vers&atilde;o do Sistema</title>
	</head>
	<body>
     <form action="versao" method="post">
      <input type="password" name="pwd" id="pwd"  size=50 maxlength="500" />
      <input type="submit"/>
     </form>
    </body>
   </html>
   <script language="JavaScript">
     document.getElementById('pwd').focus();
   </script>
  </c:otherwise>
 </c:choose>
