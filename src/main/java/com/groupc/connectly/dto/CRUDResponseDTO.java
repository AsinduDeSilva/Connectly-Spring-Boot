package com.groupc.connectly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CRUDResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;
}
