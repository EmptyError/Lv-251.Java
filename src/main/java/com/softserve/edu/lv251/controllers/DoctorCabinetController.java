package com.softserve.edu.lv251.controllers;

import com.google.gson.Gson;
import com.softserve.edu.lv251.dto.pojos.AppointmentsDTO;
import com.softserve.edu.lv251.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Author: Vitaliy Kovalevskyy
 */
@Controller
public class DoctorCabinetController {

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value = "/doctor/сabinet",method = RequestMethod.GET)
    public String home(ModelMap model){
        return "doctor_schedule";
    }

    @RequestMapping(value = "/doctor/cabinet/getApp", method = RequestMethod.POST)
    @ResponseBody
    public String getApp(Principal principal)
    {
        return new Gson().toJson(AppointmentsDTO.convert(appointmentService.getAppiontmentbyDoctorsEmail(principal.getName())));
    }


    @RequestMapping(value = "doctor/patients",method = RequestMethod.GET)
    public String patients(){
        return "doctor_cabinet_patients";
    }

}
