//URL du Servlet
var url = "/CatalogueJulien/CJServlet";

//Gestion panier enlever
var index = 0;
var panier = document.getElementById("panier");

//Ajax get pour ajouter un élément au panier avec créations de node + appendChild
function ajoutPanier(id){
    $.get(url, { name:"ajout", leID:id }, function(response) {
        var unsplit = response;
        var split = unsplit.split("#");
        var tr = document.createElement("tr");
        tr.setAttribute("id", "tr"+index);
        
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var td4 = document.createElement("td");
        
        var button = document.createElement("button");
        button.setAttribute("onclick", "retraitPanier(this.name)");
        button.setAttribute("name", index);
        
        var txt1 = document.createTextNode(split[0]);
        var txt2 = document.createTextNode(split[1]);
        var txt3 = document.createTextNode(split[2]);
        var txt4 = document.createTextNode("Retrait du panier");
        
        td1.appendChild(txt1);
        td2.appendChild(txt2);
        td3.appendChild(txt3);
        button.appendChild(txt4);
        td4.appendChild(button);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        
        index++;
        var panier = document.getElementById("panier");
        panier.appendChild(tr);
    });
}   

//Ajax get pour afficher les descriptions
function afficherDescription(id){
    $.get(url, { name:"affiche", leID:id }, function(response) {
        document.getElementById("des"+id).innerHTML = response;
    });
}

function retraitPanier(id){
    var retrait = document.getElementById("tr"+id);
    retrait.parentNode.removeChild(retrait);    
}

