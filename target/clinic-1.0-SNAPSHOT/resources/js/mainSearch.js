/**
 * Created by Yana on 28.07.2017.
 */
$(document).ready(function() {
    clinicsAll();
    $("select").change(function(){
        if($(this).val() == 0){
            $(".fa-user-md").removeClass("fa-user-md");
            $(".change").addClass("fa-ambulance");
            $("#autocomplete").attr('placeholder',$("#clinic").html());
            clinicsByDistrict();
            console.log("You select 0");
            clinicsAll();

        } if(($(this).val() == 1)) {
            $(".change ").removeClass("fa-ambulance");
            $(".change").addClass("fa-user-md");
            $("#autocomplete").attr('placeholder',$("#doctor").html());
            console.log("You select 1")
            console.log($("#selectDocOrClinic").val());
            doctorsByDistrict();
            console.log("before all docs")
            allDocs();
        }
         if (($(this).val() == 2)) {console.log("You select 2");
            $(".change ").removeClass("fa-ambulance");
            $(".change").addClass("fa-user-md");
             $("#autocomplete").attr('placeholder',$("#docByspec").html());
            doctorsBySpecialization();
            doctorsByDistrict();
        }
    });

});

;
clinicsByDistrict();









function clinicsAll() {
    $("#autocomplete").autocomplete({
        serviceUrl: '/all/clinics',
        paramName: "name",
        delimiter: ",",
           transformResult: function (response) {
            console.log("before all clinics" + response.toString());
            return {
                   suggestions: $.map($.parseJSON(response), function (item) {
                    var i = item.clinic_name;
                    console.log(i);
                    var html="<a href='"+"/clinics/"+item.id+"'>"+i+"</a>";
                    return {value: i, data: item.id,url:'/clinics/'+item.id};

                })


            };


        },
        onSelect: function (suggestion) {
            console.log('You selected: ' + suggestion.value + ', ' + suggestion.data );
            var id = suggestion.data;

            $.ajax({
                url: '/search/clinics/'+id,
                method: 'GET',
                contentType: 'application/json',

                success: function (result) {
                    console.log(result.clinic_name);
                   $("#myCarousel").empty();
                    $(".content").empty();
                    $(".content").append("<div class='row row-content'> <div class='container-fluid'> <div class='row'>"+
                        "<div class='col-xs-6 col-md-3'> <a href='#' class='thumbnail'>"+
                        "<img width=200' height='200' src='data:image/jpeg;base64,"+result.photo+"' alt='...'></a></div>"+
                        "<a href='"+"clinics/"+result.id+"'class='btn-link'><span class='doc-name'>"+result.clinic_name+"</span></a><p>"+result.description+"</p>"+
                        " </div> </div>");

                }

            })

        }


    });

}


function clinicsByDistrict() {
    $("#autocomplete-districts").autocomplete({
        serviceUrl: '/districts/byName',
        paramName: "name",
        delimiter: ",",
        transformResult: function (response) {
            console.log("before clinics by districts");
            return {

                suggestions: $.map($.parseJSON(response), function (item) {

                    var i = item.name;
                    console.log(i);
                    return {value: i, data: item.name};
                })
            };
            },
        onSelect: function (suggestion) {
            console.log('You selected: ' + suggestion.value + ', ' + suggestion.data );
            var name = suggestion.data;
            $.ajax({
                url: '/search/clinics/by/district/'+name,
                method: 'GET',
                contentType: 'application/json',

                success: function (res) {


                    console.log(res.length);
                    $("#myCarousel").empty();
                    $(".content").empty();
                    for (var i = 0; i < res.length; i++) {
                        var photo="data:image/jpeg;base64,"+res[i].photo;
                        console.log(photo);
                        $(".content").append("<div class='row row-content'> <div class='container-fluid'> <div class='row'>"+
                            "<div class='col-xs-6 col-md-3'> <a href='#' class='thumbnail'>"+
                            "<img width=200' height='200' src='data:image/jpeg;base64,"+res[i].photo+"' alt='...'></a></div>"+
                            "<a href='"+"clinics/"+res[i].id+"'class='btn-link'><span class='doc-name'>"+res[i].clinic_name+"</span></a>"+
                            " </div> </div>");
                    }


                }

            })

        }


    });
}

function doctorsByDistrict() {
    $("#autocomplete-districts").autocomplete({
        serviceUrl: '/districts/byName',
        paramName: "name",
        noSuggestionNotice:'No results',
        showNoSuggestionNotice:true,
        delimiter: ",",
        transformResult: function (response) {
            console.log("before doc by districts");
            return {

                suggestions: $.map($.parseJSON(response), function (item) {

                    var i = item.name;
                    console.log(i);
                    return {value: i, data: item.name};
                })
            };
        },
        onSelect: function (suggestion) {
            console.log('You selected: ' + suggestion.value + ', ' + suggestion.data );
            var name = suggestion.data;
            $.ajax({
                url: '/search/doctors/by/district/'+name,
                method: 'GET',
                contentType: 'application/json',
                success: function (res) {
                    console.log(res.length);
                    $("#myCarousel").empty();
                    $("#content").empty();
                    for (var i = 0; i < res.length; i++) {
                        console.log("Doctors search by districts");
                        $("#content").append(" <div class='row row-content'> <div class='container-fluid'> <div class='row'>"+
                            "<div class='col-xs-6 col-md-3'> <a href='#' class='thumbnail'>"+
                            "<img width=200' height='200' src='data:image/jpeg;base64,"+res[i].photo+"' alt='...'></a></div>"+
                            "<a href='"+"clinic/"+res[i].id+"'class='btn-link'><span class='doc-name'>"+res[i].firstname+"</span></a>"+
                            "<p><spring:message code='messages.specialization'/>:"+res[i].specialization.name+"</p> </div> </div>")
                    }


                }

            })

        }


    });

}
function  doctorsBySpecialization() {
    console.log("doc by cpec");
    $("#autocomplete").autocomplete({
        serviceUrl: '/doc/by/spec',
        noSuggestionNotice:'No results',
        showNoSuggestionNotice:true,
        paramName: "name",
        delimiter: ",",
        transformResult: function (response) {
            console.log("before doc by spec" );
            return {

                suggestions: $.map($.parseJSON(response), function (item) {

                    var i = item.name;
                    console.log(i);
                    return {value: i, data: item.name};
                })
            };
        },
        onSelect: function (suggestion) {
            console.log('You selected: ' + suggestion.value + ', ' + suggestion.data );
            var name = suggestion.data;
            $.ajax({
                url: '/search/doctors/by/spec/'+name,
                method: 'GET',
                contentType: 'application/json',
                success: function (res) {
                    console.log(res.length);
                    $("#myCarousel").empty();
                    $("#content").empty();
                    for (var i = 0; i < res.length; i++) {
                        console.log("Doctors search by districts");
                        $("#content").append(" <div class='row row-content'> <div class='container-fluid'> <div class='row'>"+
                            "<div class='col-xs-6 col-md-3'> <a href='#' class='thumbnail'>"+
                            "<img width=200' height='200' src='data:image/jpeg;base64,"+res[i].photo+"' alt='...'></a></div>"+
                            "<a href='"+"doctors/"+res[i].id+"'class='btn-link'><span class='doc-name'>"+res[i].firstname+"</span></a>"+
                            "<p><spring:message code='messages.specialization'/>:"+res[i].specialization.name+"</p> </div> </div>")
                    }


                }

            })

        }


    });
}
