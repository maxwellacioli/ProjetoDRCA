package edu

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SecretariaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Secretaria.list(params), model:[secretariaCount: Secretaria.count()]
    }

    def show(Secretaria secretaria) {
        respond secretaria
    }

    def create() {
        respond new Secretaria(params)
    }

    @Transactional
    def save(Secretaria secretaria) {
        if (secretaria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secretaria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secretaria.errors, view:'create'
            return
        }

        def secList = Secretaria.findAllByDepartamento(secretaria.departamento)
  
		if(secList.size()==0 || (secList.size()==1 && secretaria.grad != secList.get(0).grad)) { 
		   secretaria.save flush:true
		} else {
		   transactionStatus.setRollbackOnly()
		   flash.message = "Essa secretaria ja foi criada"
		   respond secretaria.errors, view:'create'
		   return               
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secretaria.label', default: 'Secretaria'), secretaria.id])
                redirect secretaria
            }
            '*' { respond secretaria, [status: CREATED] }
        }
    }

    def edit(Secretaria secretaria) {
        respond secretaria
    }

    @Transactional
    def update(Secretaria secretaria) {
        if (secretaria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secretaria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secretaria.errors, view:'edit'
            return
        }

        def secList = Secretaria.findAllByDepartamento(secretaria.departamento)
  
		if(secList.size()==0 || (secList.size()==1 && secretaria.grad != secList.get(0).grad)) { 
		   secretaria.save flush:true
		} else {
		   transactionStatus.setRollbackOnly()
		   flash.message = "Essa secretaria ja foi criada"
		   respond secretaria.errors, view:'create'
		   return               
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'secretaria.label', default: 'Secretaria'), secretaria.id])
                redirect secretaria
            }
            '*'{ respond secretaria, [status: OK] }
        }
    }

    @Transactional
    def delete(Secretaria secretaria) {

        if (secretaria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        secretaria.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'secretaria.label', default: 'Secretaria'), secretaria.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secretaria.label', default: 'Secretaria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
