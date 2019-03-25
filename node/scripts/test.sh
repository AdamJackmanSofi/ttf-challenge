#!/bin/sh

set -e -o pipefail

# This will fail if symlinks are used to run this script
cd "$(dirname "${BASH_SOURCE[0]}")"

#IFS=$(echo -en "\n\b")
for f in $(find ../tests -type f -name '*_test.js'); do
	node $f || break
done
