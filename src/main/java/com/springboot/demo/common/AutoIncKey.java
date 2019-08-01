package com.springboot.demo.common;

import com.mongodb.client.result.UpdateResult;
import com.springboot.demo.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class AutoIncKey {

    @Autowired
    MongoTemplate mongoTemplate;

    public int getNextSequence(String collectionName) {
        MongoSequence seq = mongoTemplate.findAndModify(
                new Query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().upsert(true).returnNew(true),
                MongoSequence.class);

        return seq.getSeq();
    }

    public <T> int upsertOne(T object, Query query, Class<T> entityClass) {
        Update update = new Update();
        HashMap<String, Object> map = ObjectUtil.beanToMap(object);
        map.forEach(update::set);
        UpdateResult updateResult = mongoTemplate.upsert(query, update, entityClass);
        return updateResult.getModifiedCount() == 0 && updateResult.getUpsertedId() == null ? 0 : 1;
    }
}
