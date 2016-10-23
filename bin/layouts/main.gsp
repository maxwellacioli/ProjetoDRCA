<!doctype html>
<html lang="en" class="no-js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
            </div>
            <div class="nav-drop" aria-expanded="false" style="height: 0.8px;">
                <ul>
                    <content>
				        <li class="sub-menu-parent">
				            <a href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">DRCA<span class="caret"></span></a>
				            <ul class="sub-menu">
				                <li > <a href="/aluno/index">Aluno</a> </li>
				                <li> <a href="/curso/index">Curso</a> </li>
				                <!--<li> <a href="/DRCA/index">DRCA</a> </li>-->
				                <li> <a href="/departamento/index">Departamento</a> </li>
				                <li> <a href="/disciplina/index">Disciplina</a> </li>
				                <li> <a href="/professor/index">Professor</a> </li>
				                <li> <a href="/secretaria/index">Secretaria</a> </li>
				            </ul>
				        </li>        
				    </content>
                </ul>
            </div>
        </div>
    </div>

	<div class="div-out">
		<div class="div-in">
			<g:layoutBody/>
		</div>
	</div>
	
    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
