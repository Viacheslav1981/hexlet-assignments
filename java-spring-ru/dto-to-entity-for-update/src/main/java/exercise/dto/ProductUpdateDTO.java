package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductUpdateDTO {

    private String title;
    private int price;
    private Date updatedAt;
}
