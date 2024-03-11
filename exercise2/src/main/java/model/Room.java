package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Room {
    private int id;
    private int hotelId;
    private int number;
    private String price;
}
