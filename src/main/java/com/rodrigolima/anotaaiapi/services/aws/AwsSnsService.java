package com.rodrigolima.anotaaiapi.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    AmazonSNS snsClient;

    Topic catalogTopic;

    public AwsSnsService(AmazonSNS amazonSNS, @Qualifier("catalogEventsTopic") Topic catalogTopic){
        this.snsClient = amazonSNS;
        this.catalogTopic = catalogTopic;
    }

    public void publish(MessageDTO message){
        snsClient.publish(catalogTopic.getTopicArn(),message.toString());
    }
}
