@echo off
mode con: cols=60 lines=20>nul
title �������ݿ�
color 2f

set mycd=%~dp0
set file=db_44mo.sql
if exist %1% goto outfile

:start
echo ���ڵ������ݿ�%file%
if not exist %file% goto nofile
"%mycd%/mysql" -h localhost -u root -p123456<%file%
if %errorlevel%==0 goto suss

cls

:tryage
echo ����ʧ�ܣ����ڳ��Կ���mysql����
net start mysql>nul

if %errorlevel%==0 goto start

:bad

echo sorry������ʧ�ܣ������ֶ������

goto exit


:suss
echo ����ɹ�
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
echo �޷��ҵ����ݿ��ļ�%file%
ping 127.1>nul
goto exit

:outfile
set file="%1%"
goto start

