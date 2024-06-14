package gorgeousSandwich.order.Util;


import gorgeousSandwich.order.Shared.exceptions.ValidationException;

public class Validations {
    private Validations() {
        //Util class
    }


    public static void numberIsInBetween(double value, double lower, double higher) throws ValidationException {
        if (value <= lower || value >= higher)
            throw new ValidationException(String.format("Number %s is not between %s and %s!", value, lower, higher));
    }

    public static void numberIsBetween(double value, double lower, double higher) throws ValidationException {
        if (value < lower || value > higher)
            throw new ValidationException(String.format("Number %s is not between %s and %s!", value, lower, higher));
    }

    public static void numberIsGreater(double value, double lower) {
        if (value <= lower) {
            throw new ValidationException(String.format("Number %s is not greater than %s!", value, lower));
        }

    }

    public static void numberIsEqual(double value, double comparison) throws ValidationException {
        if (value != comparison) {
            throw new ValidationException(String.format("Number %s is not equal to %s!", value, comparison));
        }
    }

    public static void numberIsLower(double value, double higher) throws ValidationException {
        if (value >= higher) {
            throw new ValidationException(String.format("Number %s is not lower than %s!", value, higher));
        }
    }

    public static void numberIsPositive(double value) throws ValidationException {
        try {
            numberIsGreater(value, 0);
        } catch (ValidationException e) {
            throw new ValidationException(String.format("Number %s is not Positive", value), e);
        }
    }

    public static void numberIsNegative(double value) throws ValidationException {
        try {
            numberIsLower(value, 0);
        } catch (ValidationException e) {
            throw new ValidationException(String.format("Number %s is not Negative!", value), e);
        }
    }

    public static void numberIsNonPositive(double value) throws ValidationException {
        try {
            if (value > 0) {
                throw new ValidationException(String.format("Number %s is not Lower or Equal to zero!", value));
            }
        } catch (ValidationException e) {
            throw new ValidationException(String.format("Number %s is not Positive!", value), e);
        }
    }

    public static void numberIsNonNegative(double value) throws ValidationException {
        try {
            if (value < 0) {
                throw new ValidationException("double%s is not Higher or Equal to zero!");
            }
        } catch (ValidationException e) {
            throw new ValidationException(String.format("Number %s is not Negative", value), e);
        }
    }

    public static void notAnyNull(Object... objects) throws ValidationException {
        for (Object o : objects) {
            notNull(o);
        }
    }

    public static void notNull(Object object) throws ValidationException {
        if (object == null) {
            throw new ValidationException("An object was NULL where it should not be!");
        }
    }

    public static void notEmpty(String string) throws ValidationException {
        if (string == null || string.isEmpty()) {
            throw new ValidationException("String was empty where it should not be!");
        }
    }

    public static void notAnyEmpty(String... strings) throws ValidationException {
        for (String string : strings) {
            notEmpty(string);
        }
    }

    public static void matchesRegex(String value, String regex) throws ValidationException {
        Validations.notEmpty(regex);
        if (value == null || !value.matches(regex)) {
            throw new ValidationException(String.format("String %s does not match pattern %s!", value, regex));
        }
    }

    public static void isTrue(boolean condition) {
        if (!condition) {
            throw new ValidationException("Condition is false!");
        }
    }

    public static void isFalse(boolean condition) {
        if (!condition) {
            throw new ValidationException("Condition is not false!");
        }
    }


}
