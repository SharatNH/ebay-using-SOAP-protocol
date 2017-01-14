var ejs=require("ejs");
var http=require("http");
var assert = require('assert');

var soap = require('soap');
var baseURL = "http://localhost:8080/lab3/services";


exports.displayinindex=function(req,res){     //to display advt

    console.log("inside displayinindex");


    var option = {
        ignoredNamespaces : true
    };

    var url = baseURL+"/displayinindex?wsdl";
    console.log(url);
    var args = {};
    soap.createClient(url,option,function(err,client) {
        client.displayindex(args, function (err, results) {
            debugger
            console.log("fetch items");

            // console.log(results.displayindexReturn);
            // console.log("//////////////////////////////////////");
            // var tempstr=results.displayindexReturn;
            // console.log(tempstr.length);
            // var newStr = tempstr.substring(1, tempstr.length-1);
            // console.log(newStr);
            // console.log("//////////////////////////////////////");
           var data=JSON.parse(results.displayindexReturn);
           console.log(data);

            console.log(data.length);
            console.log("//////////////////////////////////////");

            if (err) {
                console.log("err");
                console.log(err);

            }
            else {
                if (data.length>0) {
                    var data = {
                        result: data,
                        success: true,
                        user: req.session.user
                    };
                    console.log(data);
                    res.send(data);
                }
                else {
                    var success = '';
                    var data = {

                        success: false,
                        user: req.session.user
                    };
                    console.log(data);
                    res.send(data);

                    //res.render("index");    //render the file
                    console.log("no items to display");
                }
            }
        });
    });

};