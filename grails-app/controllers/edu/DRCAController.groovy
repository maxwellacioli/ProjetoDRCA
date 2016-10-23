package edu

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DRCAController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DRCA.list(params), model:[DRCACount: DRCA.count()]
    }

    def show(DRCA DRCA) {
        respond DRCA
    }

    def create() {
        respond new DRCA(params)
    }

    @Transactional
    def save(DRCA DRCA) {
        if (DRCA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (DRCA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond DRCA.errors, view:'create'
            return
        }

        DRCA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'DRCA.label', default: 'DRCA'), DRCA.id])
                redirect DRCA
            }
            '*' { respond DRCA, [status: CREATED] }
        }
    }

    def edit(DRCA DRCA) {
        respond DRCA
    }

    @Transactional
    def update(DRCA DRCA) {
        if (DRCA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (DRCA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond DRCA.errors, view:'edit'
            return
        }

        DRCA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DRCA.label', default: 'DRCA'), DRCA.id])
                redirect DRCA
            }
            '*'{ respond DRCA, [status: OK] }
        }
    }

    @Transactional
    def delete(DRCA DRCA) {

        if (DRCA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        DRCA.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DRCA.label', default: 'DRCA'), DRCA.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'DRCA.label', default: 'DRCA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
