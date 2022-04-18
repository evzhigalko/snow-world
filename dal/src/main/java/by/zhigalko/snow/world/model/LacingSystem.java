package by.zhigalko.snow.world.model;

public enum LacingSystem {
    BOA("Кнопка"),
    LACES("Шнурки");

    private final String mechanism;

    LacingSystem(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getMechanism() {
        return mechanism;
    }
}
