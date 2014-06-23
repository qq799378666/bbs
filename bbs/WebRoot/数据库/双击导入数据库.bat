@echo off
mode con: cols=60 lines=20>nul
title 导入数据库
color 2f

set mycd=%~dp0
set file=db_44mo.sql
if exist %1% goto outfile

:start
echo 正在导入数据库%file%
if not exist %file% goto nofile
"%mycd%/mysql" -h localhost -u root -p123456<%file%
if %errorlevel%==0 goto suss

cls

:tryage
echo 导入失败，正在尝试开启mysql服务
net start mysql>nul

if %errorlevel%==0 goto start

:bad

echo sorry，导入失败，还是手动导入吧

goto exit


:suss
echo 导入成功
color 6f
goto exit

:exit
ping 127.1>nul


for /l %%i in (20,-1,-20) do (
color %%i>nul
mode con lines=%%i>nul
)

for /l %%i in (60,-5,-50) do (
color %%i>nul
mode con cols=%%i>nul

)
exit

:nofile
echo 无法找到数据库文件%file%
ping 127.1>nul
goto exit

:outfile
set file="%1%"
goto start

