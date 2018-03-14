package pl.myprojects.valentinepostcard.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(MultipartException.class)
	public String handleError(MultipartException e, RedirectAttributes redirectAttr) {
		
		redirectAttr.addFlashAttribute("error", e.getCause().getMessage());
		
		return "redirect:/";
	}
}
