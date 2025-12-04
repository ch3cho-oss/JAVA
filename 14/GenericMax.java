public class GenericMax {
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] intArr = {3, 1, 4, 1, 5, 9};
        String[] strArr = {"apple", "banana", "cherry"};
        Double[] doubleArr = {1.2, 3.4, 2.5};
        
        System.out.println("整数数组最大值：" + findMax(intArr));
        System.out.println("字符串数组最大值：" + findMax(strArr));
        System.out.println("小数数组最大值：" + findMax(doubleArr));
    }
}
