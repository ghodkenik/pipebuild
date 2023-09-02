package com.test.springtest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.mongo.Product;
import com.test.mongo.ProductRepository;

@Controller
public class HelloController {

	/**@GetMapping("/")
	public String index() {
		System.out.println(" ****************** This is something to test ****************** ");
		return "index";
	}**/
}