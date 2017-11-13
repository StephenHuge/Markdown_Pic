git pull
git status
@rem ready to add and commit
@set /p a=please input commit message:
pause
git add .
git commit -m "%a%"
git push
pause