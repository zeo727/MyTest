package JavaStudy.java8.testStream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author https://blog.csdn.net/weixin_34221775/article/details/89050311
 */
public class TestTransaction {
    List<Transaction> transactions;

    public static Stream<String> filterCharacter(String string) {
        List<String> list = new ArrayList<>();

        for (Character character : string.toCharArray()) {
            list.add(character.toString());
        }
        return list.stream();
    }

    @Before
    public void before() {
        Trader liu = new Trader("Lau", "Beijing");
        Trader lee = new Trader("Lee", "Shanghai");
        Trader zhang = new Trader("Zhang", "Guangzhou");
        Trader wang = new Trader("Wang", "Beijing");

        transactions = Arrays.asList(
                new Transaction(liu, 2016, 300),
                new Transaction(lee, 2015, 100),
                new Transaction(lee, 2016, 500),
                new Transaction(zhang, 2016, 9000),
                new Transaction(wang, 2017, 1000),
                new Transaction(liu, 2016, 1500)
        );
    }

    // (1) 找出2016年发生的所有交易，并按交易额排序（从低到高)。
    @Test
    public void test1() {
        transactions.stream()
                .filter(e -> e.getYear() == 2016)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

    }

    // (2) 交易员都在哪些不同的城市工作过？
    @Test
    public void test2() {
        transactions.stream()
                .map(e -> e.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    // (3) 查找所有来自于北京的交易员，并按姓名排序。
    @Test
    public void test3() {
        List<Trader> toSort = new ArrayList<>();
        for (Transaction e : transactions) {
            if (e.getTrader().getCity() == "Beijing") {
                Trader trader = e.getTrader();
                toSort.add(trader);
            }
        }
        toSort.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        Set<Trader> uniqueValues = new HashSet<>();
        for (Trader trader : toSort) {
            if (uniqueValues.add(trader)) {
                System.out.println(trader);
            }
        }
    }

    // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
    @Test
    public void test4() {
        transactions.stream()
                .map(e -> e.getTrader().getName())
                .sorted()
                .distinct()
                .forEach(System.out::println);
        System.out.println("----------------------");

        String str1 = transactions.stream()
                .map(e -> e.getTrader().getName())
                .sorted()
                .distinct()
                .reduce("", String::concat);

        System.out.println(str1);

        System.out.println("----------------------");

        transactions.stream()
                .map(e -> e.getTrader().getName())
                .distinct()
                .flatMap(TestTransaction::filterCharacter)
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::print);
    }

    // (5) 有没有交易员是在深圳工作的？
    @Test
    public void test5() {
        boolean b = transactions.stream()
                .allMatch(t -> t.getTrader().getCity().equals("Shenzhen"));
        System.out.println(b);

    }

    // (6) 打印生活在北京的交易员的所有交易额。
    @Test
    public void test6() {
        Optional<Integer> sum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Beijing"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(sum.get());

    }

    // (7) 所有交易中，最高的交易额是多少？
    @Test
    public void test7() {
        Optional<Transaction> max = transactions.stream()
                .max(Comparator.comparingInt(Transaction::getValue));

    }

    // (8) 找到交易额最小的交易。
    @Test
    public void test8() {
        Optional<Transaction> op = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));
        System.out.println(op.get());
    }


}
