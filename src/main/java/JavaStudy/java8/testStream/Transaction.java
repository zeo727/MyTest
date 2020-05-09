package JavaStudy.java8.testStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//交易类

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
}
