package com.shop.controller;

import java.util.Map;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


public class RenderingController {

    public RenderingController() {
    }

    public Object render(Map<String, Object> map, String string) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(map, string));
    }
}
