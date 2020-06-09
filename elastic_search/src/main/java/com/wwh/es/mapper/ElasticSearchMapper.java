package com.wwh.es.mapper;

import com.wwh.es.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wangwenhao
 * @description ElasticSearchMapper
 * @date 2020-06-01 10:30
 */
public interface ElasticSearchMapper extends ElasticsearchRepository<Student,Long> {
}
