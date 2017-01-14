var ejs=require("ejs");
var http=require("http");

var soap = require('soap');
var baseURL = "http://localhost:8080/lab3/services";

exports.listshoppingcart=function(req,res) {
    console.log("inside shoppingcart");

    if (req.session.user) {


        var option = {
            ignoredNamespaces : true
        };

        var url = baseURL+"/shoppingcart?wsdl";
        console.log(url);
        var args = {user:req.session.user};
        soap.createClient(url,option,function(err,client) {
            client.listshoppingcart(args, function (err, results) {

                console.log(results);

                var data=JSON.parse(results.listshoppingcartReturn);
                console.log(data);

                console.log(data.length);

                if (err) {
                    throw err;
                }
                else if (results.listshoppingcartReturn) {
                    var data = {
                        result: data,
                        success: true,
                        user: req.session.user
                    };
                    console.log(data);
                    res.send(data);
                }
                else {
                    console.log("nothing to show");
                    var data = {
                        result: " ",
                        success: false,
                        user: req.session.user
                    };

                    console.log(data);
                    res.send(data);
                }
            });
        });
    }
    else {
        var data = {
            failed: true
        };

        console.log(data);
        res.send(data);

    }
};


exports.deletecart=function(req,res) {

    var id = req.param("id");
    console.log("inside the delete card function");
    console.log(id);

    var option = {
        ignoredNamespaces : true
    };

    var url = baseURL+"/shoppingcart?wsdl";
    console.log(url);
    var args = {user:req.session.user,pid:id};
    soap.createClient(url,option,function(err,client) {
        client.deletecart(args, function (err, results) {

            console.log(results);
            console.log(results.deletecartReturn);
            if (err) {
                throw err;
            }
            else {
                if (results.deletecartReturn) {
                    var data = {
                        success: true
                    };
                    console.log(data);
                    res.send(data);
                }
                else {
                    console.log("somethning went wrong when delete cart");
                    var data = {
                        success: false
                    };
                    console.log(data);
                    res.send(data);
                }
            }

        });
    });

};