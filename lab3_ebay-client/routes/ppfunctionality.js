var ejs=require("ejs");
var http=require("http");

var assert = require('assert');
var soap = require('soap');
var baseURL = "http://localhost:8080/lab3/services";


exports.addtocart=function(req,res){

    console.log("inside the function to add to cart database");

    var user=req.session.user;
    var title = req.param("title");
    var itemdesc = req.param("itemdesc");
    var id=req.param("id");
    var rate = req.param("rate");
    var quantity = req.param("qty");
    var total = req.param("total");

    console.log(id);
    console.log(quantity);
    console.log(rate);
    console.log(total);
    console.log(user);

       if(req.session.user!="guest")
    {
        var option = {
            ignoredNamespaces : true
        };

        var url = baseURL+"/ppfunctionality?wsdl";
        console.log(url);
        var args = {user:user,title:title,itemdesc:itemdesc,pid:id,rate:rate,quantity:quantity};
        soap.createClient(url,option,function(err,client) {
            client.addtocart(args, function (err, results) {
                console.log(results);
                console.log(results.addtocartReturn);

                if (err) {
                    throw err;

                }
                else {
                    if (results.addtocartReturn) {
                        //rediret to login page
                        console.log("success reroute to index");
                        var result = {
                            success: true
                        };
                        res.send(result);
                    }
                    else {
                        console.log("some error when rendering");
                        res.end(err);
                    }
                }
                console.log("user added");

            });
        });
    }
    else
    {
        var result={
            success:false
        };
        res.send(result);
    }
};

exports.addtobid=function(req,res){  // to add to bidding cart

    console.log("inside cal total fuccntion");
    var n=Date();

    var user=req.session.user;
    //var title = req.param("title");
    //var itemdesc = req.param("itemdesc");
    var id=req.param("pid");
    //var rate = req.param("rate");
    var quantity = req.param("qty");
    var bidamt = req.param("bidamt");
    var title=req.param("title");

    console.log(id);
    console.log(quantity);
    console.log(bidamt);
    console.log(user);
    //console.log(user);



    if (req.session.user != "guest") {
        var option = {
            ignoredNamespaces : true
        };

        var url = baseURL+"/ppfunctionality?wsdl";
        console.log(url);
        var args = {user:user,pid:id,bidamt:bidamt,quantity:quantity};
        soap.createClient(url,option,function(err,client) {
            client.addtobid(args, function (err, results) {

                console.log(results);
                console.log(results.addtobidReturn);

                if (err) {
                    console.log("err"+err);
                }
                else {
                    if (results.addtobidReturn) {
                        //rediret to login page

                        console.log("success reroute to index");
                        var result = {
                            success: true
                        };
                        res.send(result);
                    }
                    else {
                        console.log("some error when rendering");
                        res.end(err);
                    }
                }
                console.log("user bid added");

            });
        });
    }
    else {
        var result = {
            success: false
        };
        res.send(result);
    }



};


exports.getcart=function(req,res){

    console.log("inside getcart");

    var p_id=(req.session.pid);
    console.log("pid "+p_id);

    var option = {
        ignoredNamespaces : true
    };

    var url = baseURL+"/ppfunctionality?wsdl";
    console.log(url);
    var args = {pid:p_id};
    soap.createClient(url,option,function(err,client) {
        client.getcart(args, function (err, results) {
            console.log(results);


            var data=JSON.parse(results.getcartReturn);
            console.log(data);



            var date = new Date();
            var day = date.getDate();

            if (err) {
                throw err;

            }
            else {

                if (results.getcartReturn) {
                    console.log("valid pid");

                    var data = {
                        result: data,
                        success: true,
                        day: day
                    };

                    console.log(data);
                    res.send(data);

                }
                else {
                    //invalid login
                    var success = '';
                    var result = {
                        success: false
                    };
                    console.log(result);
                    res.send(result);

                    //res.render("index");    //render the file
                    console.log("item doesnt exists");
                }
            }

        });
    });
};


