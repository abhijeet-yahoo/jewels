package com.jiyasoft.jewelplus.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/data")
	@ResponseBody
	public String data() {
		return "{\"total\": 7800,\"rows\": [{\"id\":1,\"name\":\"test1\",\"price\":\"$1\"},{\"id\":2,\"name\":\"test2\",\"price\":\"$2\"},{\"id\":3,\"name\":\"test3\",\"price\":\"$3\"},{\"id\":4,\"name\":\"test4\",\"price\":\"$4\"},{\"id\":5,\"name\":\"test5\",\"price\":\"$5\"},{\"id\":6,\"name\":\"test6\",\"price\":\"$6\"},{\"id\":7,\"name\":\"test7\",\"price\":\"$7\"},{\"id\":8,\"name\":\"test8\",\"price\":\"$8\"},{\"id\":9,\"name\":\"test9\",\"price\":\"$9\"},{\"id\":10,\"name\":\"test10\",\"price\":\"$10\"}]}";
	}

}
