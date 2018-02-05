<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pet</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create Pet' : 'Edit Pet'}</h2>
    <hr>
    <jsp:useBean id="pet" type="com.online.shelter.pet.servlet.model.Pet" scope="request"/>
    <form method="post" action="pets">
        <input type="hidden" name="id" value="${pet.id}">
        <dl>
            <dt>Create Date:</dt>
            <dd><input type="date" value="${pet.createdDate}" name = "createDate"></dd>
        </dl>
        <dl>
            <dt>Type Pet:</dt>
           <%-- <dd><input type="text" value="${pet.typePet}" size="50" name = "typePet"></dd>--%>
            <dd><select name="typePet">
                <option>Cat</option>
                <option>Dog</option>
                <option>Others</option>
            </select></dd>
        </dl>
        <dl>
            <dt>Name Pet:</dt>
            <dd><input type="text" value="${meal.namePet}"size="50" name="namePet"></dd>
        </dl>
        <dl>
            <dt>Breed:</dt>
            <dd><input type="text" value="${meal.breed}"size="15" name="breed"></dd>
        </dl>
        <dl>
            <dt>Sex:</dt>
            <dd><input type="text" value="${meal.sex}"size="1" name="sex"></dd>
        </dl>
        <dl>
            <dt>Color:</dt>
            <dd><input type="text" value="${meal.color}"size="15" name="color"></dd>
        </dl>
        <dl>
            <dt>Age:</dt>
            <dd><input type="step" value="${meal.age}" name="age"></dd>
        </dl>
        <dl>
            <dt>Growth:</dt>
            <dd><input type="number" value="${meal.growth}" name="growth"></dd>
        </dl>
        <dl>
            <dt>Weight:</dt>
            <dd><input type="step" value="${meal.weight}" name="weight"></dd>
        </dl>
        <dl>
            <dt>Name Person:</dt>
            <dd><input type="text" value="${meal.namePerson}"  size="15" name="namePerson"></dd>
        </dl>
        <dl>
            <dt>Phone:</dt>
            <dd><input type="tel" value="${meal.phone}"  size="20" name="phone"></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="email" value="${meal.email}"  size="20" name="email"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
