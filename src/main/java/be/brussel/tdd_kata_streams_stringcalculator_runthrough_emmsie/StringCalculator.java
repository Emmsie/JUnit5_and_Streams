package be.brussel.tdd_kata_streams_stringcalculator_runthrough_emmsie;

import java.util.*;
import java.util.stream.Collectors;

public class StringCalculator {

    private int count;

    public int add(String numbers) throws Exception {

        count++;

        if(numbers.isEmpty()){
            return 0;
        }

        String  allNumbers = stringWithCommaAsDelimiter(numbers);
        String allNumbersWithCommaInsteadOfNewLine = allNumbers.replaceAll("\n", ",");
        String[] numbersStringArray = allNumbersWithCommaInsteadOfNewLine.split(",");

        List<Integer> numbersIntList = stringArrayToIntegerList(numbersStringArray);

        List<Integer> negativeNumbers = listAllNegativeNumbers(numbersIntList);
        checkForNegatives(negativeNumbers);

        List<Integer> numbersSmallerThan1001 = listAllNumbersSmallerThan1001(numbersIntList);

        return calculateSum(numbersSmallerThan1001);
    }

    public int getCalledCount(){
        return count;
    }



    private String stringWithCommaAsDelimiter(String numbers){
        String stringWithCommaAsDelimiter = numbers;

        if(numbers.startsWith("//") && numbers.startsWith("\n", 3)){
            stringWithCommaAsDelimiter = numbers.replaceAll(numbers.substring(2,3), ",")
                    .substring(4, numbers.length());
        }
        return stringWithCommaAsDelimiter;
    }

    private List<Integer> listAllNegativeNumbers(List<Integer> allNumbers){
        return allNumbers.stream().filter(e->e<0).collect(Collectors.toList());
    }

    private List<Integer> stringArrayToIntegerList(String[] numbersAsStringArray){
        return Arrays.stream(numbersAsStringArray).mapToInt(Integer::valueOf)
                .boxed().collect(Collectors.toList());
    }

    private void checkForNegatives(List<Integer> negativeNumbers) throws Exception {
        if(!negativeNumbers.isEmpty()){
            StringBuilder exceptionStringBuilder = new StringBuilder();
            negativeNumbers.forEach(e -> exceptionStringBuilder.append(e +" "));
            throw new Exception("negatives not allowed: " + exceptionStringBuilder);
        }
    }

    private List<Integer> listAllNumbersSmallerThan1001(List<Integer> numbersIntList){
        return numbersIntList.stream().filter(e->e<1001).collect(Collectors.toList());
    }

    private int calculateSum(List<Integer> numbers){
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

}
