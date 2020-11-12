package com.mobo.xddemo.domain;

import com.mobo.xddemo.utils.DataStore;
import com.mobo.xddemo.utils.MessageUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 短信发送实体类
 *
 * @author Mobo
 * @create 2020-11-12-16:40
 */
@Component
public class MessageXSend{

    @Resource
    private MessageUtil messageUtil;

    public static final String ADDRESSBOOK = "addressbook";
    public static final String TO = "to";
    public static final String PROJECT = "project";
    public static final String VARS = "vars";
    public static final String LINKS = "links";
    public DataStore dataStore = null;

    public MessageXSend() {
        dataStore = new DataStore();
    }

    public void addTo(String address) {
        dataStore.addWithComma("to", address);
    }

    public void addAddressBook(String addressbook) {
        dataStore.addWithComma("addressbook", addressbook);
    }

    public void setProject(String project) {
        dataStore.put("project", project);
    }

    public void addVar(String key, String val) {
        dataStore.addWithJson("vars", key, val);
    }

    public String xsend() throws Exception {
        return messageUtil.request(dataStore);
    }
}