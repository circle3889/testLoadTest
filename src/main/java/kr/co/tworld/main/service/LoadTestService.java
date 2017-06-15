package kr.co.tworld.main.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadTestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<String> twdNodeTest(HttpServletRequest request, String type, int idx) {

		ResponseEntity<String> rtn = null;
		
	    String sessionId = request.getSession().getId();
	    String tokenId = (String) request.getSession().getAttribute("tokenId");
	    String segment = "A1";
	    
		String apiGateway = "https://twdapigateway-interpervasive-pianist.sk.kr.mybluemix.net";
		String login = "/login/loginProc?id={id}&password={password}&SESSION={sessionId}";
		String main = "/main/main/notice";
		String freebill = "/freebill/freebill/main?tokenId={tokenId}";
		String hotbill = "/hotbill/hotbill?tokenId={tokenId}";
		String node = "https://twdnode.sk.kr.mybluemix.net/main?segment={segment}";
		String nodeLogin = "https://twdnode.sk.kr.mybluemix.net/bff/loginProc?id={idx}&password=1111";
		
		
		
		
		if(type.equals("login")){
			
			//System.out.println(apiGateway +  login);
			rtn = restTemplate.getForEntity(apiGateway +  login, String.class, idx, idx, sessionId);
			
		}else if(type.equals("main")){

			//System.out.println(apiGateway +  main);
			rtn = restTemplate.getForEntity(apiGateway +  main, String.class);
			
		}else if(type.equals("freebill")){

			//System.out.println(apiGateway +  freebill);
			rtn = restTemplate.getForEntity(apiGateway +  freebill, String.class, tokenId);
			
		}else if(type.equals("hotbill")){

			//System.out.println(apiGateway + hotbill);
			rtn = restTemplate.getForEntity(apiGateway + hotbill, String.class, tokenId);
			
		}else if(type.equals("node")){

			//System.out.println(node);
			rtn = restTemplate.getForEntity(node, String.class, segment);
			
		}else if(type.equals("nodeLogin")){

			//System.out.println(node);
			rtn = restTemplate.getForEntity(nodeLogin, String.class, idx);
			
		}
		
	    return rtn;
		
	}
}