package kr.co.tworld.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.tworld.main.service.LoadTestService;

@Controller
public class MainController {
	
	@Autowired
	private LoadTestService loadTestService;
	
	@ResponseBody
	@RequestMapping("/drive")
	public void drive(HttpServletRequest request, @RequestParam(value="cnt", defaultValue="3") int cnt, @RequestParam(value="type", defaultValue="main") String type) {
		
		for(int i=1; i<=cnt; i++){
			
			ResponseEntity<String> result = loadTestService.twdNodeTest(request, type, i);
			
			System.out.println("[" + type + "] : " + i);
			//System.out.println("[" + type + "] " + i + " : " + result.getHeaders());
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}