package com.setuschool.backend.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageResponse {
    private UserDto userDto;
    private String message;
    private String ImageName;
    private boolean success;
    private Integer responseCode;
}
