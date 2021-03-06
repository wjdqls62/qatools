package com.js.qa.qatools.hr.repository.mysql;
import com.js.qa.qatools.hr.entity.mysql.mysql_qa_test_hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface MySQL_Qa_Test_Hr extends JpaRepository<mysql_qa_test_hr, Integer>, QuerydslPredicateExecutor<mysql_qa_test_hr> {

    /**
     * AutoIncreament 초기화
     */
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE qa_test_hr AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncreament();
}
