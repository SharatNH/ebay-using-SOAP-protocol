var ejs=require("ejs");
var http=require("http");


var assert = require('assert');
var soap = require('soap');
var baseURL = "http://localhost:8080/lab3/services";

exports.callproductpage=function(req,res) {    //call the product page

   var pid = req.param("id");
    //pid1=req.query("id");
    console.log(pid);

    console.log("inside callproductpage");
    if (req.session.user == undefined) {
        req.session.user = "guest";
        req.session.pid = pid;
    }
    else {
        req.session.pid = pid;
    }

    var n = Date();

    var option = {
        ignoredNamespaces : true
    };

    var url = baseURL+"/userlogs?wsdl";
    console.log(url);
    var args = {user:req.session.user,pid:pid};
    soap.createClient(url,option,function(err,client) {
        client.userlog(args, function (err, results) {

            console.log(results);
            if (results.userlogReturn) {
                console.log("entered");
                //res.render('productpage', { title: 'Express' });
                // res.send(doc);
                res.render('productpage', {title: 'Express'});
            }
            else {
               console.log("///////////////////////");
                console.log("some err while rendering");

            }
        });
    });
};

    //res.render('productpage', { title: 'Express' });


    /*console.log("callproductpage");

     pid=req.param("productid");

     console.log(pid);

     ejs.renderFile('./views/productpage.ejs',function (err,result){

     if (!err)
     {

     res.end(result);
     }
     // render or error
     else
     {
     res.end('An error occurred');
     console.log(err);
     }

     });*/

