package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDTO {

    @NotBlank
    @Length(min = 4, max = 64)
    private String title;
    @NotBlank
    @Length(min = 4, max = 512)
    private String content;
}
