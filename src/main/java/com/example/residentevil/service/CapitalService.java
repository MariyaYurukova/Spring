package com.example.residentevil.service;

import com.example.residentevil.domain.models.service.CapitalServiceModel;

import java.util.List;

public interface CapitalService {

    List<CapitalServiceModel> findAllCapitals();

}
