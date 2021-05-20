package com.skandy.skandy.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "appartment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {

    @Id
    public UUID id;
    public int flatNumber;
    public int houseNumber;
    public int floor;
    public int gate;
    public int houseBuilding;
    public float area;
    public int roomCount;
}
