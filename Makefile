SHELL := /bin/sh
.DEFAULT_GOAL := help

.PHONY: help status branches check-clean set-remote push sync switch new-module new-experiment build test clean

help:
	@echo "Android Jetpack Compose PAPB"
	@echo ""
	@echo "Git:"
	@echo "  make status                         Show repository status"
	@echo "  make branches                       List local branches"
	@echo "  make set-remote URL=<url>           Add the origin remote"
	@echo "  make push                           Push the current branch and set upstream"
	@echo "  make sync                           Fast-forward the current branch"
	@echo "  make switch BRANCH=<branch>         Switch to an existing branch"
	@echo "  make new-module NAME=<name>         Create module/<name> from main"
	@echo "  make new-module NAME=<name> BASE=<branch>"
	@echo "  make new-experiment NAME=<name>     Create experiment/<name> from current branch"
	@echo ""
	@echo "Android:"
	@echo "  make build                          Build the debug APK"
	@echo "  make test                           Run unit tests"
	@echo "  make clean                          Remove Gradle build outputs"

status:
	@git status -sb

branches:
	@git branch --sort=-committerdate

check-clean:
	@git diff --quiet && git diff --cached --quiet && test -z "$$(git ls-files --others --exclude-standard)" || \
		{ echo "Error: commit or stash your changes first."; exit 1; }

set-remote:
	@test -n "$(URL)" || { echo "Error: use make set-remote URL=<url>"; exit 1; }
	@git remote get-url origin >/dev/null 2>&1 && git remote set-url origin "$(URL)" || git remote add origin "$(URL)"
	@echo "origin -> $(URL)"

push:
	@git push -u origin HEAD

sync: check-clean
	@git pull --ff-only

switch: check-clean
	@test -n "$(BRANCH)" || { echo "Error: use make switch BRANCH=<branch>"; exit 1; }
	@git switch "$(BRANCH)"

BASE ?= main
new-module: check-clean
	@test -n "$(NAME)" || { echo "Error: use make new-module NAME=<name>"; exit 1; }
	@git check-ref-format --branch "module/$(NAME)" >/dev/null
	@git switch "$(BASE)"
	@git switch -c "module/$(NAME)"

new-experiment: check-clean
	@test -n "$(NAME)" || { echo "Error: use make new-experiment NAME=<name>"; exit 1; }
	@git check-ref-format --branch "experiment/$(NAME)" >/dev/null
	@git switch -c "experiment/$(NAME)"

build:
	@./gradlew assembleDebug

test:
	@./gradlew test

clean:
	@./gradlew clean
