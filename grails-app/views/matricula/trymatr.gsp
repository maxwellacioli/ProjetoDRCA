<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Matrícula - Resultado</title>
    </head>
    <body>
        <g:hasErrors bean="${this.aluno}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.aluno}" var="error">
                <li><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
    </body>
</html>
