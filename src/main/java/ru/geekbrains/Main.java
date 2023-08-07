package ru.geekbrains;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Toy> queue = new PriorityQueue<Toy>((t1, t2) -> t2.getWeight() - t1.getWeight());

        Toy[] toys = {
                new Toy(1, "машина", 200),
                new Toy(2, "самолет", 300),
                new Toy(3, "танк", 400)
        };

        for (Toy toy : toys) {
            queue.offer(toy);
        }

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(getRandomToy(queue, random).getName());
            sb.append(System.lineSeparator());
        }

        try (FileWriter writer = new FileWriter("result.txt")) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Toy getRandomToy(PriorityQueue<Toy> queue, Random random) {
        int total = queue.stream().mapToInt(Toy::getWeight).sum();
        int rand = random.nextInt(total);
        int count = 0;
        for (Toy toy : queue) {
            count += toy.getWeight();
            if (count > rand) {
                return toy;
            }
        }
        throw new IllegalStateException("PriorityQueue is empty");
    }
}
