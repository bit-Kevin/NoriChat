package io.github.kevvy.chat_java;


import com.zaxxer.hikari.pool.HikariPool;
import io.github.kevvy.chat_java.mappers.UserMapper;
import io.github.kevvy.chat_java.redis.RedisUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor // 自动生成构造方法并注入依赖
public class InitHealthCheck implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitHealthCheck.class);

    @Resource
    private DataSource dataSource;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private Environment env;

    @Resource
    private UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("-----------------------------------------------------");
        logger.info("         🚀 服务启动中：系统健康检查开始              ");
        logger.info("-----------------------------------------------------");

        checkYaml();
        checkDatabase();
        checkRedis();
        checkJWTConfig();
        checkMapper();
        checkUploadDir();

        logger.info("-----------------------------------------------------");
        logger.info("         🟢 自检完成，服务启动成功！                ");
        logger.info("-----------------------------------------------------");
    }

    // ---------------------------------------------------------
    // 1. 检查 YAML 配置是否加载正常
    // ---------------------------------------------------------
    private void checkYaml() {
        try {
            String port = env.getProperty("server.port");
            logger.info("🟢 YAML 配置加载正常（端口: {}）", port);
        } catch (Exception e) {
            logger.error("🔴 YAML 配置加载失败！请检查 application.yml", e);
        }
    }

    // ---------------------------------------------------------
    // 2. 检查数据库连接
    // ---------------------------------------------------------
    private void checkDatabase() {
        try (Connection conn = dataSource.getConnection()) {
            logger.info("🟢 数据库连接正常");
        } catch (HikariPool.PoolInitializationException e) {
            logger.error("🔴 数据库连接池初始化失败，请检查 JDBC 配置", e);
        } catch (SQLException e) {
            logger.error("🔴 无法连接数据库，请检查账号/密码/端口配置", e);
        } catch (Exception e) {
            logger.error("🔴 未知数据库错误", e);
        }
    }

    // ---------------------------------------------------------
    // 3. 检查 Redis
    // ---------------------------------------------------------
    private void checkRedis() {
        try {
            redisUtil.get("healthCheck");
            logger.info("🟢 Redis 连接正常");
        } catch (RedisConnectionFailureException e) {
            logger.error("🔴 Redis 连接失败，请检查 Redis 地址、密码或服务是否启动", e);
        } catch (DataAccessException e) {
            logger.error("🔴 Redis 操作失败，请检查 Redis 配置", e);
        } catch (Exception e) {
            logger.error("🔴 未知 Redis 错误", e);
        }
    }

    // ---------------------------------------------------------
    // 4. 检查 JWT 配置
    // ---------------------------------------------------------
    private void checkJWTConfig() {
        String secret = env.getProperty("jwt.secret");
        if (secret == null || secret.length() < 16) {
            logger.error("🔴 JWT 密钥未正确配置！请检查 jwt.secret");
        } else {
            logger.info("🟢 JWT 配置正常");
        }
    }

    // ---------------------------------------------------------
    // 5. 检查 Mapper 是否扫描成功
    // ---------------------------------------------------------
    private void checkMapper() {
        try {
            userMapper.countAll();
            logger.info("🟢 MyBatis Mapper 加载正常");
        } catch (Exception e) {
            logger.error("🔴 MyBatis Mapper 加载失败！请检查 @MapperScan 路径", e);
        }
    }

    // ---------------------------------------------------------
    // 6. 检查上传目录是否可写
    // ---------------------------------------------------------
    private void checkUploadDir() {
        File dir = new File("upload/");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                logger.error("🔴 无法创建 upload/ 目录，请检查文件权限！");
                return;
            }
        }

        if (!dir.canWrite()) {
            logger.error("🔴 upload/ 目录不可写，请检查服务器权限！");
        } else {
            logger.info("🟢 上传目录权限正常");
        }
    }
}

