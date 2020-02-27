package com.example.hello.model;

import javax.persistence.*;
@Entity
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "selection_id")
    private  Selection selection;

    public Version(Product product, Variant variant, Selection selection) {
        this.product = product;
        this.variant = variant;
        this.selection = selection;
    }

    public Version() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }
}
