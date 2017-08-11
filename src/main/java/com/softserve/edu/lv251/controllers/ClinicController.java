package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.entity.Clinics;
import com.softserve.edu.lv251.service.ClinicService;
import com.softserve.edu.lv251.service.PagingSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    @Qualifier("clinicService")
    private PagingSizeService<Clinics> pagingSizeService;

    @GetMapping("/{current}")
    public ModelAndView tenClinics(@PathVariable("current") Integer chainIndex) {
        ModelAndView model = new ModelAndView("clinics");
        model.addObject("getClinics", pagingSizeService.getEntity(chainIndex, 10));
        model.addObject("numberChain", pagingSizeService.numberOfPaging(10));
        return model;
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String clinicDetails(@PathVariable(name = "id", required = true) long id, Model model) {
        Clinics clinic = clinicService.getClinicByID(id);
        model.addAttribute("clinic", clinic);
        model.addAttribute("mappoint", clinic.getContact().getAddress() + " " + clinic.getContact().getCity());
        return "clinic_details";
    }

    @RequestMapping(value = "/map")
    public String map() {
        return "map";
    }
}