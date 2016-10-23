<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'aluno.label', default: 'Aluno')}" />
        <title>Alunos disponíveis</title>
    </head>
    <body>
        
		<div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>                
            </ul>
        </div>
		
		<h1>Todos os alunos da universidade</h1>
		
        <div id="list-aluno" class="content scaffold-list" role="main">
            
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            
			<div class="list">
				<table>
					<thead>
						<tr>
							<th>Escolha um aluno</th>
						</tr>						
					</thead>
					<tbody>
						<g:each in="${alunoList}" status="i" var="alunoInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

								<td>
									<g:link action="lsdisc" controller="matricula"
										id="${alunoInstance.id}">
										${fieldValue(bean: alunoInstance, field: "nome")}
									</g:link>
									
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>

            <div class="pagination">
                <g:paginate total="${alunoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>