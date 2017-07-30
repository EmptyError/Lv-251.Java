<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/css/search.css"/>" rel="stylesheet">
<div class="container">
    <%--<div class="row">--%>
        <%--<h2></h2>--%>
        <%--<div id="custom-search-input">--%>

            <%--<div class="input-group col-md-12">--%>
                <%--<input type="text" id="autocomplete" name="search" class="  search-query form-control" placeholder='<spring:message code="messages.searchDoctors"/>' />--%>
                <%--<span class="input-group-btn">--%>
                                    <%--<button class="btn btn-danger" onclick="task2()"  id="search-doctor">--%>
                                        <%--<span class=" glyphicon glyphicon-search"></span>--%>
                                    <%--</button>--%>

                                <%--</span>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>
        <div class="container">
            <div class="row" style="margin-top: 50px">

                <nav class="navbar navbar-inverse">
                    <div  class="container-fluid">

                        <div class="navbar-form " >
                            <div class="form-group">

                                <select class="selectpicker form-control"style="width:210px " id="selectDocOrClinic">
                                    <option  value="3"><spring:message code="messages.chooseSearch"/></option>
                                    <option  value="1"><spring:message code="messages.doctorsSearch"/></option>
                                    <option   value="2"><spring:message code="messages.doctorsSearchBySpec"/></option>
                                </select>
                                <div class="input-group ">
                                    <span class="input-group-addon" id="sizing-addon1"><i class="fa fa-ambulance change" aria-hidden="true" style="color: #226ed9"></i></span>
                                    <input type="text" class="form-control " id="autocomplete" style="width: 530px" placeholder='<spring:message code="messages.searchClinics"/>'aria-describedby="sizing-addon1">
                                </div>
                                <input id="autocomplete-districts" type="text" class="form-control " style="width: 210px" placeholder='<spring:message code="messages.district"/>' >
                            </div>
                            <a href="/allDoctors"><button id="main-search-btn" class="btn btn-facebook"><spring:message code="messages.search"/></button></a>
                        </div>

                    </div>

                </nav>



            </div>
        </div>
</div>
<div id="content">



<c:choose>
    <c:when test="${doctors.size()>0}">
        <div class="container">
        <c:forEach items="${doctors}" var="doctor">
            <a href="doctors/${doctor.id}">
                <div class="row row-content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xs-6 col-md-3">
                                <a href="#" class="thumbnail">
                                    <img width="200" height="200" src="/resources/img/User_Default.png" alt="...">
                                </a>
                            </div>
                            <a href="doctors/${doctor.id}" class="btn-link"> <span class="doc-name">${doctor.firstname} ${doctor.lastname} ${doctor.middlename}</span></a>
                            <p><spring:message code="messages.specialization"/>:${doctor.specialization.name}</p>
                        </div>

                    </div>
                </div>
            </a>
        </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container">
        <div class="row row-content">
            <div class="container-fluid">
               There are no doctors in database
            </div>
        </div>
    </c:otherwise>
</c:choose>
        </div>
</div>