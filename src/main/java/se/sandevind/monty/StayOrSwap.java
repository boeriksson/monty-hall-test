package se.sandevind.monty;

public enum StayOrSwap {
    STAY,
    SWAP;

    public static StayOrSwap getEnum(String value) {
        return valueOf(value.toUpperCase());
    }
}
