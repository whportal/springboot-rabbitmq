package com.wwh.es.controller;

import com.wwh.es.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wangwenhao
 * @description ElasticsearchController
 * @date 2020-06-04 13:47
 */
@RestController
@RequestMapping("es")
public class ElasticsearchController {

    @GetMapping("list")
    public List<Student> listStudent(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        return null;
    }
}
