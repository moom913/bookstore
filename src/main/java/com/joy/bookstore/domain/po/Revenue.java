package com.joy.bookstore.domain.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "revenue")
public class Revenue implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "revenue", nullable = false, unique = true)
    private Integer revenue;

    public Revenue() {
    }

    public Revenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Long getId() {
        return id;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }
}
