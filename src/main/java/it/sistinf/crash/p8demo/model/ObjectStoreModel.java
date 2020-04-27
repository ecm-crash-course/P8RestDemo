package it.sistinf.crash.p8demo.model;

import lombok.Data;

@Data
public class ObjectStoreModel {

    private String displayName;

    public ObjectStoreModel(String _displayName) {
        this.displayName = _displayName;
    }


}