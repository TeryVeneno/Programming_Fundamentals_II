package course.labs.single;

public class Lab6 {

    // Part 1

    static boolean affirmative (String s) {
        return s.matches("[Tt]rue|[Yy]es");
    }

    // Part 2

    static boolean conVolCon (String s) {
        return s.matches("(?i)[^aeiou][aieou][^aeiou].*");
    }

    // Part 3

    static boolean checkPhoneFormat (String s) {
        return s.matches("[(]\\d{3}[)]\\s\\d{3}\\s\\d{4}");
    }

    // Part 4

    static boolean twoNum (String s) {
        return s.matches("^(.*\\d.*\\d.*)$");
    }

    // Part 5

    static boolean passwordValidate (String s) {
        return s.matches("^(?=.*\\d)^(?=.*[A-Z])^(?=.*[!@#$%^&*]).*$");
    }

    // Part 6

    static boolean dup_three (String s) {
        return s.matches("\\b(\\w+)\\s+\\1\\b\\s+\\1\\b");
    }

    // Bonus, Not working

    static boolean reverse_match (String s) {
        return s.matches("\\b(\\w+)\\s+$(\\1)^");
    }

    public static void main(String[] args) {
        System.out.println(reverse_match("fun nuf"));
    }
}
