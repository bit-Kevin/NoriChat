package io.github.kevvy.chat_java.entity.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
//@Component("APPConfig") 定义名称
@Component
public class APPConfig {

    @Value("${ws.port:}")
    private  Integer wsPost;
    @Value("${project.folder:}")
    private  String projectFolder;
    @Value("${admin.email:}")
    private  String adminEmail;
}
