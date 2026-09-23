# Android Jetpack Compose PAPB

A single Android Studio project for PAPB course modules and small Jetpack Compose experiments.

## Branching Strategy

`main` is the clean course workspace. Coursework and experiments live on separate branches so each module remains available without creating another Android project.

| Branch | Purpose | Example |
| --- | --- | --- |
| `main` | Stable project template and shared setup | `main` |
| `module/<name>` | One practical course module | `module/01-compose-introduction` |
| `experiment/<name>` | Temporary exploration or proof of concept | `experiment/lazy-column` |

Rules:

1. Keep `main` clean and runnable.
2. Use one `module/*` branch per course module.
3. Create module branches from `main` by default. For a cumulative module, pass the previous module as `BASE`.
4. Create experiment branches from the branch whose code you want to explore.
5. Put the final course project in its own repository.
6. Commit or stash changes before switching branches. The Makefile refuses branch switches from a dirty worktree.

## First-Time Setup

Set the remote after creating an empty repository on your Git host:

```bash
make set-remote URL=git@github.com:your-user/android-jetpack-compose-papb.git
make push
```

## Common Workflow

Create the first module from `main`:

```bash
make new-module NAME=01-compose-introduction
```

Create a cumulative module from the previous module:

```bash
make new-module NAME=02-state BASE=module/01-compose-introduction
```

Create an experiment from the current branch:

```bash
make new-experiment NAME=lazy-column
```

Switch branches and inspect the repository:

```bash
make switch BRANCH=main
make status
make branches
```

Build and test:

```bash
make build
make test
```

Run `make help` to see every available command.

## Development Tooling & Recommended Plugins

Panduan dan ringkasan shortcut plugin produktivitas Jetpack Compose (Compose HotSwan v2, Compose Navigation Graph, Rainbow Brackets, Key Promoter X, JSON to Kotlin Class) dapat dilihat pada [Panduan Plugin](docs/DEVELOPMENT_PLUGINS.md).

## Requirements

- Android Studio with a compatible Android SDK
- JDK required by the configured Android Gradle Plugin
- GNU Make for the shortcut commands

Android Studio can still run every Gradle task directly; the Makefile is only a convenience layer.
