package JavaStudy.java8.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMan {
    private Optional<Godness> godness = Optional.empty();
}
