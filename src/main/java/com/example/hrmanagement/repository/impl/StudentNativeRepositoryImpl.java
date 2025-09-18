package com.example.hrmanagement.repository.impl;
import com.example.hrmanagement.repository.StudentNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class StudentNativeRepositoryImpl implements StudentNativeRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public boolean removeStudent(Long studentId){
        HashMap params = new HashMap();
        params.put("id",studentId);

        int updateStatus = namedParameterJdbcTemplate.update(
                "DELETE FROM STUDENT WHERE ID=:id",
                params);

//        select or insert - namedParameterJdbcTemplate.execute();
//        update or delete - namedParameterJdbcTemplate.update();

        return updateStatus > 0 ? true : false;
    }
}
