// Copyright (c) 2016. MyLife Digital Ltd
package com.mld.config;

import org.ektorp.CouchDbInstance;
import org.ektorp.DbAccessException;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.spring.HttpClientFactoryBean;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class CouchDBConfiguration {

    private static final Logger log = getLogger(CouchDBConfiguration.class);


    @Value("${mld.couchdb.host}")
    private String host;

    @Value("${mld.couchdb.port}")
    private String port;

    @Value("${mld.couchdb.database}")
    private String database;

    @Bean
    public StdCouchDbConnector connector() throws Exception {

        log.info("Attempting to establish CouchDB Connector on {}:{}/{}",new Object[]{host,port,database});
        CouchDbInstance dbInstance = new StdCouchDbInstance(getHttpClient());
        StdCouchDbConnector connector = new StdCouchDbConnector(database, dbInstance);

        //Validate the connection
        try {
            connector.createDatabaseIfNotExists();
        }catch (DbAccessException e)        {
            log.warn("Cannot connect to the database (is the database up and available?)", e);
        }
        return connector;
    }

    private  HttpClient getHttpClient() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("autoUpdateViewOnChange", "true");

        HttpClientFactoryBean factory = new HttpClientFactoryBean();
        factory.setUrl(host+":"+port);
        factory.setHost(host);
        factory.setPort(Integer.valueOf(port));
        factory.setProperties(properties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
