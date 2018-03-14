package pl.myprojects.valentinepostcard.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MainController {
	
	private static final String[] technologies = {"Spring Boot", "Thymeleaf", "Maven", "JavaScript", 
    		"jQuery", "HTML", "CSS", "Bootstrap"};
	
	private static final Map<String,String> licences;
	static {
        Map<String, String> tmp = new LinkedHashMap<String,String>();
        tmp.put("background", "Steve Schoger heropatterns.com");
        tmp.put("rubber stamp", "pngtree.com");
        tmp.put("post stamp", "stencilsanddecals.com");
        tmp.put("postcard paper", "pexels.com");
        licences = Collections.unmodifiableMap(tmp);
    }
	
	@GetMapping("/licences")
    public String showLicences(Model model) {
    	
    	model.addAttribute("licences", licences);
    	
        return "licences";
    }
	
	@GetMapping("/technologies")
    public String showTechnologies(Model model) {
    	
    	model.addAttribute("technologies", technologies);
    	
        return "technologies";
    }
	
	@GetMapping(value = { "/" })
	public String index(){
		return "upload";
	}
	
	@GetMapping("/collage")
	public String paintPicture(Model model){
		return "collage";
	}
	
	@PostMapping(value="/sending")
	public String redirect(@RequestParam(value="image") MultipartFile img, 
			@RequestParam(value="to") String to, @RequestParam(value="from") String from, 
			@RequestParam(value="letter") String letter, RedirectAttributes redirectAttr) throws IOException {
		
		String imgAsBase64 = Base64.encodeBase64String(img.getBytes());
		redirectAttr.addFlashAttribute("picture",imgAsBase64);
		redirectAttr.addFlashAttribute("to",to);
		redirectAttr.addFlashAttribute("from",from);
		redirectAttr.addFlashAttribute("letter",letter);
			return "redirect:/collage";	
	}
}
