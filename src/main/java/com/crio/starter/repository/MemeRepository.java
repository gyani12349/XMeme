package com.crio.starter.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.crio.starter.data.MemeEntity;
public interface MemeRepository extends MongoRepository<MemeEntity,String>{  
    List<MemeEntity> findTop100ByOrderByIdDesc();

    boolean existsByNameAndUrlAndCaption(String name, String url, String caption);
}