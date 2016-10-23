package edu

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DepartamentoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Departamento.list(params), model:[departamentoCount: Departamento.count()]
    }

    def show(Departamento departamento) {
        respond departamento
    }

    def create() {
        respond new Departamento(params)
    }

    @Transactional
    def save(Departamento departamento) {
        if (departamento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (departamento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond departamento.errors, view:'create'
            return
        }

        departamento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'departamento.label', default: 'Departamento'), departamento.id])
                redirect departamento
            }
            '*' { respond departamento, [status: CREATED] }
        }
    }

    def edit(Departamento departamento) {
        respond departamento
    }

    @Transactional
    def update(Departamento departamento) {
        if (departamento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (departamento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond departamento.errors, view:'edit'
            return
        }

        departamento.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'departamento.label', default: 'Departamento'), departamento.id])
                redirect departamento
            }
            '*'{ respond departamento, [status: OK] }
        }
    }

    @Transactional
    def delete(Departamento departamento) {

        if (departamento == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        departamento.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'departamento.label', default: 'Departamento'), departamento.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'departamento.label', default: 'Departamento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
