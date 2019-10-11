package com.example.foss.pojo;

public class FoodStatus {
    private Integer statusId;
    private Integer foodId;
    private FoodInformation foodInformation;
    private String foodName;
    private String typeName;
    private String addDate;
    private String updateDate;
    private String operator;
    private String status;
    private int deleteTag;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public FoodInformation getFoodInformation() {
        return foodInformation;
    }

    public void setFoodInformation(FoodInformation foodInformation) {
        this.foodInformation = foodInformation;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(int deleteTag) {
        this.deleteTag = deleteTag;
    }
    @Override
    public String toString() {
        return "FoodStatus{" +
                "statusId=" + statusId +
                ", foodInformation=" + foodInformation +
                ", addDate='" + addDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", operator='" + operator + '\'' +
                ", status='" + status + '\'' +
                ", deleteTag=" + deleteTag +
                '}';
    }
}
