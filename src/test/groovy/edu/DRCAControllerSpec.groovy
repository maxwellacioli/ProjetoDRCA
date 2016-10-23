package edu

import grails.test.mixin.*
import spock.lang.*

@TestFor(DRCAController)
@Mock(DRCA)
class DRCAControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.DRCAList
            model.DRCACount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.DRCA!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def DRCA = new DRCA()
            DRCA.validate()
            controller.save(DRCA)

        then:"The create view is rendered again with the correct model"
            model.DRCA!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            DRCA = new DRCA(params)

            controller.save(DRCA)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/DRCA/show/1'
            controller.flash.message != null
            DRCA.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def DRCA = new DRCA(params)
            controller.show(DRCA)

        then:"A model is populated containing the domain instance"
            model.DRCA == DRCA
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def DRCA = new DRCA(params)
            controller.edit(DRCA)

        then:"A model is populated containing the domain instance"
            model.DRCA == DRCA
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/DRCA/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def DRCA = new DRCA()
            DRCA.validate()
            controller.update(DRCA)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.DRCA == DRCA

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            DRCA = new DRCA(params).save(flush: true)
            controller.update(DRCA)

        then:"A redirect is issued to the show action"
            DRCA != null
            response.redirectedUrl == "/DRCA/show/$DRCA.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/DRCA/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def DRCA = new DRCA(params).save(flush: true)

        then:"It exists"
            DRCA.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(DRCA)

        then:"The instance is deleted"
            DRCA.count() == 0
            response.redirectedUrl == '/DRCA/index'
            flash.message != null
    }
}
