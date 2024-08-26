package BT1;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPIExample {
    public static void main(String[] args) {

        int[] numbers = {3, 7, 2, 8, 6, 10, 5, 11};

        OptionalInt maxNumber = Arrays.stream(numbers).max();
        System.out.println("Số lớn nhất trong mảng: " + (maxNumber.isPresent() ? maxNumber.getAsInt() : "Mảng rỗng"));

        List<Integer> evenNumbers = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Các số chẵn trong mảng: " + evenNumbers);

        int threshold = 6;
        List<Integer> greaterThanThreshold = Arrays.stream(numbers)
                .filter(n -> n > threshold)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Các số lớn hơn " + threshold + ": " + greaterThanThreshold);


        int sum = Arrays.stream(numbers).reduce(0, Integer::sum);
        System.out.println("Tổng các số trong mảng: " + sum);

        boolean hasEven = Arrays.stream(numbers).anyMatch(n -> n % 2 == 0);
        System.out.println("Danh sách có chứa ít nhất một số chẵn: " + hasEven);

        int x = 5, y = 10;
        List<Integer> rangeList = IntStream.range(x, y + 1).boxed().collect(Collectors.toList());
        System.out.println("Danh sách các số từ " + x + " đến " + y + ": " + rangeList);

        List<String> words = Arrays.asList("apple", "orange", "banana", "pear", "grape");
        List<String> sortedWords = words.stream().sorted().collect(Collectors.toList());
        System.out.println("Danh sách các từ sau khi sắp xếp: " + sortedWords);

        List<String> upperCaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Danh sách các từ sau khi chuyển thành chữ in hoa: " + upperCaseWords);
    }
}

