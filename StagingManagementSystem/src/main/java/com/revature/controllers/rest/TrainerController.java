package com.revature.controllers.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Trainer;
import com.revature.services.GenericService;

@RestController
@RequestMapping("trainer")
public interface TrainerController extends GenericService<Trainer>
{

}
