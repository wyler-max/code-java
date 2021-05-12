package org.example.practicecode.designpattern.iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    private String name;
    private String description;
    private boolean vagetable;
    private float price;
}
