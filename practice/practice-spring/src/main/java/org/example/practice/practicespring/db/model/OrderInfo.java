package org.example.practice.practicespring.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private Long id;
    private Long userid;
    private String username;
    private Long price;
}
