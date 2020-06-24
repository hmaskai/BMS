PWD=$(pwd)
GIT_ROOT=$(git rev-parse --show-toplevel)

cd "$GIT_ROOT" || return
mvn validate 2>&1
PASSED=$?
cd "$PWD" || return

if [[ $PASSED -ne 0 ]]; then
  exit 1
fi