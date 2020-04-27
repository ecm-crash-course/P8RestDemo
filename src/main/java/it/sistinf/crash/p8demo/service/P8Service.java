package it.sistinf.crash.p8demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.sistinf.crash.p8demo.model.ObjectStoreModel;

@Service
public class P8Service {

    // Set the constants
    // Use /FileNet/Engine for EJB
    @Value("${p8.url}")
    private String uri;
    @Value("${p8.username}")
    private String username;
    @Value("${p8.password}")
    private String password;
    @Value("${p8.object_store}")
    private String objectStore;

    public ObjectStoreModel getObjectStoreList(){
        // Get the connection
        Connection conn = Factory.Connection.getConnection(uri);
        UserContext uc = UserContext.get();
        // Build the subject using the FileNetP8 stanza
        // Use FileNetP8 for the EJB transport (also the default)
        uc.pushSubject(
            UserContext.createSubject(conn,username,password,"FileNetP8WSI" )
        );
        
        ObjectStoreModel myOs = null;
        try
            {
            // Get the default domain
            Domain domain = Factory.Domain.getInstance(conn, null);
            // Get an object store
            ObjectStore os = Factory.ObjectStore.fetchInstance(domain, this.objectStore, null);
            myOs = new ObjectStoreModel(os.get_DisplayName());
        } catch (Exception e ){
            throw e;
        } finally {
            // Pop the subject off the UserContext stack
            uc.popSubject();
        }
        return myOs;
    }


}