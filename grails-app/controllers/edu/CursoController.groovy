package edu

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CursoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Curso.list(params), model:[cursoCount: Curso.count()]
    }

    def show(Curso curso) {
        respond curso
    }

    def create() {		
        respond new Curso(params)
    }

    @Transactional
    def save(Curso curso) {
        if (curso == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (curso.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond curso.errors, view:'create'
            return
        }

        curso.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'curso.label', default: 'Curso'), curso.id])
                redirect curso
            }
            '*' { respond curso, [status: CREATED] }
        }
    }

    def edit(Curso curso) {
        respond curso
    }

    @Transactional
    def update(Curso curso) {
        if (curso == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (curso.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond curso.errors, view:'edit'
            return
        }

        curso.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'curso.label', default: 'Curso'), curso.id])
                redirect curso
            }
            '*'{ respond curso, [status: OK] }
        }
    }

    @Transactional
    def delete(Curso curso) {

        if (curso == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        curso.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'curso.label', default: 'Curso'), curso.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'curso.label', default: 'Curso'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
