package com.erikender.webapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class InventoryDto {

    private int userId;
    private String itemName;

    public InventoryDto(int userId, String itemName) {
        this.userId = userId;
        this.itemName = itemName;
    }
}
