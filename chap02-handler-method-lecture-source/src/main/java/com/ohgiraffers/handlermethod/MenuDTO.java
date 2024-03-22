package com.ohgiraffers.handlermethod;

/* 설명. DTO를 작성할 때, 커맨드 객체로 사용하려면 form의 name값과 이름을 일치시켜줘야 한다. */
public class MenuDTO {

    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    /* 설명. 커맨드 객체는 기본생성자를 이용해 인스턴스를 만들기 때문에 기본생성자가 반드시 필요함. */
    public MenuDTO() {
    }

    public MenuDTO(String name, int price, int categoryCode, String orderableStatus) {
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
