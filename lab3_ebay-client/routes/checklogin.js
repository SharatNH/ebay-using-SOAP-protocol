var ejs=require("ejs");
var http=require("http");

var assert = require('assert');
var soap = require('soap');
var baseURL = "http://localhost:8080/lab3/services";


exports.checklogin=function(req,res){

    console.log("inside checklogin");
    var username=req.param('user');
    var password=req.param('password');
    console.log(username);

    var n=Date();

    var option = {
        ignoredNamespaces : true
    };

    var url = baseURL+"/checklogin?wsdl";
    console.log(url);
    var args = {username: req.param("user"), password: req.param("password")};
    soap.createClient(url,option,function(err,client) {
        client.validlogin(args, function (err, results) {

            console.log(results);
            console.log(results.validloginReturn);
            if (err) {
                throw err;
            }
            else if (results.validloginReturn) {
                req.session.user = username;   //session needs to be created
                req.session.sessionstart = n;  //prefer updating this in db
                console.log(req.session.user);
                console.log("valid user");
                json_responses = {"statusCode": 200};
                console.log("Response is:" + JSON.stringify(json_responses));
                res.send(json_responses);
            }
            else {
                console.log("invalid user");
                json_responses = {"statusCode": 401};
                console.log("Response is" + JSON.stringify(json_responses));
                res.send(json_responses);
            }
        });
    });
};