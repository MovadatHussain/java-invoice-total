import java.nio.file.*;
import java.math.*;
import java.time.*;
import java.util.*;

class Main {
    static String[] fields(String row, int count) {
        String[] values = row.split(",", -1);
        if (values.length != count) throw new IllegalArgumentException("unexpected column count: " + row);
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].strip();
            if (values[i].isEmpty()) throw new IllegalArgumentException("empty field: " + row);
        }
        return values;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) throw new IllegalArgumentException("usage: java Main.java input.txt");
        var rows = Files.readAllLines(Path.of(args[0]));
        BigDecimal total = BigDecimal.ZERO;
        for (String row : rows) {
            String[] f = fields(row, 3);
            int qty = Integer.parseInt(f[0]);
            BigDecimal price = new BigDecimal(f[1]), rate = new BigDecimal(f[2]);
            if (qty < 1 || price.signum() < 0 || rate.signum() < 0) throw new IllegalArgumentException("negative input or zero quantity");
            BigDecimal gross = price.multiply(BigDecimal.valueOf(qty)).multiply(BigDecimal.ONE.add(rate.movePointLeft(2)));
            total = total.add(gross.setScale(2, RoundingMode.HALF_UP));
        }
        System.out.println(total.setScale(2));
    }
}
