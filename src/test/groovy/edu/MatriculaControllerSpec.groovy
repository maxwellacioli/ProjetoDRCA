package edu

import grails.test.mixin.*
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MatriculaController)
@Mock([Aluno, AlunoController])
class MatriculaControllerSpec extends Specification {

	def populateValidAluno(params) {
	
        assert params != null
		
		params["id"] = 1
        params["matricula"] = 98124812
		params["nome"] = "Alex Carvalho"
		params["creditosObrigatorios"] = 130
		params["creditosEletivos"] = 50
		params["curso.id"] = 1
        
    }

	void "A action index retorna uma tabela vazia quando não há alunos"() {

        when:"Quando a action index é executada"
            controller.index()

        then:"O modelo está correto"
            !model.alunoList // Lista está de alunos vazia
            model.alunoCount == 0
		
    }
	
	void "A action index retorna todos os alunos quando existe algum"() {

        when:"Quando um aluno é salvo"
			request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
           
			populateValidAluno(params)
			def aluno = new Aluno(params)
            def alunoController = new AlunoController()
			alunoController.save(aluno)
			
		then:"Ele existe"
			def alunosTotal = Aluno.count()
            alunosTotal == 1	
			
		when:"Quando a action index é executada e existe aluno"
            controller.index()

        then:"Ela retorna todos os alunos"
            model.alunoList != null // Lista está não está vazia
            model.alunoCount == alunosTotal
    }
	
	
	void "A action lsdisc retorna ao index quando o aluno não existe"() {

        when:"Quando a action lsdisc é executada com um aluno que não existe"
			controller.lsdisc(null)

        then:"A página é redirecionada para a view index"
            response.redirectedUrl == '/matricula/index'
    }
	
	void "A action trymatr retorna ao index quando a disciplina não existe"() {

        when:"Quando a action trymatr é executada com uma disciplina que não existe"
            controller.trymatr(null)

        then:"A página é redirecionada para a view index"
            response.redirectedUrl == '/matricula/index'
		
    }
	
	
}
