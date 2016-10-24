package edu

import grails.test.mixin.*
import spock.lang.*

@TestFor(AlunoController)
@Mock(Aluno)
class AlunoControllerSpec extends Specification {

	//Integer matricula
	//String nome
	//Integer creditosObrigatorios
	//Integer creditosEletivos
	//Curso curso

    def populateValidParams(params) {
	
        assert params != null
		
		params["id"] = 1
        params["matricula"] = 98124812
		params["nome"] = "Alex Carvalho"
		params["creditosObrigatorios"] = 130
		params["creditosEletivos"] = 50
		params["curso.id"] = 1
        
    }

    void "A action index retorna o modelo correto"() {
	
        when:"A action index é executada"
            controller.index()

        then:"O modelo está correto"
            !model.alunoList
            model.alunoCount == 0
    }

    void "A action create retorna o modelo correto"() {
        when:"A action create é executada"
            controller.create()

        then:"O modelo esta correto"
            model.aluno!= null
    }

    void "A action save persiste uma instância"() {

        when:"A action save é executada com uma instância inválida"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            //Esse aluno ní£o possui parâmetros válidos, pois nenhum deles foi setado
			def aluno = new Aluno() 			
            aluno.validate()
            controller.save(aluno)

        then:"A view create é renderizada de novo"
            model.aluno!= null
            view == 'create'

        when:"A action save é executada com uma instância válida"
            response.reset()
            populateValidParams(params)
            aluno = new Aluno(params)
            controller.save(aluno)

        then:"A página é redirecionada para a view show com id correto"
            response.redirectedUrl == '/aluno/show/1'
            controller.flash.message != null // Mensagem Salvo com Sucesso!
            Aluno.count() == 1
    }

    void "A action show retorna o modelo correto"() {
	
        when:"A action show é executada com uma instância null"
            controller.show(null)

        then:"Um erro 404 é retornado"
            response.status == 404

		when:"A action show é executada com uma instância inválida"
            //Esse aluno ní£o possui parâmetros válidos, pois nenhum deles foi setado
			controller.show(new Aluno())

        then:"Um erro 404 é retornado"
            response.status == 404
			
        when:"A action show é executada com uma instância válida"
            populateValidParams(params)
            def aluno = new Aluno(params)
            controller.show(aluno)

        then:"O modelo é povoado com os dados da instância válida"
            model.aluno == aluno
    }

    void "A action edit retorna o modelo correto"() {
	
        when:"A action edit é executada com uma instância null"
            controller.edit(null)

        then:"Um erro 404 é retornado"
            response.status == 404
			
		when:"A action edit é executada com uma instância inválida"
            //Esse aluno ní£o possui parâmetros válidos, pois nenhum deles foi setado
			controller.edit(new Aluno())

        then:"Um erro 404 é retornado"
            response.status == 404

        when:"A action show é executada com uma instância válida"
            populateValidParams(params)
            def aluno = new Aluno(params)
            controller.edit(aluno)

        then:"O modelo é povoado com os dados da instância válida"
            model.aluno == aluno
    }

    void "A action update atualiza uma instância"() {
	
        when:"A action update é executada com uma instância null"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"Um erro 404 é retornado e página é redirecionada para a view index"
            response.redirectedUrl == '/aluno/index'
            flash.message != null // Mensagem de Ní£o Encontrado

         when:"A action update é executada com uma instância inválida"
            response.reset()
            //Esse aluno ní£o possui parâmetros válidos, pois nenhum deles foi setado
			def aluno = new Aluno()
            aluno.validate()
            controller.update(aluno)

        then:"A view create é renderizada de novo e o modelo está correto"
            view == 'edit'
            model.aluno == aluno

        when:"A action update é executada com uma instância válida"
            response.reset()
            populateValidParams(params)
            aluno = new Aluno(params).save(flush: true)
            controller.update(aluno)

        then:"A página é redirecionada para a view show com id correto"
            aluno != null
            response.redirectedUrl == "/aluno/show/$aluno.id"
            flash.message != null // Mensagem Atualizado com Sucesso!
    }

    void "A action delete exclui uma instância"() {
	
        when:"A action delete é executada com uma instância nula"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"Um erro 404 é retornado e página é redirecionada para a view index"
            response.redirectedUrl == '/aluno/index'
            flash.message != null // Mensagem de Ní£o Encontrado

        when:"Uma instância válida de aluno é criada e salva"
            response.reset()
            populateValidParams(params)
            def aluno = new Aluno(params).save(flush: true)

        then:"Ela existe"
            Aluno.count() == 1

        when:"A action delete é executada com a instância"
			controller.delete(aluno)

        then:"A instância é excluí­da"
            Aluno.count() == 0
            response.redirectedUrl == '/aluno/index'
            flash.message != null // Mesagem de Excluí­do com Sucesso!
    }
}
