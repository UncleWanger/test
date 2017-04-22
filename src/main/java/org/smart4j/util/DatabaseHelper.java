package org.smart4j.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by JERRY ZHANG on 2017-4-22.
 */
public class DatabaseHelper {
    private static Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);
    private static QueryRunner queryRunner = new QueryRunner();
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    static{
        Properties prop = PropUtil.loadProp("db.properties");
        DRIVER = PropUtil.getString(prop,"jdbc.driver");
        URL = PropUtil.getString(prop,"jdbc.url");
        USERNAME = PropUtil.getString(prop,"jdbc.username");
        PASSWORD = PropUtil.getString(prop,"jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return conn;
        } catch (Exception e) {
            logger.error("when get the connection occured a error",e);
        }
        return null;
    }

    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("when closing the connection occured a error",e);
            }
        }
    }

    public static<T> List<T>  queryEntityList(Class<T> entityClass,String sql,Object...params){
        List<T> entityList = null;
        try {
            entityList = queryRunner.query(sql,new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConnection(getConnection());
        }

        return entityList;
    }
}
