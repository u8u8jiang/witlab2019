
an exercise of git

## AS06a GIT  

* 目前世界上最先進的內容追蹤軟體  
* Linux 之父第二偉大作品  
* 分散式架構  
* 圖形化介面工具輔助, 入門容易  
* 整合DevOps利器  

Q1. What difference between Git and GitLab?  
Q2. What is the HEAD of Git?  
Q3. What difference between merge and rebase?  
Q4. What difference between log and reflog?  

```Bash
git add
git commit -m "add @@"  
git push
```
![](.\image\git01.png)
![](.\image\git02.png)

* GIT是「分散式」的「版本控制」工具  
    * 輔助「偕同開發」的工具
  
  
## The three elements of Git  
* Blob: git的最小單位，a blob; a snapshot    
* tree: restore the relation of files to *.git\objects*    
* commit: record tree not blob into *.git\objects*     

## The four elements of point in Git
* tag    
* HEAD  
* remote  
* branch  

## 檔案還原的幾種指令
* restore    
* reset   
* revert: 保留git log    
* rebase  

```Bash
#放棄正在修改中，但尚未commit的檔案
git restore

#已commit但須往回退版本 
git reset head^   #退1步 
git reset head^^   #退2步
git reset head~5   #退5步

#看到修改的地方
git diff

#保留git log
git revert HEAD
```

## GitLab Flow (ground rule)  
1. 使用feature branch，需經code review才可commit進master  
2. 所有commit都要測試  
3. 自動部屬(based on branch and tag)  
4. tag靠人工加註而非CI  
5. 已push的commit不得rebase  
6. 每個人都從master開始  
7. 修bug順序，先master再release
8. commit訊息要明確說明用意

  
# Some keywords   
* CICD- 自動測試  
* autodeploy  
* container  

# issue
```Bash
# 1.檔名大小寫不對
git mv ANS.txt ans.txt
git config --local core.ignorecase false

# 2.救回commit
git reflog 
git reset commit --hard  

# 3.救回branch
git branch new_dev 2bbbbb  

# 4.無法推上remote
sync or pull rebase
push -f
```