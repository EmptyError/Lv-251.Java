<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 25.07.2017
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">


    <div class="row row-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-6 col-md-3">
                    <a href="#" class="thumbnail">
                        <img width="200" height="200" src="data:image/jpeg;base64,${clinic.photo}"
                             alt="...">
                    </a>
                </div>
                    <h4 class="card-title">${clinic.clinic_name}</h4>
                    <p class="card-text">${clinic.description}</p>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-block">
            <h4><spring:message code="messages.contact"/></h4>
            <p class="card-text">${clinic.contact.address}</p>
            <p class="card-text">${clinic.contact.district}</p>
            <p class="card-text">${clinic.contact.city}</p>
            <p class="card-text">${clinic.contact.zipCode}</p>
            <p class="card-text">${clinic.contact.firstPhone}</p>
            <p class="card-text">${clinic.contact.secondPhone}</p>
            <p class="card-text">${clinic.contact.thirdPhone}</p>
            <p class="card-text">${clinic.contact.email}</p>
        </div>

        <%--<iframe--%>
                <%--height="450"--%>
                <%--frameborder="0"--%>
                <%--src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBp1Qv4qS0nsGmiiXY1Z60u8DLrBGgalQQ&q=${mappoint}" allowfullscreen>--%>
        <%--</iframe>--%>
    </div>

</div>
