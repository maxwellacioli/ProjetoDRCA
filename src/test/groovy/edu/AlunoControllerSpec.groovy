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
	
        when:"A action index � executada"
            controller.index()

        then:"O modelo est� correto"
            !model.alunoList
            model.alunoCount == 0
    }

    void "A action create retorna o modelo correto"() {
        when:"A action create � executada"
            controller.create()

        then:"O modelo esta correto"
            model.aluno!= null
    }

    void "A action save persiste uma inst�ncia"() {

        when:"A action save � executada com uma inst�ncia inv�lida"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            //Esse aluno n�o possui par�metros v�lidos, pois nenhum deles foi setado
			def aluno = new Aluno() 			
            aluno.validate()
            controller.save(aluno)

        then:"A view create � renderizada de novo"
            model.aluno!= null
            view == 'create'

        when:"A action save � executada com uma inst�ncia v�lida"
            response.reset()
            populateValidParams(params)
            aluno = new Aluno(params)
            controller.save(aluno)

        then:"A p�gina � redirecionada para a view show com id correto"
            response.redirectedUrl == '/aluno/show/1'
            controller.flash.message != null // Mensagem Salvo com Sucesso!
            Aluno.count() == 1
    }

    void "A action show retorna o modelo correto"() {
	
        when:"A action show � executada com uma inst�ncia null"
            controller.show(null)

        then:"Um erro 404 � retornado"
            response.status == 404

		when:"A action show � executada com uma inst�ncia inv�lida"
            //Esse aluno n�o possui par�metros v�lidos, pois nenhum deles foi setado
			controller.show(new Aluno())

        then:"Um erro 404 � retornado"
            response.status == 404
			
        when:"A action show � executada com uma inst�ncia v�lida"
            populateValidParams(params)
            def aluno = new Aluno(params)
            controller.show(aluno)

        then:"O modelo � povoado com os dados da inst�ncia v�lida"
            model.aluno == aluno
    }

    void "A action edit retorna o modelo correto"() {
	
        when:"A action edit � executada com uma inst�ncia null"
            controller.edit(null)

        then:"Um erro 404 � retornado"
            response.status == 404
			
		when:"A action edit � executada com uma inst�ncia inv�lida"
            //Esse aluno n�o possui par�metros v�lidos, pois nenhum deles foi setado
			controller.edit(new Aluno())

        then:"Um erro 404 � retornado"
            response.status == 404

        when:"A action show � executada com uma inst�ncia v�lida"
            populateValidParams(params)
            def aluno = new Aluno(params)
            controller.edit(aluno)

        then:"O modelo � povoado com os dados da inst�ncia v�lida"
            model.aluno == aluno
    }

    void "A action update atualiza uma inst�ncia"() {
	
        when:"A action update � executada com uma inst�ncia null"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"Um erro 404 � retornado e p�gina � redirecionada para a view index"
            response.redirectedUrl == '/aluno/index'
            flash.message != null // Mensagem de N�o Encontrado

         when:"A action update � executada com uma inst�ncia inv�lida"
            response.reset()
            //Esse aluno n�o possui par�metros v�lidos, pois nenhum deles foi setado
			def aluno = new Aluno()
            aluno.validate()
            controller.update(aluno)

        then:"A view create � renderizada de novo e o modelo est� correto"
            view == 'edit'
            model.aluno == aluno

        when:"A action update � executada com uma inst�ncia v�lida"
            response.reset()
            populateValidParams(params)
            aluno = new Aluno(params).save(flush: true)
            controller.update(aluno)

        then:"A p�gina � redirecionada para a view show com id correto"
            aluno != null
            response.redirectedUrl == "/aluno/show/$aluno.id"
            flash.message != null // Mensagem Atualizado com Sucesso!
    }

    void "A action delete exclui uma inst�ncia"() {
	
        when:"A action delete � executada com uma inst�ncia nula"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"Um erro 404 � retornado e p�gina � redirecionada para a view index"
            response.redirectedUrl == '/aluno/index'
            flash.message != null // Mensagem de N�o Encontrado

        when:"Uma inst�ncia v�lida de aluno � criada e salva"
            response.reset()
            populateValidParams(params)
            def aluno = new Aluno(params).save(flush: true)

        then:"Ela existe"
            Aluno.count() == 1

        when:"A action delete � executada com a inst�ncia"
			controller.delete(aluno)

        then:"A inst�ncia � exclu�da"
            Aluno.count() == 0
            response.redirectedUrl == '/aluno/index'
            flash.message != null // Mesagem de Exclu�do com Sucesso!
    }
}
