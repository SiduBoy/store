package com.jyb.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /*
    数据库连接池：
    1.DBCP
    2.C3P0
    3.HiKari
    * HikariProxyConnection@1410791643 wrapping com.mysql.cj.jdbc.ConnectionImpl@64e1377c

     * */
    @Test
    void ceshi() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
