package com.example.residentevil.web.controllers;


import com.example.residentevil.domain.models.binding.VirusAddBindingModel;
import com.example.residentevil.domain.models.views.CapitalListViewModel;
import com.example.residentevil.service.CapitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel) {

        modelAndView.addObject("bindingModel", bindingModel);
        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream()
        .map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList()));

        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);

            return super.view("add-virus", modelAndView);
        }

           return  super.redirect("/");
    }


    @GetMapping("/show")
    public ModelAndView show(){
        return (super.view("show"));
    }



}

