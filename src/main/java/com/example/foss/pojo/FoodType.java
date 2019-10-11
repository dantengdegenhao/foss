package com.example.foss.pojo;

import java.util.Date;

public class FoodType {
    private int typeId;
    private String typeName;
    private String addDate;
    private String updateDate;
    private String operator;
    public FoodType() {
    }

    public FoodType(String typeName, String addDate, String updateDate, String operator) {
        this.typeName = typeName;
        this.addDate = addDate;
        this.updateDate = updateDate;
        this.operator = operator;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", addDate=" + addDate +
                ", updateDate=" + updateDate +
                ", operator='" + operator + '\'' +
                '}';
    }
}
