
var request = require('request')
    ,express = require('express')
    ,assert = require("assert")
    ,http = require("http");

describe('http tests', function(){

    it('should return signin page for correct url', function(done){
        http.get('http://localhost:3000/signin', function(res) {

            assert.equal(true, results.validloginReturn);
            done();
        })
    });

    it('should not return page for wrong url', function(done){
        http.get('http://localhost:3000', function(res) {
            assert.equal(true, results.validloginReturn);
            done();
        })
    });

    it('should return register page for correct url', function(done){
        http.get('http://localhost:3000/selling', function(res) {
            assert.equal(200, res.statusCode);
            done();
        })
    });


    it('should login for correct credentials', function(done) {
        request.post(
            'http://localhost:3000/signin',
            { form: { "username": 'balaji1',"password":'balaji' } },
            function (error, response, body) {
                assert.equal(true, results.validloginReturn);
                done();
            }
        );
    });

    it('should not allow to login', function(done) {
        request.post(
            'http://localhost:3000/signin',
            { form: { "username": 'balaji1',"password":'test' } },
            function (error, response, body) {
                assert.equal(false, results.validloginReturn);
                done();
            }
        );
    });
});