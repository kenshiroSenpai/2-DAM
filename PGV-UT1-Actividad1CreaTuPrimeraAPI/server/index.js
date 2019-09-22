var express = require("express");
var bodyParser = require('body-parser');
let app = express();

app.use(bodyParser.urlencoded({ extended: true }));

const list1Object = ({
    name: "list1",
    products: [{
        name: "banana"
    },
    {
        name: "pera"
    }]
});

const arrayList1 = [{
    name: "list1",
    products: [{
        name: "banana"
    },
    {
        name: "pera"
    }]
},
{
    name: "list2",
    products: [{
        name: "manzana"
    },
    {
        name: "pera"
    }]
}];

const searchListObject = (req, res) => {
    if(list1Object.name == req.query.name.toLowerCase()){
        res.json(list1Object);
    }else{
        res.json({
            message: "not found"
        });
    }
}

const searchProductsObject = (req, res) => {
    const result = list1Object.products.filter(prod => prod.name == req.query.products.toLowerCase());
    if (result.length == 0) {
        res.json({
            message: "not found"
        });
    }
    res.json(list1Object);
}

const showListObject = (req, res) => {
    res.json(list1Object);
}

const showListArray = (req, res) => {
    res.json(arrayList1);
}

const showProductInListArray = (req,res) => {
    const result = arrayList1.filter(prod => prod.name == req.params.name.toLowerCase()); 
    if (result.length == 0) {
        res.json({
            message: "not found"
        });
    }
    res.json(result[0].products);
}

// const createList = (req, res) => {
//     var list = res.body;
//     if(list.name){
//         const result = ({
//             name: list.name,
//             products: name.products
//         });
//         res.json(result);
//     }else{
//         res.json({
//             message: "not found"
//         });
//     }
// }

const searchListArray = (req, res) => {
    const result = arrayList1.filter(list => list.name == req.query.name.toLowerCase());
    if (result.length == 0) {
        res.json({
            message: "not found"
        });
    }
    res.json(result);
}

const searchProductsArray = (req, res) => {
    const result = arrayList1.filter(item => {
        for(let i = 0; i<item.products.length; i++){
            if(item.products[i].name == req.query.products.toLowerCase()){
                return true;
            }
        }
        return false;
    }
    );
    if (result.length == 0) {
        res.json({
            message: "not found"
        });
    }
    res.json(result);
}

app.get("/searchListArray", searchListArray);
app.get("/searchProductsArray", searchProductsArray);
app.get("/showListObject", showListObject);
app.get("/showListArray", showListArray);
app.get("/showListArray/:name", showProductInListArray);
app.get("/searchListObject", searchListObject);
app.get("/searchProductsObject", searchProductsObject);
//app.post("/createList", createList);
const port = 40000;
app.listen(port, () => {
    console.log("http://localhost/:" + port)
});

