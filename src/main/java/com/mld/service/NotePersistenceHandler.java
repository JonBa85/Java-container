package com.mld.service;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotePersistenceHandler extends CouchDbRepositorySupport<Note> {

    @Autowired
    public NotePersistenceHandler(CouchDbConnector couchDbConnector) {
        super(Note.class, couchDbConnector);
        initStandardDesignDocument();
    }
}
