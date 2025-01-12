package com.design.pattern.structural;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Used when you want to create large number similar objects in Hundreds of thousands
// To reduce memory usage you share the object that are similar in some way rather then creating new ones
// Intrinsic state and Extrinsic state

// 1. Flyweight Interface
interface Flyweight {
    void render(String extrinsicState);
}

// 2. Concrete Flyweight (Character Representation)
class CharacterFlyweight implements Flyweight {
    private final char character;    // Intrinsic state
    private final String fontFamily; // Intrinsic state
    private final int fontSize;      // Intrinsic state
    private final String fontStyle;  // Intrinsic state

    public CharacterFlyweight(char character, String fontFamily, int fontSize, String fontStyle) {
        this.character = character;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
    }

    @Override
    public void render(String extrinsicState) {
        System.out.println("Rendering " + character + " [" + fontFamily + ", " + fontSize + ", " + fontStyle + ", " + extrinsicState + "]");
    }
}

// 3. Flyweight Factory
class FlyweightFactory {
    private final Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(char character, String fontFamily, int fontSize, String fontStyle) {
        String key = character + "-" + fontFamily + "-" + fontSize + "-" + fontStyle;
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new CharacterFlyweight(character, fontFamily, fontSize, fontStyle));
        }
        return flyweights.get(key);
    }
}

// 4. Unshared Flyweight (Custom Properties)
class Character {
    private final Flyweight flyweight; // Shared flyweight
    private final String color;       // Extrinsic state
    private final int positionX;      // Extrinsic state
    private final int positionY;      // Extrinsic state

    public Character(Flyweight flyweight, String color, int positionX, int positionY) {
        this.flyweight = flyweight;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void render() {
        flyweight.render(color);
        System.out.println("Position: (" + positionX + ", " + positionY + ")");
    }
}

// 5. Client
public class FlyWeightClient {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();

        // Create shared flyweights
        Flyweight flyweightA = factory.getFlyweight('A', "Arial", 12, "Bold");
        Flyweight flyweightB = factory.getFlyweight('B', "Arial", 12, "Bold");

        // Create characters with custom properties
        Character char1 = new Character(flyweightA, "Red", 10, 20);
        Character char2 = new Character(flyweightB, "Blue", 15, 20);
        Character char3 = new Character(flyweightA, "Green", 20, 25);

        // Render characters
        char1.render();
        char2.render();
        char3.render();
    }
}
