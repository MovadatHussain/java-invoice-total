# invoice-total

Calculate invoice totals from quantity, unit price and tax rate.

Each row is quantity,unit_price,tax_percent. Tax is rounded per line to cents. Use plain comma-separated fields without embedded commas or header rows.

## Run

Requires a JDK 17 or newer. From the project directory:

```sh
java Main.java example.txt
```

Expected console output:

```text
26.60
```

To regression-test on Linux or macOS, run `java Main.java example.txt > actual.txt && diff -u expected.txt actual.txt`. The example and expected output are included for Windows users as well.

On Linux or macOS, run `bash check.sh` to check per-line half-cent rounding and rejection of zero quantities as well as the example invoice.
