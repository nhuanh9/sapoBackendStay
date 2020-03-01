package com.example.hello.model;
import javax.persistence.*;
import java.util.Date;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String imageUrls;
    private Date createDay;
    private Date theMostNearEditDay;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Product(String name, String address, String imageUrls, Date createDay, Date theMostNearEditDay, Category category) {
        this.name = name;
        this.address = address;
        this.imageUrls = imageUrls;
        this.createDay = createDay;
        this.theMostNearEditDay = theMostNearEditDay;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getTheMostNearEditDay() {
        return theMostNearEditDay;
    }

    public void setTheMostNearEditDay(Date theMostNearEditDay) {
        this.theMostNearEditDay = theMostNearEditDay;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
