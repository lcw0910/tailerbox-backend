package com.tailerbox.backend.intra.data.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    @Min(1)
    @Max(5)
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 3)
    private String userId;
    private String userName;
    private String email;
    private Instant updatedAt;
    private Instant deletedAt;
    private Instant createdAt;
}
