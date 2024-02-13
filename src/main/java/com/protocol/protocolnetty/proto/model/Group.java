package com.protocol.protocolnetty.proto.model;

import com.protocol.protocolnetty.proto.GroupedModel;
import com.protocol.protocolnetty.proto.annotation.StorageName;

@StorageName("tc_groups")
public class Group extends GroupedModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
