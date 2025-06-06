package backend.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomUrlRequest {
    @NotNull
    @NotEmpty
    @Size(min = 4, message = "Short URL must be at least 4 characters long")
    private String shortUrl;

    @NotNull
    @NotEmpty
    @Pattern( regexp = "^(https?://|www\\.)[a-zA-Z0-9\\-]+(\\.[a-zA-Z]{2,})+(:\\d+)?(/.*)?$",
            message = "Invalid URL format" )
    private String originalUrl;
}
