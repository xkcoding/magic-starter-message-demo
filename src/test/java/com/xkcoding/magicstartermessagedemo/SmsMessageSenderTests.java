package com.xkcoding.magicstartermessagedemo;

import com.xkcoding.magic.message.model.sms.SmsMessage;
import com.xkcoding.magic.message.support.sms.SmsMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 短信测试类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/11 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsMessageSenderTests {
    @Autowired
    private SmsMessageSender smsMessageSender;

    /**
     * 测试短信
     */
    @Test
    public void testSms() {
        SmsMessage sms = new SmsMessage();
        sms.setMobile("17326075631");

        // 短信模板：尊敬的读者，代码日记发布文章《${article}》，欢迎阅读！
        Map<String, String> map = new HashMap<>();
        map.put("article", "测试短信发送");
        sms.setParams(map);
        sms.setOutId(sms.getMobile());

        sms.setSignName("代码日记");
        sms.setTemplateCode("article_notification");
        smsMessageSender.send(sms);
    }

}
