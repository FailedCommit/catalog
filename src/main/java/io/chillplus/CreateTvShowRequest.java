package io.chillplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTvShowRequest {
    @NotBlank(message="Title may not be blank")
    String title;
    @NotBlank(message="Category may not be blank")
    String category;
}
