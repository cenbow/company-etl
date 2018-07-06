package com.silita.task;

import com.silita.common.kafka.KafkaConsumerBase;
import com.silita.factory.AbstractFactory;
import com.silita.factory.MohurdFactory;
import com.silita.model.Document;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dh on 2018/7/5.
 */
@Component
public class ReceiveMsg extends KafkaConsumerBase {
    private static Logger logger = Logger.getLogger(ReceiveMsg.class);

    private AbstractFactory factory;

    @Autowired
    private MohurdFactory mohurdFactory;

    @PostConstruct
    public void init() {
        this.factory = mohurdFactory;
        super.init();
    }

    @Override
    protected void msgHandle(Object msg) throws Exception {
        Document document = (Document) msg;
        logger.info(String.format("接收消息==>实体类：%s", document.getObject().getClass().getName()));
        factory.process(document.getObject());
    }
}
