package com.xkcoding.magicstartermessagedemo;

import com.xkcoding.magic.message.model.dingtalk.*;
import com.xkcoding.magic.message.model.dingtalk.support.Attention;
import com.xkcoding.magic.message.model.dingtalk.support.actioncard.ActionCardSingle;
import com.xkcoding.magic.message.model.dingtalk.support.actioncard.ActionCardWhole;
import com.xkcoding.magic.message.model.dingtalk.support.actioncard.Button;
import com.xkcoding.magic.message.model.dingtalk.support.feedcard.FeedCard;
import com.xkcoding.magic.message.model.dingtalk.support.feedcard.FeedCardLink;
import com.xkcoding.magic.message.model.dingtalk.support.link.Link;
import com.xkcoding.magic.message.model.dingtalk.support.markdown.Markdown;
import com.xkcoding.magic.message.model.dingtalk.support.text.Text;
import com.xkcoding.magic.message.support.dingtalk.DingTalkMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 钉钉测试类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/11 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DingTalkMessageSenderTests {
    @Autowired
    private DingTalkMessageSender dingTalkMessageSender;

    /**
     * 测试 text 消息
     */
    @Test
    public void testText() {
        TextDingTalkMessage textMsg = new TextDingTalkMessage();
        textMsg.setText(new Text("测试钉钉文本消息"));
        // 测试 @all
        textMsg.setAt(new Attention(Collections.singletonList("17326075631"), true));
        dingTalkMessageSender.send(textMsg);
    }

    /**
     * 测试 link 消息
     */
    @Test
    public void testLink() {
        LinkDingTalkMessage linkMsg = new LinkDingTalkMessage();
        Link link = new Link();
        link.setTitle("测试钉钉超链接消息：spring-boot-demo");
        link.setText("spring boot demo 是一个用来深度学习并实战 spring boot 的项目，目前总共包含 63 个集成demo，已经完成 52 个。");
        link.setMessageUrl("https://github.com/xkcoding/spring-boot-demo");
        link.setPicUrl("https://camo.githubusercontent.com/14758c0377ca916afc6f8624f6299bcdd68667a8/68747470733a2f2f7374617263686172742e63632f786b636f64696e672f737072696e672d626f6f742d64656d6f2e737667");
        linkMsg.setLink(link);
        dingTalkMessageSender.send(linkMsg);
    }

    /**
     * 测试 markdown 消息
     */
    @Test
    public void testMarkdown() {
        MarkdownDingTalkMessage markdownMsg = new MarkdownDingTalkMessage();
        Markdown markdown = new Markdown();
        markdown.setTitle("测试钉钉markdown消息");
        markdown.setText("# 测试钉钉markdown消息：magic-starter-message\n" + "\n" + "## 简介\n" + "\n" + "`magic-starter-message` 主要是对一些常用的发送消息通知的封装，包括钉钉机器人、各类邮件、阿里大鱼、微信等。\n" + "\n" + "## 模块地址\n" + "\n" + "[magic-starter-message](https://github.com/xkcoding/magic-starter/magic-starter-message)");
        markdownMsg.setMarkdown(markdown);
        // 测试 @17326075631
        markdownMsg.setAt(new Attention(Collections.singletonList("17326075631"), false));
        dingTalkMessageSender.send(markdownMsg);
    }

    /**
     * 测试 整体跳转ActionCard 消息
     */
    @Test
    public void testActionCardWhole() {
        ActionCardWholeDingTalkMessage wholeMsg = new ActionCardWholeDingTalkMessage();
        ActionCardWhole actionCardWhole = new ActionCardWhole();

        actionCardWhole.setTitle("测试整体跳转消息");
        actionCardWhole.setText("# 测试整体跳转消息：GitHub Actions 初体验\n" + "\n" + "## 前言\n" + "\n" + "前段时间同性交友网站 `GitHub` 推出了自家的 `Registry` 服务，支持多种语言的镜像服务，具体参见：https://help.github.com/en/articles/about-github-package-registry\n" + "\n" + "随后，又推出了自家的自动化构建服务：`Github Actions`，具体参见：https://help.github.com/en/articles/about-github-actions\n" + "\n" + "这俩服务我都在第一时间申请使用了，也都已经审核通过了，但是由于 `Registry` 测试有些不稳定，还存在一些问题，所以，并未发布体验文章，不过 关于`GitHub Actions` 我已经成功体验了一把。\n");
        actionCardWhole.setHideAvatar("0");
        actionCardWhole.setBtnOrientation("0");
        actionCardWhole.setSingleTitle("点我学习");
        actionCardWhole.setSingleURL("https://xkcoding.com/2019/08/29/early-experience-about-github-actions.html");

        wholeMsg.setActionCard(actionCardWhole);
        dingTalkMessageSender.send(wholeMsg);
    }

    /**
     * 测试 独立跳转ActionCard 消息
     */
    @Test
    public void testActionCardSingle() {
        ActionCardSingleDingTalkMessage singleMsg = new ActionCardSingleDingTalkMessage();
        ActionCardSingle actionCardSingle = new ActionCardSingle();
        actionCardSingle.setTitle("测试独立跳转消息");
        actionCardSingle.setText("# 测试独立跳转消息：重要提示\n" + "\n" + "> 本群已与多加搜索引擎合作，遇到问题先去合作平台咨询！");
        actionCardSingle.setBtnOrientation("0");
        actionCardSingle.setHideAvatar("0");

        List<Button> buttons = new ArrayList<>();
        buttons.add(new Button("百度咨询一下", "https://www.baidu.com/"));
        buttons.add(new Button("谷歌翻墙一下", "https://www.google.com/"));
        buttons.add(new Button("是在不行，你就 360 一下", "https://www.so.com/?src=360portal&ls="));

        actionCardSingle.setBtns(buttons);
        singleMsg.setActionCard(actionCardSingle);
        dingTalkMessageSender.send(singleMsg);
    }

    /**
     * 测试 FeedCard 消息
     */
    @Test
    public void testFeedCard() {
        FeedCardDingTalkMessage feedMsg = new FeedCardDingTalkMessage();
        FeedCard feedCard = new FeedCard();
        List<FeedCardLink> feedCardLinks = new ArrayList<>();
        feedCardLinks.add(new FeedCardLink("设计模式之行为型设计模式 - 策略模式", "https://xkcoding.com/2019/09/03/design-pattern-strategy.html", "https://static.xkcoding.com/blog/2019-08-28-strategy-uml.png"));
        feedCardLinks.add(new FeedCardLink("设计模式之结构型设计模式 - 适配器模式", "https://xkcoding.com/2019/09/13/design-pattern-adapter.html", "https://static.xkcoding.com/blog/2019-09-29-design-pattern-adapter-uml.png"));
        feedCardLinks.add(new FeedCardLink("设计模式之行为型设计模式 - 委派模式", "https://xkcoding.com/2019/08/27/design-pattern-delegate.html", "https://static.xkcoding.com/blog/2019-08-28-delegate-uml.png"));
        feedCardLinks.add(new FeedCardLink("设计模式之结构型设计模式 - 代理模式", "https://xkcoding.com/2019/08/20/design-pattern-proxy.html", "https://static.xkcoding.com/blog/2019-08-21-proxy-dynamicproxy-cglib-uml.png"));
        feedCard.setLinks(feedCardLinks);
        feedMsg.setFeedCard(feedCard);
        dingTalkMessageSender.send(feedMsg);
    }


}
