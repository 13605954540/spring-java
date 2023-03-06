package org.example.repository;

import org.example.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.io.Serializable;

public interface UserRepository extends ElasticsearchRepository<User, Serializable> {

}
