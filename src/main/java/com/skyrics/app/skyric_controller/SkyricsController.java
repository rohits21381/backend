package com.skyrics.app.skyric_controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skyrics.app.payloads.ProductDto;
import com.skyrics.app.payloads.UserDto;
import com.skyrics.app.services.ProductService;
import com.skyrics.app.services.UserService;
import com.skyrics.app.utility.AppConstant;
import com.skyrics.app.utility.ProductResponse;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/skyrics")
public class SkyricsController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/home" , method = RequestMethod.GET)
	public String homeSkyrics(@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_Size ,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir,Model model) {
		
		ProductResponse allProduct = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
		Set<ProductDto> content = allProduct.getContent();
		model.addAttribute("title","skyrics_HomePage");
		model.addAttribute("products",content);
		return "skyrics";
	}

	@GetMapping("/signup")
	public String signupSkyrics(Model model) {
		model.addAttribute("title","SignUp page");
		return "signup";
	}

	//POST-create user
	@PostMapping("/success")
	public String registrationHandler(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model ) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("title","SignUp page");
			model.addAttribute("userDto",userDto);
			System.out.println("blinding result"+bindingResult);
			return "signup";
		}
		model.addAttribute("title","Success page");
		this.userService.createUser(userDto);
		model.addAttribute("userDto",userDto);
		return "success";
	}

	@GetMapping("/login")
	public String loginSkyrics(Model model) {
		model.addAttribute("title","Login page");
		return "login";
	}
}
