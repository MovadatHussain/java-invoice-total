#!/usr/bin/env bash
set -eu
cd "$(dirname "$0")"
work=$(mktemp -d)
trap 'rm -rf "$work"' EXIT
java Main.java example.txt > "$work/out"
diff expected.txt "$work/out"
# Two half-cent tax lines round separately, yielding 0.12 rather than 0.11.
printf '1,0.05,10\n1,0.05,10\n' > "$work/rounding"
test "$(java Main.java "$work/rounding")" = '0.12'
printf '0,2.00,23\n' > "$work/invalid"
if java Main.java "$work/invalid" > /dev/null 2>&1; then exit 1; fi
