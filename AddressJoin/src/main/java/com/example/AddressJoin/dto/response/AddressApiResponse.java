package com.example.AddressJoin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressApiResponse<T> {
    private T data;           
    private List<T> dataList; 
    private HttpStatus status;
    private String message;
    private boolean error;
}
