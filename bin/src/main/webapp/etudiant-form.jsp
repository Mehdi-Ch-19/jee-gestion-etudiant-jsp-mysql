<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2/22/2024
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>




<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${etudiant != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${etudiant == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${etudiant != null}">
                                Edit Etudiant
                            </c:if>
                            <c:if test="${etudiant == null}">
                                Add New Etudiant
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${etudiant != null}">
                        <input type="hidden" name="id" value="<c:out value='${etudiant.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Etudiant Name</label> <input type="text" value="<c:out value='${etudiant.name}' />" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Etudiant Email</label> <input type="text" value="<c:out value='${etudiant.email}' />" class="form-control" name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Etudiant Note</label> <input type="text" value="<c:out value='${etudiant.note}' />" class="form-control" name="country">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

</body>
</html>
