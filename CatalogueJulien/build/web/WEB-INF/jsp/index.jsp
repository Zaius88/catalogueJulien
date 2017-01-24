<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<%-- Importation partielle de la db (sans description pour ajax) --%>
<sql:query var="produits" dataSource="jdbc/CJdatasource">
    SELECT id, nom FROM produits
</sql:query>

 <%-- Uniquement pour ne pas hardcoder les noms des colonnes dans le panier --%>
<sql:query var="produitsPanier" dataSource="jdbc/CJdatasource">
    SELECT * FROM produits
</sql:query>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="main.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <title>CatalogueJulien</title>
    </head>
    <body>
        <h1>Catalogue de Julien</h1>
        <h3> Catalogue </h3>
        <div id="catalogue">
            <table border="1">
            <!-- column headers -->
            <tr>
            <c:forEach var="columnName" items="${produits.columnNames}">
            <th><c:out value="${columnName}"/></th>
            </c:forEach>
            <th>Emplacement de la description</th>
            <th></th>
            <th></th>
            </tr>
            <!-- column data -->
            <c:forEach var="row" items="${produits.rowsByIndex}">
            <tr>
            <c:forEach var="column" items="${row}">
            <td><c:out value="${column}"/></td>
            </c:forEach>
            <td id="<c:out value="des${row[0]}"/>"></td>
            <td> <button onclick="afficherDescription(this.name)" name="<c:out value="${row[0]}"/>">Afficher la description</button></td>
            <td> <button onclick="ajoutPanier(this.name)" name="<c:out value="${row[0]}"/>">Ajouter au panier</button></td>
            </tr>
            </c:forEach>
            </table>
        </div>
        <h3>Panier</h3>
        <div>
            <table border="1" id="panier">
            <!-- column headers -->
            <tr>
            <c:forEach var="columnName" items="${produitsPanier.columnNames}">
            <th><c:out value="${columnName}"/></th>
            </c:forEach>
            <th></th>
            </tr>
            <!-- column data -->
            </table>
            
        </div>
    </body>
</html>
