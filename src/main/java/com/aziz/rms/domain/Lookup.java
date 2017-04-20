package com.aziz.rms.domain;

import javax.persistence.*;

/**
 * Created by Kholishul_A on 20/04/2017.
 */
@Entity
@Table(name="LOOKUP")
public class Lookup {

    @Id
    @GeneratedValue
    @Column(name="LOOKUP_ID")
    private long Id;

    @Column(name = "DATA_TYPE")
    private String dataType;

    @Column(name = "DATA_CODE")
    private String dataCode;

    @Column(name = "DATA_DESC")
    private String dataDesc;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }
}
