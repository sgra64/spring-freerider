# 1. Project Assembly: *spring-freerider*

Project *spring-freerider* is assembled from two
[*"git-submodules"*](https://www.atlassian.com/git/tutorials/git-submodule)
that are developed and maintained separately from the project:

- [*".env"*](https://github.com/sgra64/se1-gitmodules/tree/env-mvn)
    -- holds the project sourcing script: *env.sh* to set the CLASSPATH
    environment variable from maven dependencies and git submodule

- [*".vscode"*](https://github.com/sgra64/se1-gitmodules/tree/vscode-mvn)
    -- with *VSCode settings* for the project.

Steps:

1. [Create *git*-Project: *spring-freerider*](#1-create-git-project-spring-freerider)

1. [Install *git-submodules*](#2-install-git-submodules)

1. [Install *.gitinit*, *.gitmodules*](#3-install-gitinit-gitmodules)

1. [Check into Own Remote Repository](#4-check-into-own-remote-repository)



&nbsp;

## 1. Create *git*-Project: *spring-freerider*

Select a workspace on your laptop and create a project directory:
*"spring-freerider"*.

In it, create a local git repository with an empty root commit tagged as *"root"*:

```sh
git init --initial-branch=main
git commit --allow-empty -m "root commit (empty)"
git tag root

git log --oneline       # show commit log with empty root commit tagged as 'root'
```
```
c9f861e (HEAD -> main, tag: root) root commit (empty)
```



&nbsp;

## 2. Install *git-submodules*

Create a new branch: *"gitmodules"* off the root commit to track *git modules*.

```sh
# create local branch 'gitmodules'
git switch -c gitmodules

# install folders '.env' and '.vscode' as git-submodules
git submodule add -b env-mvn -f -- https://github.com/sgra64/se1-gitmodules.git .env
git submodule add -b vscode-mvn -f -- https://github.com/sgra64/se1-gitmodules.git .vscode
```
```
Cloning into 'C:/Sven1/svgr2/tmp/svgr/workspaces/2-se/spring-freerider/.env'...
remote: Enumerating objects: 36, done.
remote: Counting objects: 100% (36/36), done.
remote: Compressing objects: 100% (28/28), done.
remote: Total 36 (delta 11), reused 28 (delta 3), pack-reused 0 (from 0)
Receiving objects: 100% (36/36), 16.31 KiB | 1.48 MiB/s, done.
Resolving deltas: 100% (11/11), done.

Cloning into 'C:/Sven1/svgr2/tmp/svgr/workspaces/2-se/spring-freerider/.vscode'...
remote: Enumerating objects: 36, done.
remote: Counting objects: 100% (36/36), done.
remote: Compressing objects: 100% (28/28), done.
remote: Total 36 (delta 11), reused 28 (delta 3), pack-reused 0 (from 0)
Receiving objects: 100% (36/36), 16.31 KiB | 1.36 MiB/s, done.
Resolving deltas: 100% (11/11), done.
```

Commit *git modules* to branch *"gitmodules"*:

```sh
git commit -m "add git-modules .env, .vscode"
```

Since *git-submodules* are maintained by a seprate project-team, updates
can be obtained on the *gitmodules* branch:

```sh
git switch gitmodules

# pull updates for each git-module
git submodule foreach git pull
```
```
Entering '.env'
Already up to date.
Entering '.vscode'
Already up to date.
```



&nbsp;

## 3. Install *.gitinit*, *.gitmodules*

Switch back to the *main* branch and set-up *.gitignore* and *.gitmodules* files.

```sh
git switch main

# checkout file '.gitmodules' from the 'submodules' branch
git checkout gitmodules .gitmodules

# fetch '.gitignore' file from URL
curl -o .gitignore \
    https://raw.githubusercontent.com/sgra64/spring-freerider/refs/heads/main/.gitignore

# commit files to the 'main' branch
git add .gitignore .gitmodules
git commit "add .gitignore, .gitmodules"

# show commit log of 'main'-branch
git log --oneline
```
```
8c8de5f (HEAD -> main) add .gitignore, .gitmodules
c9f861e (tag: root) root commit (empty)
```



&nbsp;

## 4. Check into Own Remote Repository

Push the *main*-branch to a remote repository: *"spring-freerider"* that you
can create on *GitHub* oder *GitLab* (e.g.
[*gitlab.bht-berlin.de*](gitlab.bht-berlin.de)).

Log into the account and create a new empty project *"spring-freerider"*.

Set-up a local *"remote"* (URL) pointing at the remote project:

```sh
# set up the link named 'origin' pointing at the remote project repository
git remote add origin <project-URL>

# show the new remote named 'origin'
git remote -v
```
```
origin  git@github.com:sgra64/spring-freerider.git (fetch)
origin  git@github.com:sgra64/spring-freerider.git (push)
```

Push local branch *main* to the remote:

```sh
git push -u origin main
```

If you receive an error, pull the remote *main* branch before pushing:

```sh
git pull origin main
git push -u origin main
```
