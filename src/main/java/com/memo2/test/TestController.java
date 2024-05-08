package com.memo2.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test1")
public class TestController {
	@RequestMapping("/11")
  @ResponseBody
    public String helloWorld() {
        return "Hello world!";
    }
  
  @RequestMapping("/1")
  @ResponseBody
  public String test1_1() {
	  return "<h2>예제1번</h2>문자열을 Reponse Body로 보내는 예제";
  }
  
  //	Map 출력 => JSON String 출력
  @RequestMapping("/2")
  @ResponseBody
  public Map<String, Object> test1_2() {
	  Map<String, Object> map = new HashMap<>();
	  map.put("사과", 4);
	  map.put("포도", 5);
	  
	  
	  return map;
  }
  
  @RequestMapping("/7")
  public String test2() {
	  return "test/test01";
  }
  
}