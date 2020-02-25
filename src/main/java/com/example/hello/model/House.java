package com.example.hello.model;

import javax.persistence.*;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameHouse;
    @ManyToOne
    @JoinColumn(name = "category_house")
    private CategoryHouse categoryHouse;


    private Long amountBathRoom;
    private Long amountBedRoom;
    private String address;
    private String description;
    private String imageUrl;

    public House( String nameHouse, CategoryHouse categoryHouse, Long amountBathRoom, Long amountBedRoom, String address, String description,  String imageUrl) {
        this.nameHouse = nameHouse;
        this.categoryHouse = categoryHouse;
        this.amountBathRoom = amountBathRoom;
        this.amountBedRoom = amountBedRoom;
        this.address = address;
        this.description = description;
        this.imageUrl = imageUrl;

    }
    public House() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNameHouse() {
        return nameHouse;
    }

    public void setNameHouse(String nameHouse) {
        this.nameHouse = nameHouse;
    }

    public CategoryHouse getCategoryHouse() {
        return categoryHouse;
    }

    public void setCategoryHouse(CategoryHouse categoryHouse) {
        this.categoryHouse = categoryHouse;
    }

    public Long getAmountBathRoom() {
        return amountBathRoom;
    }

    public void setAmountBathRoom(Long amountBathRoom) {
        this.amountBathRoom = amountBathRoom;
    }

    public Long getAmountBedRoom() {
        return amountBedRoom;
    }

    public void setAmountBedRoom(Long amountBedRoom) {
        this.amountBedRoom = amountBedRoom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
