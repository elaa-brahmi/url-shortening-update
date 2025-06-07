package backend.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor //generate a constructor with all fields
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String fullName;
    private String status;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<UrlShortened> urls = new ArrayList<>();
}
