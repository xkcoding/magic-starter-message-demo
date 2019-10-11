package com.xkcoding.magicstartermessagedemo;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import com.xkcoding.magic.message.model.email.EmailMessage;
import com.xkcoding.magic.message.model.email.support.EmailAttachment;
import com.xkcoding.magic.message.model.email.support.EmailStaticResource;
import com.xkcoding.magic.message.support.email.EmailMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 邮件测试类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/11 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailMessageSenderTests {
    @Autowired
    private EmailMessageSender emailMessageSender;

    /**
     * 测试简单邮件
     */
    @Test
    public void testSimpleEmail() {
        EmailMessage simple = EmailMessage.simpleEmail();
        simple.setFrom("xkcoding");
        simple.setSubject("主题：测试简单邮件");
        simple.setContent("内容：测试简单邮件");
        simple.setTos(Collections.singletonList("237497819@qq.com"));
        emailMessageSender.send(simple);
    }

    /**
     * 测试复杂邮件
     */
    @Test
    public void testMimeEmail() throws URISyntaxException {
        EmailMessage mime = EmailMessage.mimeEmail();
        mime.setFrom("xkcoding");
        mime.setSubject("主题：测试复杂邮件");
        // 设置模板
        mime.setTemplate("test");
        // 设置参数
        Map<String, Object> params = new HashMap<>();
        params.put("project", "Spring Boot Demo");
        params.put("author", "Yangkai.Shen");
        params.put("url", "https://github.com/xkcoding/spring-boot-demo");
        mime.setParams(params);

        Resource resource = ResourceUtil.getResourceObj("static/xkcoding.png");
        // 设置附件
        EmailAttachment attachment = new EmailAttachment(resource.getName(), new File(resource.getUrl().toURI()));
        mime.setAttachments(Collections.singletonList(attachment));

        // 设置静态资源
        EmailStaticResource staticResource = new EmailStaticResource("xkcoding", new File(resource.getUrl().toURI()));
        mime.setStaticResources(Collections.singletonList(staticResource));

        mime.setTos(Collections.singletonList("237497819@qq.com"));
        emailMessageSender.send(mime);
    }

}
