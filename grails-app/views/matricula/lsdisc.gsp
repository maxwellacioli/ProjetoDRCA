<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'disciplina.label', default: 'Disciplina')}" />
        <title>Disciplinas disponíveis</title>
    </head>
    <body>
		
		<div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>                
				<li><a class="back" href="${createLink(uri: '/matricula')}"><g:message code="default.back.label"/></a></li>                
            </ul>
        </div>
		
        <div id="list-disciplina" class="content scaffold-list" role="main">            
            
			
			<h1>Disciplinas disponíveis para ${aluno.nome}</h1>
			
			<g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
			
			<g:hasErrors bean="${this.aluno}">
				<ul class="errors" role="alert">
					<g:eachError bean="${this.aluno}" var="error">
					<li><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			
			
			<div class="list">
				<table>
					<thead>
						<tr>
							<th>Escolha uma disciplina</th>
						</tr>						
					</thead>
					<tbody>
						<g:each in="${disciplinaList}" status="i" var="disciplinaInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

								<td>
									<g:link action="trymatr" controller="matricula"
										id="${disciplinaInstance.id}"
										params="[a: aluno.id]">
										${fieldValue(bean: disciplinaInstance, field: "nome")}
										<input type="hidden" name="aluno" value="${aluno.id}" />
									</g:link>
									
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
				
			<div class="pagination">
                <g:paginate total="${disciplinaList.size() ?: 0}" />
            </div>
            
        </div>
    </body>
</html>
