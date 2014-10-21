<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%
   response.setHeader("Cache-control", "no-cache, no-store");
   response.setHeader("Pragma", "no-cache");
   response.setHeader("Expires", "-1");
%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
 <meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1"/>
 <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
 <meta http-equiv="Pragma" content="no-cache" />
 <meta http-equiv="Expires" content="-1" />
 <meta http-equiv="Cache-Control" content="no-cache" />
 <title><spring:message code="global.titulo" /></title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"/>
 <!-- Favicons -->
 <decorator:getProperty property="page.style"/>
 <decorator:head/>
</head>
<body>
    <nav class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="navbar-brand"><spring:message code="global.titulo" /></div>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li id="home"><a href="${pageContext.request.contextPath}/index.jsp"><spring:message code="global.home" /></a></li>
            <li id="home"><a href="${pageContext.request.contextPath}/versao" target="_BLANK"><spring:message code="global.versao" /></a></li>
            <li id="home"><a href="${pageContext.request.contextPath}/tms/monitoring" target="_BLANK"><spring:message code="global.monitoracao" /></a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Servi&ccedil;os REST<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a target="_BLANK" href="${pageContext.request.contextPath}/rest/servicos/getMelhorCaminho?origem=A&destino=D&autonomia=10&valorLitro=2.5">Buscar Melhor Caminho (GET)</a></li>
                <li><a target="_BLANK" href="${pageContext.request.contextPath}/rest/servicos/incluirMapa">Incluir Mapas (POST)</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  <decorator:body/>

 <div class="clearBody"></div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<decorator:getProperty property="page.script"/>
 </body>
</html>