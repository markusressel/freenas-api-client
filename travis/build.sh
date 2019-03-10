#!/usr/bin/env bash

echo "Travis Container environment variables:"
env | sort

echo

if [[ "${TRAVIS_BRANCH}" =~ "${TELEGRAM_BRANCHES}"  ]]; then
  echo "Start compiling and assembling apk..."
  ./gradlew clean lintDebug assembleDebug --stacktrace
else
  echo "Start compiling WITHOUT assembling apk..."
  ./gradlew clean lintDebug --stacktrace
fi
