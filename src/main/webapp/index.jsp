<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Gestion des Etudiant
</h1>
<br/>
<a href="user-servlet">Hello Servlet</a>
<form>
    <label for="fname">First name:</label><br>
    <input type="text" id="fname" name="fname"><br>
    <label for="lname">Last name:</label><br>
    <input type="text" id="lname" name="lname">
    <label for="note">Note :</label><br>
    <input type="number" id="note" name="note">
    <button name="ajouter">
        Ajouter
    </button>
</form>

</body>
</html>