<html>

<body>
<div id="container">
    	<div class="content">
    		<div class="textContent">
				<div class="bread">
			    	<a href="javascript:void(0);"><spring:message	code="generico.home" /></a>/ <span> Erro no Sistema</span>
			    </div>
				<div class="actions"></div>

				<h1>Sistema indispon&iacute;vel</h1>

				<br />
				<p id="msgRetorno" class="msg red"><i class="ico cancel"></i><strong>${mensagem}</strong></p>

				<div class="actionForm">
					<button onclick="javascript:history.back(1);" name='btnVoltar' id='btnVoltar'  class="button" type="button"><i class="ico arrowL"></i>Voltar</button>&nbsp;&nbsp;
				</div>
			</div>
		</div>
	</div>

<!--

  <%=String.valueOf( new java.util.Date())%>
  =======================================================
       Stacktrace
  =======================================================
    ${stacktrace}

  =======================================================

 -->
</body>
</html>
