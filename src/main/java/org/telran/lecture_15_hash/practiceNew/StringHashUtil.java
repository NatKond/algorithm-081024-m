package org.telran.lecture_15_hash.practiceNew;

public class StringHashUtil {

    private static class Student {
        private String firstName;
        private String lastName;
    }

    public static int hashCode(Object object) {
        if (object instanceof Integer) {
            return (int) object;
        } else if (object instanceof Student) {
            Student student = (Student) object;
            return hashCode(student.firstName) + hashCode(student.lastName);
        }else if (object instanceof String) {
            return hashCode((String) object);
        } else {
            throw new UnsupportedOperationException("This method doesn't supported for this data type");
        }
    }

    public static int hashCode(String input) {
        //"hello" => h e l l o =>
        if (input == null) {
            return 0;
        }
        int hash = 0;
        int magicNumber = 31;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * magicNumber + input.charAt(i);
        }
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(hashCode("abi"));
        System.out.println(hashCode("abc"));
        System.out.println(hashCode("cba"));

        Student student = new Student();
        student.firstName ="first";
        student.lastName ="last";
        System.out.println(hashCode(student));
        System.out.println(hashCode(student));
    }
}
