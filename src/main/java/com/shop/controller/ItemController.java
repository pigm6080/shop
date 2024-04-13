package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/admin/item/new")
    public String itemForm(){
        System.out.println("여기까지옴.");
        return "/item/itemForm";
    }
}
