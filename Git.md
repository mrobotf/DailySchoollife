## 分支学习
**通过 [Learn Git Branching](https://learngitbranching.js.org/?locale=zh_CN) 学习**

`git commit`提交一个新的版本

#### 创建分支
`git branch newbranch` 创建新的分支 *newbranch*

`git checkout newbranch` 转到 *newbranch* 分支上

`git checkout -b newbranch` 创建 *newbranch* 分支，并转到这个分支上


#### 合并分支
`git merge bugFix` 将*bugFix*分支合并到当前分支上

`git rebase main` 若当前所在分支为 *bugFix*，将这个分支和 *main* 父节点相连，相当于 *bugFix* 在 *main* 后


#### HEAD
`git checkout c4` *main* 指向c1, 此命令使得 *HEAD* 指向 c4<br>

`cat .git/HEAD` 查看 *HEAD* 指向

`git symbolic-ref HEAD` 如果 *HEAD* 指向的是一个引用, 用此命令查看

`git log` 查看提交记录的哈希值

+ *HEAD* 是一个对当前检出记录的符号引用, 也就是指向你正在其基础上进行工作的提交记录。
+ *HEAD* 总是指向当前分支上最近一次提交记录。大多数修改提交树的 Git 命令都是从改变 HEAD 的指向开始的。
+ *HEAD* 通常情况下是指向分支名的（如 bugFix）。在你提交时，改变了 bugFix 的状态，这一变化通过 HEAD 变得可见
+ 哈希值可能极其复杂(基于 SHA-1，共 40 位), 但只需要提供能够唯一标识提交记录的前几个字符即可。因此我可以仅输入 `fed2` 而不是上面的一长串字符


#### 相对引用
+ 通过哈希值指定提交记录很不方便, 所以有了相对引用, 这样就可以从一个易于记忆的地方(比如 *bugFix* 分支或 *HEAD*)开始计算

`^` 向上移动 1 个提交记录, 如 *HEAD^*, 向 *HEAD* 父节点移动了一次

`~<num>` 向上移动多个提交记录, 如 `git checkout HEAD~3`, *HEAD* 向后 4 步

`git branch -f main HEAD~<num>` 将 *main* 分支移动到 *HEAD~3* 的位置  `-f`指强制移动分支


#### 撤销变更
`git reset HEAD~1` 把分支记录回退一个提交记录。向上移动分支, 原来指向的提交记录还在，但是处于未加入暂存区状态, 本地代码库不知道有这个提交记录。但对大家一起使用的远程分支是无效的

`git revert HEAD` 若原来指向 `c2`, revert 之后, *c2* 后新有了一个 *c2'*, *c2'* 状态和 *c1* 相同, *c2* 还是 revert 前的 *c2*

+ 撤销变更由底层部分(暂存区的独立文件或者片段)和上层部分(变更到底是通过哪种方式被撤销的)组成


#### 整理提交记录
`git cherry-pick c2 c4` 将 *c2* *c4* 复制到 *HEAD* 后
+ `cherry-pick` 可以将提交树上任何地方的提交记录取过来追加到 *HEAD* 上（只要不是 *HEAD* 上游的提交就没问题）

`git rebase -i HEAD~4` 整理到 *HEAD~4* 的记录（`--interactive` 会打开一个 UI 界面）
>当 rebase UI 界面打开时, 你能做3件事:
  > + 调整提交记录的顺序（通过鼠标拖放来完成）
  > + 删除你不想要的提交（通过切换 pick 的状态来完成，关闭就意味着你不想要这个提交记录）
  > + 合并提交。它允许你把多个提交记录合并成一个。

`git commit --amend` 撤销上次的提交，上次的修改去掉重新提交


#### Git Tags
+ 一定程度上，一直指向某个提交记录的标识（标签可以被删除后重新在另外一个位置创建同名的标签）

`git tag v1 c1` 建立标签 *v1* 指向 *c1*（若不指定，会用 *HEAD* 所指向位置）

`git describe <ref>` 用来描述离你最近的锚点
+ `<ref>` 可以是任何能被 Git 识别成提交记录的引用，如果你没有指定的话，Git 会以你目前所检出的位置 *HEAD*
+ 输出的结果是 `<tag>_<numCommits>_g<hash>`, `<tag>` 表示的是离 `<ref>` 最近的标签， `<numCommits>` 是表示这个 `<ref>` 与 `<tag>` 相差有多少个提交记录， `<hash>` 表示的是你所给定的 `<ref>` 所表示的提交记录哈希值的前几位。
+ 当 ref 提交记录上有某个标签时，则只输出标签名称

`git bisect` 查找产生 Bug 的提交记录


## 远程

`git clone`

+ 远程仓库命名规则 `<remote name>/<branch name>`, `<remote name>` 为远程仓库名
+ 远程分支在你检出时自动进入分离 HEAD 状态。Git 这么做是出于不能直接在这些分支上进行操作的原因, 你必须在别的地方完成你的工作, （更新了远程分支之后）再用远程分享你的工作成果

`git fetch` 实际上将本地仓库中的远程分支更新成了远程仓库相应分支最新的状态, *o/main* 更新, 但不会更新 *main* 分支, 不会改变磁盘上的任何数据
`git pull` 对 `git fetch` 和 `git merge` 的合并, 但 *o/main* 在 merge 时并不改变
`git pull --rebase` 将本地分支接在远程最新分支之后
`git push` `git pull` 的相反命令, 所有分支都被同步

#### 远程服务器拒绝
+ 应该按照流程,新建一个分支, push 这个分支并申请 pull request
