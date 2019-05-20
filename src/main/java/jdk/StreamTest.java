package jdk;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/19
 * @Description:
 */
public class StreamTest {
    //集合专注的是数据，流专注的是计算
    @Test
    public void test() {
        List<Integer> list2 = Arrays.asList(2, 7, 6, 6);
//        Set<String> list1 = Arrays.asList("1", "3", "2", "1");
        LinkedHashSet<Integer> integers = new LinkedHashSet<>(list2);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
//        list.stream()
//                .filter((e) -> e > 5)   //e是接受的一个参数，返回的是该参数加1 的值
//                .forEach(System.out::println);
//        //数组创建流
//        Integer[] num = new Integer[8];
//        Stream<Integer> stream = Arrays.stream(num);
//        //值创建流。可以接受任何数量的参数
//        Stream<String> test = Stream.of("test", "one");
//        Stream<Integer> integerStream = Stream.of(2, 4, 65);
//        Stream.iterate(1,(a) ->a+1).forEach(System.out::println);// 无限流
//        Stream.generate(() -> Math.random()).forEach(System.out::println);
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
//        list.stream()
//                .distinct()
//                .forEach(System.out::println);
//        list.stream()
//                .skip(7)
//                .forEach(System.out::println);
//        list.stream()
//                .mapToInt(x -> x * 10)
//                .forEach(System.out::println);

        List<String> list = Arrays.asList("abc", "efg", "xyz");
        list.stream()
                .flatMap(StreamTest::string2Stream)
                .forEach(System.out::println);
    }

    /**
     * 接收一个字符串将其所以的字符添加到list中然后返回stream
     * @param str
     * @return
     */
    public static Stream<Character> string2Stream(String str) {
        List<Character> list = new ArrayList<>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test2() {
//        List<Integer> list = Arrays.asList(9, 10, 7, 3);
//        Optional<Integer> first = list.stream()
//                .findAny();
//        Integer integer = first.get();
//        // 是否全部元素都大于2
//        System.out.println(integer);

//        Optional<Integer> integer = Optional.ofNullable(1);
//        // 如果不是null， 调用Consumer
//        integer.ifPresent(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println("value is " +integer);
//            }
//        });

//        orElseThrow（判空等场景特别有用）
//        三目运算符，方法连缀
//        System.out.println(Optional.ofNullable("demo").orElse("a"));
////        System.out.println(Optional.ofNullable());
//        OptionalInt reduce = IntStream.of(1, 2, 3,4)
//                .reduce(Integer::sum);
//        System.out.println(reduce.getAsInt());

    }
}
