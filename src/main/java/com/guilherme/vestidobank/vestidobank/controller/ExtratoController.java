package com.guilherme.vestidobank.vestidobank.controller;

import com.guilherme.vestidobank.vestidobank.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/v1")
public class ExtratoController {

    @PostMapping("/gerarExtrato")
    @ResponseBody
    public ModelAndView gerarExtrato(@RequestBody User dados) {
        String username = dados.getUsername();
        String password = username.substring(0, 20);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "http://localhost:8080/extrato.pdf");
        modelAndView.addObject("password", password);
        modelAndView.addObject("availableDownloads", 2);
        modelAndView.setViewName("extratoResult");
        return modelAndView;
    }
}