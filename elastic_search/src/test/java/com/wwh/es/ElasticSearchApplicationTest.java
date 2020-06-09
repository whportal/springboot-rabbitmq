package com.wwh.es;

import com.wwh.es.entity.Student;
import com.wwh.es.mapper.ElasticSearchMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwenhao
 * @description ElasticSearchApplicationTest
 * @date 2020-06-01 10:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchApplicationTest {

    @Autowired
    private ElasticSearchMapper searchMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void insertData() {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId((long) i)
                    .setName("王五_" + i)
                    .setNickName("王麻子_" + i)
                    .setAddress("中国北京市朝阳区_" + i)
                    .setAge(i);
            studentList.add(student);
        }
        searchMapper.saveAll(studentList);
    }

    @Test
    public void selectAll() {
        Iterable<Student> studentList = searchMapper.findAll();
        studentList.forEach(System.out::println);
    }

    @Test
    public void multipleQuery() {
    }
}
