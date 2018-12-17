package com.tyshawn.framework;

/**
 * 提供相关配置项常量
 */
public interface ConfigConstant {

    //配置文件的名称
    String CONFIG_FILE = "handwritten.properties";

    //数据库
    String JDBC_DRIVER = "handwritten.framework.jdbc.driver";
    String JDBC_URL = "handwritten.framework.jdbc.url";
    String JDBC_USERNAME = "handwritten.framework.jdbc.username";
    String JDBC_PASSWORD = "handwritten.framework.jdbc.password";

    //文件地址
    String APP_BASE_PACKAGE = "handwritten.framework.app.base_package";
    String APP_JSP_PATH = "handwritten.framework.app.jsp_path";
    String APP_ASSET_PATH = "handwritten.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "handwritten.framework.app.upload_limit";
}
