var ejs=require("ejs");
var http=require("http");

var soap = require('soap');
var baseURL = "http://localhost:8080/lab3/services";

exports.registeruser=function(req,res){

    console.log("inside registeruser.js");

    var user=req.param("newuser");
    var password=req.param("password");
    var lastname=req.param("lastname");
    var firstname=req.param("firstname");

    var option = {
        ignoredNamespaces : true
    };

    var url = baseURL+"/registeruser?wsdl";
    console.log(url);
    var args = {username: req.param("newuser"), password: req.param("password"),firstname:req.param("firstname"),lastname:req.param("lastname")};
    soap.createClient(url,option,function(err,client) {
        client.newuser(args, function (err, results) {

            console.log(results);
            console.log(results.newuserReturn);
            if (err) {
                throw err;

            }
            else {
                if (results.newuserReturn) {
                    json_response = {"statusCode": 200};
                    res.send(json_response);
                }
                else {
                    json_response = {"statusCode": 400};
                    console.log("json statuscode" + JSON.stringify(json_response));
                    res.send(json_response);
                }
            }
        });
    });
};